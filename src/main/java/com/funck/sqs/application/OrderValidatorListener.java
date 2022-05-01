package com.funck.sqs.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funck.sqs.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderValidatorListener {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final OrderMessageSender orderMessageSender;

    @JmsListener(destination = "order-validator")
    public void orderValidator(String orderContent) throws Exception{
        try {
            Order order = mapper.readValue(orderContent, Order.class);
            order.validate();
            log.info("Order received =" + order.toString());

            if(order.validationMessages.isEmpty()) {
                orderMessageSender.sendMessageToOrderFinisher("{\"status\": \"ORDER_VALID\", id:" + order.getId() + "}" );
            } else{
                log.info("Order is not valid");
                orderMessageSender.sendMessageToOrderFinisher("{\"status\": \"ORDER_NOT_VALID\", id:" + order.getId() + ", "
                        + order.getValidationMessagesAsJson() + "}"  );
            }
        }catch (Exception e){
            throw e;
        }
    }

}
