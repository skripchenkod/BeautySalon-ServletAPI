package ua.skrypchenko.beautysalon.dto;

import ua.skrypchenko.beautysalon.entity.User;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;

public class MastersScheduleDto {

    private Date workDay;
    private Time startWorkDay;
    private Time endWorkDay;
    private User masterId;

    public MastersScheduleDto(Date workDay, Time startWorkDay, Time endWorkDay, User masterId) {
        this.workDay = workDay;
        this.startWorkDay = startWorkDay;
        this.endWorkDay = endWorkDay;
        this.masterId = masterId;
    }

    public MastersScheduleDto(Date workDay) {
        this.workDay = workDay;
    }

    public Date getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Date workDay) {
        this.workDay = workDay;
    }

    public Time getStartWorkDay() {
        return startWorkDay;
    }

    public void setStartWorkDay(Time startWorkDay) {
        this.startWorkDay = startWorkDay;
    }

    public Time getEndWorkDay() {
        return endWorkDay;
    }

    public void setEndWorkDay(Time endWorkDay) {
        this.endWorkDay = endWorkDay;
    }

    public User getMasterId() {
        return masterId;
    }

    public void setMasterId(User masterId) {
        this.masterId = masterId;
    }

    @Override
    public String toString() {
        return
               masterId + " " + workDay + " " + startWorkDay + " " + endWorkDay;
    }
}
