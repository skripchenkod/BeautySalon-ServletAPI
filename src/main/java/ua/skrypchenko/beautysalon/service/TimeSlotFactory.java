package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;
import ua.skrypchenko.beautysalon.dao.impl.MasterScheduleDaoImpl;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

public class TimeSlotFactory {

   private final MasterScheduleDao masterScheduleDao = new MasterScheduleDaoImpl();

   public List<String> getScheduleOfMaster(String masterName, Date date) {
       List<String> diapasonSchedule = masterScheduleDao.getScheduleForFreeTimeSlots(masterName, date);
       List<String> schedule = new ArrayList<>();

          String startTimeS = diapasonSchedule.get(0);
          String endTimeS = diapasonSchedule.get(1);

           int startTimeI = Integer.parseInt(startTimeS.substring(0,2));
           int endTimeI = Integer.parseInt(endTimeS.substring(0,2));

       for (int i = startTimeI; i < endTimeI; i++) {
           if(startTimeI<10){
               schedule.add("0"+ startTimeI + ":00:00");
           }
           else {
               schedule.add(startTimeI + ":00:00");
           }
            startTimeI += 1;
       }

        return schedule;
   }

}
