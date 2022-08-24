package ua.skrypchenko.beautysalon.entity;

import java.util.Objects;

public class Procedure {
  private  Integer id;
  private String name;
  private String description;
  private int durationHours;
  

  public Procedure(Integer id, String name, String description, int durationHours) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.durationHours = durationHours;
  }

  public Procedure(String name) {
    this.name = name;
  }

  public Procedure() {
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDurationHours() {
    return durationHours;
  }

  public void setDurationHours(int durationHours) {
    this.durationHours = durationHours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Procedure)) return false;
    Procedure procedure = (Procedure) o;
    return durationHours == procedure.durationHours &&
            Objects.equals(name, procedure.name) &&
            Objects.equals(description, procedure.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash( name, description, durationHours);
  }

  @Override
  public String toString() {
    return "Procedure{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", durationHours=" + durationHours +
            '}';
  }
}
