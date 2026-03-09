package com.fund.www.provider.repository.futu;

import com.fund.www.provider.bean.bo.StockPlateBO;
import com.fund.www.provider.bean.vo.ObserverFuTuNode;
import com.fund.www.provider.common.futu.FuTuSubjectEnum;
import com.fund.www.provider.common.futu.ReturnTypeEnum;
import com.fund.www.provider.exceptions.ServiceException;
import com.fund.www.provider.observer.futu.ObserverFuTu;
import com.fund.www.provider.observer.futu.SubjectFuTu;
import com.futu.openapi.*;
import com.futu.openapi.pb.QotCommon;
import com.futu.openapi.pb.QotGetOwnerPlate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 服务 API
 */
public class FuTuOpenApi implements FTSPI_Conn, FTSPI_Qot, SubjectFuTu {
    /**
     * 服务 API 连接对象
     */
    private final FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();

    /**
     * 观察者Map
     */
    private final Map<FuTuSubjectEnum, ObserverFuTu> fuTuMap = new ConcurrentHashMap<>();

    /**
     * 单实例
     */
    private static volatile FuTuOpenApi instance;

    static {
        FTAPI.init();
    }

    private FuTuOpenApi(){
        this.qot.setClientInfo("javaclient", 1);  //设置客户端信息
        this.qot.setConnSpi(this);  //设置连接回调
        this.qot.setQotSpi(this);   //设置交易回调
        this.qot.initConnect("127.0.0.1", (short)11111, false);
    }

    /**
     * 获取实例
     *
     * @return FuTuOpenApi
     */
    public static FuTuOpenApi getInstance(){
        if (Objects.isNull(instance)){
            synchronized (FuTuOpenApi.class){
                if (Objects.isNull(instance)){
                    instance = new FuTuOpenApi();
                }
            }
        }
        return instance;
    }

    @Override
    public void registerObserver(ObserverFuTu observer) {
        fuTuMap.put(observer.subject(), observer);
    }

    @Override
    public void removeObserver(ObserverFuTu observer) {
        fuTuMap.remove(observer.subject());
    }

    /**
     * 通知观察者
     *
     * @param node 数据对象
     */
    private <T> void notifyObserver(ObserverFuTuNode<T> node) {
        ObserverFuTu observer = fuTuMap.getOrDefault(node.getSubject(), null);
        if (Objects.isNull(observer)){
            return;
        }
        observer.recieveData(node);
    }

    @Override
    public void onInitConnect(FTAPI_Conn client, long errCode, String desc) {
        System.out.printf("Qot onInitConnect: ret=%b desc=%s connID=%d\n", errCode, desc, client.getConnectID());
        if (errCode != 0) {
            throw new ServiceException("初始化【富途 API】对象异常，code:"+ errCode + ", 异常信息:" + desc);
        }
    }


    @Override
    public void onReply_GetOwnerPlate(FTAPI_Conn client, int serialNo, QotGetOwnerPlate.Response response) {
        checkResponse(response, "onReply_GetOwnerPlate");
        notifyObserver(new ObserverFuTuNode<>(serialNo, response, FuTuSubjectEnum.PLATE));
    }

    /**
     * 获取历史 K 线
     * 接口限制
     *
     * 分 K 提供最近 8 年数据，日 K 及以上提供最近 10 年的数据。
     * 我们会根据您账户的资产和交易的情况，下发历史 K 线额度。因此，30 天内您只能获取有限只股票的历史 K 线数据。具体规则参见 订阅额度 & 历史 K 线额度。您当日消耗的历史 K 线额度，会在 30 天后自动释放。
     * 每 30 秒内最多请求 60 次历史 K 线接口。注意：如果您是分页获取数据，此限频规则仅适用于每只股票的首页，后续页请求不受限频规则的限制。
     * 换手率，仅提供日 K 及以上级别。
     * 期权，仅提供日K, 1分K，5分K，15分K，60分K。
     * 美股 盘前和盘后 K 线，仅支持 60 分钟及以下级别。由于美股盘前和盘后时段为非常规交易时段，此时段的 K 线数据可能不足 2 年。
     * 美股的 成交额，仅提供 2015-10-12 之后的数据。
     *
     * @param boList
     * @return
     */
    public int queryHistoryKLine(List<StockPlateBO> boList){
        return  2;
    }

    /**
     * 获取股票 板块
     *
     * 接口限制
     * 每 30 秒内最多请求 10 次获取股票所属板块接口
     * 每次请求的股票列表中，股票个数上限为 200 个
     * 仅支持正股和指数
     *
     * @param boList 股票列表
     */
    public int getOwnerPlate(List<StockPlateBO> boList){
        List<QotCommon.Security> securityList = boList.stream()
                .map(bo -> {
                    QotCommon.Security sec = QotCommon.Security.newBuilder()
                            .setMarket(bo.getMarket().getCode())
                            .setCode(bo.getGupiaoCode())
                            .build();
                    return sec;
                })
                .collect(Collectors.toList());
        QotGetOwnerPlate.C2S c2s = QotGetOwnerPlate.C2S.newBuilder()
                .addAllSecurityList(securityList)
                .build();

        QotGetOwnerPlate.Request request = QotGetOwnerPlate.Request.newBuilder().setC2S(c2s).build();
        int requestSeq = qot.getOwnerPlate(request);
        System.out.println("批量获取股票板块请求序号:" + requestSeq);
        return requestSeq;
    }

    /**
     * 校验返回结果
     *
     * @param response 返回结果对象
     * @param method 方法
     */
    private void checkResponse(QotGetOwnerPlate.Response response, String method){
        if (Objects.equals(ReturnTypeEnum.RETURN_TYPE_FAILED.getCode(), response.getRetType())) {
            System.out.println(method + "获取信息失败，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
            throw new ServiceException("获取信息失败，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
        }

        if (Objects.equals(ReturnTypeEnum.RETURN_TYPE_TIMEOUT.getCode(), response.getRetType())) {
            System.out.println(method + "获取信息超时，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
            throw new ServiceException("获取信息超时，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
        }

        if (Objects.equals(ReturnTypeEnum.RETURN_TYPE_UNKNOWN.getCode(), response.getRetType())) {
            System.out.println(method + "获取信息未知结果，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
            throw new ServiceException("获取信息未知结果，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
        }

        if (!Objects.equals(ReturnTypeEnum.RETURN_TYPE_SUCCEED.getCode(), response.getRetType())) {
            System.out.println(method + "获取信息未知异常，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
            throw new ServiceException("获取信息未知异常，code:" +  response.getRetMsg() + ", 异常信息:" + response.getRetMsg());
        }
    }
}
