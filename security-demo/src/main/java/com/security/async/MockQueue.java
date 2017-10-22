package com.security.async;/**
 * Created by HT on 2017/10/10.
 */

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author HT
 * @create 2017-10-10 14:14
 **/
@Data
@Component
public class MockQueue {
    private String placeOrder;

    private String completeOrder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            logger.info("接到下单请求," + placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完毕," + placeOrder);
        }).start();
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
