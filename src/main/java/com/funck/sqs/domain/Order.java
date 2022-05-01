package com.funck.sqs.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Order implements Serializable, Validator {

    private Long id;
    private String clientName;
    private Integer clientScore;
    private String cardOwner;
    private String cardNumber;
    private BigDecimal price;
    private Integer amount;

    public List<String> validationMessages = new ArrayList<>();

    @Override
    public void validate() {
        validationMessages.clear();

        if(!this.clientName.equals(cardOwner)){
            validationMessages.add("Client name is not equal card owner");
        }

        if(this.amount > 500){
            validationMessages.add("Amount too big for configured threshold");
        }

        if(this.clientScore < 50){
            validationMessages.add("Score too low for configured threshold");
        }
    }

    public String getValidationMessagesAsJson(){
        StringBuilder json = new StringBuilder("messages:[");
        validationMessages.forEach(message -> {
            json.append("'" + message + "'" + ",");
        });
        json.append("]");
        return json.toString();
    }

}
