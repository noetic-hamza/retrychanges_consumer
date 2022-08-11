package com.example.demo.util;


import com.example.demo.Charging;
import com.example.demo.Repository.TblChargingRepo;
import com.example.demo.model.RetryMqMessage;
//import com.example.demo.TblChargingEntity;
import com.example.demo.model.TblChargingEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Service
public class RequestConsumer {
    Logger log = LoggerFactory.getLogger(RequestConsumer.class.getName());
@Autowired
    Charging obj;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = {"testqueue"})
    public void recieve (String message){

        try{
//            log.info("in RabbitMQ new queue");
//            log.info("message Recieved"+ message);
            System.out.println("In RabbitMq ");
            System.out.println("Message recieved "+ message );
            RetryMqMessage customMessage = objectMapper.readValue(message, RetryMqMessage.class);
            log.info(customMessage.getMsisdn());
            customMessage.setMsisdn(customMessage.getMsisdn());
            customMessage.setAmount(customMessage.getAmount());
            System.out.println("msisdn"+ customMessage.getMsisdn());
            RequestEvent event = new RequestEvent(this, customMessage);
            applicationEventPublisher.publishEvent(event);

            System.out.println("Custom"+ customMessage);
            System.out.println("DataBase entry point");

            obj.updateRecordInChargingTable( customMessage,1);
            System.out.println("DAtra added in database successfully");

            log.info("CONSUMER SERVICE | REQUESTCONSUMER CLASS | EVENT PUBLISHED FOR MSISDN | "+ event.getMessage().getMsisdn());

        } catch (Exception e) {
            log.info("Exception While Consuming "+e);
            e.printStackTrace();
        }
    }
}
