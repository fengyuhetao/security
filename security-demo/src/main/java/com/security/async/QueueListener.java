package com.security.async;/**
 * Created by HT on 2017/10/10.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author HT
 * @create 2017-10-10 14:24
 **/
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while(true) {
                if(!StringUtils.isEmpty(mockQueue.getCompleteOrder())) {
                    String orderNumber = mockQueue.getCompleteOrder();
                    logger.info("返回订单结果处理");
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
