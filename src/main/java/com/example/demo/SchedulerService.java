package com.example.demo;

import com.example.demo.Repository.TblChargingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class SchedulerService {

@Autowired
TblChargingRepo tblChargingRepo;



@Scheduled(cron = "01 04 11 * * *")
    public void deletemsisdns() {
    System.out.println("Scheduler JOB");
    tblChargingRepo.deleteAll();
    System.out.println("Data base flush successfully!");

}


}
