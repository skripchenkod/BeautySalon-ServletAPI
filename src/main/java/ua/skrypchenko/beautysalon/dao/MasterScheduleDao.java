package ua.skrypchenko.beautysalon.dao;

import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.MasterSchedule;
import ua.skrypchenko.beautysalon.entity.Reservation;

import java.sql.Time;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

public interface MasterScheduleDao {
    List<MastersScheduleDto> getScheduleForClient(String procedureName);
    List<Time> getScheduleForBusyTimeSlots(int master);
    List<String> getScheduleForFreeTimeSlots(String masterName, Date date);
    List<MastersScheduleDto> getScheduleForMaster(String masterName);
    List<String> getBusyTimeSlotsWithDuration(String masterName, Date date);
    List<String> getBusyTimeSlots(String masterName, Date date);

}
