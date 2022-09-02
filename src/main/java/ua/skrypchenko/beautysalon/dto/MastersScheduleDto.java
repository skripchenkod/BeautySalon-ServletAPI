package ua.skrypchenko.beautysalon.dto;

import ua.skrypchenko.beautysalon.entity.User;

import java.sql.Date;
import java.sql.Time;

public class MastersScheduleDto {

    private Date workDay;
    private Time startWorkDay;
    private Time endWorkDay;
    private User master;

    public MastersScheduleDto(Date workDay, Time startWorkDay, Time endWorkDay, User masterId) {
        this.workDay = workDay;
        this.startWorkDay = startWorkDay;
        this.endWorkDay = endWorkDay;
        this.master = masterId;
    }

    public MastersScheduleDto(Date workDay) {
        this.workDay = workDay;
    }

    public MastersScheduleDto(Date workDay, Time startWorkDay, Time endWorkDay) {
        this.workDay = workDay;
        this.startWorkDay = startWorkDay;
        this.endWorkDay = endWorkDay;
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
        return master;
    }

    public void setMasterId(User masterId) {
        this.master = masterId;
    }

    @Override
    public String toString() {
        return
               master + " " + workDay + " " + startWorkDay + " " + endWorkDay;
    }
}
