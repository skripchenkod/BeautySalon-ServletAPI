package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;
import ua.skrypchenko.beautysalon.dao.impl.MasterScheduleDaoImpl;
import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.MasterSchedule;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.exeption.NoFreeDateException;
import ua.skrypchenko.beautysalon.exeption.NoFreeTimeSlotsException;
import ua.skrypchenko.beautysalon.validator.DateValidator;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class MasterScheduleService {
    private final MasterScheduleDao masterScheduleDao = new MasterScheduleDaoImpl();
    private final TimeSlotFactory timeSlotFactory = new TimeSlotFactory();
    private final DateValidator dateValidator = new DateValidator();

    public List<MastersScheduleDto> getScheduleForClient(String procedureName){
        List<MastersScheduleDto> scheduleDtos = masterScheduleDao.getScheduleForClient(procedureName);
        if(!scheduleDtos.isEmpty()){
            return scheduleDtos;
        }
        else{
            throw new NoFreeDateException("No free days to sign up");
        }
    }

    public void setMasterSchedule(String workDay, String startTime, String endTime, String masterName){
        dateValidator.validator(workDay, startTime, endTime);
        System.out.println( workDay + " " + startTime + " " + endTime);
        MasterSchedule masterSchedule = new MasterSchedule(Date.valueOf(workDay), Time.valueOf(startTime+":00"), Time.valueOf(endTime+":00"), new User(masterName));
        masterScheduleDao.setMasterSchedule(masterSchedule);
    }

    public List<MastersScheduleDto> getScheduleForMaster(String masterName){
        return masterScheduleDao.getScheduleForMaster(masterName);
    }

    public List<String> getBusyTimeSlots(String masterName, String date){
        Date date1 = Date.valueOf(date);
      return   masterScheduleDao.getBusyTimeSlots(masterName, date1);
    }

    public List<String> getFreeTimeSlot(String masterName, String date) {

        Date date1 = Date.valueOf(date);

        List<String> busySlotsWithDuration = masterScheduleDao.getBusyTimeSlotsWithDuration(masterName, date1);

        List<String> busySlotsStr = new ArrayList<>();
        for (int i = 0; i < busySlotsWithDuration.size(); i += 2) {
            busySlotsStr.add(busySlotsWithDuration.get(i));
        }

        List<String> timeSlot = timeSlotFactory.getScheduleOfMaster(masterName, date1);

        for (String str : busySlotsStr) {
            timeSlot.removeIf(str::equals);
        }

        if (!timeSlot.isEmpty()) {
            return timeSlot;
        } else {
            throw new NoFreeTimeSlotsException("No free days to sign up, choose another day");
        }
    }
}
