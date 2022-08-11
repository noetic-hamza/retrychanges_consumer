package com.example.demo;

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
        TblChargingEntity tblChargingEntity1 = new TblChargingEntity();
        tblChargingEntity1.setId(BigInteger.valueOf(entity.getId()));
        tblChargingEntity1.setIscharged(isCharged);
        tblChargingEntity1.setOriginalSmsId(entity.getSmsId());
tblChargingEntity1.setNumOfAttempts(entity.getNumOfAttempts()+4);

        tblChargingEntity1.setLastAttemptTime(Timestamp.valueOf(LocalDateTime.now()));
        tblChargingEntity1.setNumOfAttempts(entity.getNumOfAttempts()+1);
        tblChargingRepo.save(tblChargingEntity1);
        System.out.println("Data saved successfully");
            //purgeRecord(Long.valueOf(tblChargingEntity1.getId().toString()));

    }
}
