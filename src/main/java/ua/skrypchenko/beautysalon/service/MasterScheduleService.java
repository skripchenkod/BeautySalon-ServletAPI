package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;
import ua.skrypchenko.beautysalon.dao.impl.MasterScheduleDaoImpl;
import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;

import java.sql.Date;
import java.util.*;


public class MasterScheduleService {
    MasterScheduleDao masterScheduleDao = new MasterScheduleDaoImpl();
    TimeSlotFactory timeSlotFactory = new TimeSlotFactory();

    public List<MastersScheduleDto> getScheduleForClient(String procedureName){
        return masterScheduleDao.getScheduleForClient(procedureName);
    }

    public List<MastersScheduleDto> getScheduleForMaster(String procedureName){
        return masterScheduleDao.getScheduleForMaster(procedureName);
    }

    public List<String> getBusyTimeSlots(String masterName, String date){
        Date date1 = Date.valueOf(date);
      return   masterScheduleDao.getBusyTimeSlots(masterName, date1);
    }

    public List<String> getFreeTimeSlot(String masterName, String date) {

        Date date1 = Date.valueOf(date);

        List<String> busySlotsWithDuration = masterScheduleDao.getBusyTimeSlotsWithDuration(masterName, date1);

        List<String> busySlotsStr = new ArrayList<>();
        for(int i = 0; i < busySlotsWithDuration.size(); i+=2){
                busySlotsStr.add(busySlotsWithDuration.get(i));
        }

        List<String> timeSlot = timeSlotFactory.getScheduleOfMaster(masterName,date1);

        for (String str : busySlotsStr ) {
            timeSlot.removeIf(str::equals);
        }

        return timeSlot;
    }
}
