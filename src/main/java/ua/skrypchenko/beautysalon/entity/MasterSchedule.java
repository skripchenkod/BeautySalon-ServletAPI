package ua.skrypchenko.beautysalon.entity;

import java.util.Date;
import java.sql.Time;
import java.util.Objects;

public class MasterSchedule {
    private int id;
    private Date workDay;
    private Time startWorkDay;
    private Time endWorkDay;
    private User masterId;

    public MasterSchedule(Date workDay, Time startWorkDay, Time endWorkDay, User masterId) {
        this.workDay = workDay;
        this.startWorkDay = startWorkDay;
        this.endWorkDay = endWorkDay;
        this.masterId = masterId;
    }

    public MasterSchedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterSchedule that = (MasterSchedule) o;
        return id == that.id && Objects.equals(workDay, that.workDay) && Objects.equals(startWorkDay, that.startWorkDay) && Objects.equals(endWorkDay, that.endWorkDay) && Objects.equals(masterId, that.masterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workDay, startWorkDay, endWorkDay, masterId);
    }

    @Override
    public String toString() {
        return "MasterSchedule{" +
                "id=" + id +
                ", workDay=" + workDay +
                ", startWorkDay=" + startWorkDay +
                ", endWorkDay=" + endWorkDay +
                ", masterId=" + masterId +
                '}';
    }
}
