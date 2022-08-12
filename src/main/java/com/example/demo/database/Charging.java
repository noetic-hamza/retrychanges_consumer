package com.example.demo.database;

import com.example.demo.Repository.TblChargingRepo;
import com.example.demo.model.RetryMqMessage;
import com.example.demo.model.TblChargingEntity;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class Charging {
    @Autowired
    TblChargingRepo tblChargingRepo;

    public void updateRecordInChargingTable(RetryMqMessage entity,int isCharged){
      Optional<TblChargingEntity> tblChargingEntity= null;
        System.out.println("Data"+ entity.getShortCode());

//        try {
//            tblChargingEntity = tblChargingRepo.findById(BigInteger.valueOf(entity.getTblChargingId()));
//        }catch (Exception e){
//        }
      if (entity.getNumOfAttempts()>=5){
        TblChargingEntity tblChargingEntity1 = new TblChargingEntity();
        tblChargingEntity1.setId(BigInteger.valueOf(entity.getId()));
        tblChargingEntity1.setIscharged(isCharged);
        tblChargingEntity1.setOriginalSmsId(entity.getSmsId());
        tblChargingEntity1.setNumOfAttempts(entity.getNumOfAttempts());
        tblChargingEntity1.setStatuscode(entity.getShortCode());
        tblChargingEntity1.setOrigintransactionid(entity.getTblChargingId());
        tblChargingEntity1.setIscharged(entity.getIsCharged());
        tblChargingEntity1.setPartnerid(entity.getPartnerId());
        tblChargingEntity1.setResponsecode(entity.getResponseCode());
        tblChargingEntity1.setSubscribernumber(entity.getMsisdn());
        tblChargingEntity1.setAdjustmentamountrelative(entity.getAmount());
        tblChargingEntity1.setSmstext(entity.getSmsText());
//        tblChargingEntity1.setOperatorid(Math.toIntExact(entity.getOperatorId()));
        tblChargingEntity1.setLastAttemptTime(Timestamp.valueOf(LocalDateTime.now()));
        tblChargingEntity1.setNumOfAttempts(entity.getNumOfAttempts());
        tblChargingRepo.save(tblChargingEntity1);
        System.out.println("Data saved successfully");
            //purgeRecord(Long.valueOf(tblChargingEntity1.getId().toString()));

    }else{
        System.out.println("No of attempts are less than 5 so data cann't be updated in charging table");
      }}

}
