package com.security.async;/**
 * Created by HT on 2017/10/10.
 */

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author HT
 * @create 2017-10-10 11:26
 **/
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Autowired
    private MockQueue mockQueue;

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开启");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

//        Runnable 必须由主线程调取 场景复杂的情况下可能不满足我们的需求
//        Thread.sleep(1000);
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                logger.info("副线程开始");
//                Thread.sleep(1000);
//                logger.info("副线程结束");
//                return "success";
//            }
//        };
        logger.info("主线程返回");
        return result;
    }
}
