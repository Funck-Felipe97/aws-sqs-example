package com.funck.sqs.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderMessageSender {

    private final JmsTemplate jmsTemplate;

    public void sendMessageToOrderFinisher (String message){
        try{
            jmsTemplate.convertAndSend("order-finisher", message);
        }catch (Exception e){
            throw e;
        }
    }

}
