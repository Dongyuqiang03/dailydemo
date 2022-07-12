package com.example.dyq.util;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.xalan.processor.XSLTElementDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrushQpsUtil {
    private static final Logger log = LoggerFactory.getLogger(BrushQpsUtil.class);
    private static int qps = 10;
    private static RateLimiter rateLimiter = RateLimiter.create(qps);

    /**
     * 默认qps 10
     *
     * @param setQps
     */
    public static void rateLimiter(int setQps) {
        if (qps != setQps) {
            int tmpQps = setQps;
            log.info("current rateLimiter key:{}, qps:{}", setQps, tmpQps);
            rateLimiter.setRate(tmpQps);
        }else{
            log.info("default rateLimiter key:{}, qps:{}", setQps, qps);
        }
        rateLimiter.acquire();
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i=1;i<100;i++){
            rateLimiter(i);
            Thread.sleep(1000);
        }
    }
}
