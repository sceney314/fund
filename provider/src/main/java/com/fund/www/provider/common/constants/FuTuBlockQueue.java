package com.fund.www.provider.common.constants;

import com.fund.www.provider.bean.vo.PlateNode;

import java.util.concurrent.ArrayBlockingQueue;

public class FuTuBlockQueue {
    /**
     * 板块阻塞队列
     */
    public static volatile ArrayBlockingQueue<PlateNode> PLATE_BLOCK_QUEUE = new ArrayBlockingQueue<>(64);
}
