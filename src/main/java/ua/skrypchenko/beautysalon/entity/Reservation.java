package ua.skrypchenko.beautysalon.entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Reservation {

  private Integer id;

  private Date data;

  private Time start;

  private Time end;

  private Procedure procedure;

  private User beautyMaster;

  private User client;

  public Reservation(Integer id, Date data, Time start, Time end, Procedure procedure, User beautyMaster, User client) {
    this.id = id;
    this.data = data;
    this.start = start;
    this.end = end;
    this.procedure = procedure;
    this.beautyMaster = beautyMaster;
    this.client = client;
  }

  public Reservation(Time start, Time end) {
    this.start = start;
    this.end = end;
  }

  public Reservation(Integer id, Date data, Time start, Time end, Procedure procedure, User beautyMaster) {
    this.id = id;
    this.data = data;
    this.start = start;
    this.end = end;
    this.procedure = procedure;
    this.beautyMaster = beautyMaster;
  }

  public Reservation(Date data, Time start, User beautyMaster) {
    this.data = data;
    this.start = start;
    this.beautyMaster = beautyMaster;
  }

  public Reservation() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Time start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Time end) {
    this.end = end;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public Procedure getProcedure() {
    return procedure;
  }

  public void setProcedure(Procedure procedure) {
    this.procedure = procedure;
  }

  public User getBeautyMaster() {
    return beautyMaster;
  }

  public void setBeautyMaster(User beautyMaster) {
    this.beautyMaster = beautyMaster;
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Reservation)) return false;
    Reservation that = (Reservation) o;
    return
         Objects.equals(start, that.start)
        && Objects.equals(end, that.end)
        && procedure == that.procedure
        && Objects.equals(beautyMaster, that.beautyMaster)
        && Objects.equals(client, that.client);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end, procedure, beautyMaster, client);
  }

  @Override
  public String toString() {
    return "Reservation{" +
            "id=" + id +
            ", start=" + start +
            ", end=" + end +
            ", serviceOption=" + procedure +
            ", beautyMaster=" + beautyMaster +
            ", client=" + client +
            '}';
  }
}
