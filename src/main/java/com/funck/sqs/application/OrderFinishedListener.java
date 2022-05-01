package com.funck.sqs.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderFinishedListener {

    @JmsListener(destination = "order-finisher")
    public void orderFinisher(String orderFinisher) throws Exception{
        try {
            log.info("Finishing order:" + orderFinisher);
        }catch (Exception e){
            throw e;
        }
    }

}
