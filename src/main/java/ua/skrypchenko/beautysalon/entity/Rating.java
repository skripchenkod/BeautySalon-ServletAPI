package ua.skrypchenko.beautysalon.entity;

import java.util.Objects;

public class Rating {

  private final Integer id;

  private User master;

  private Double ratingMark;

  public Rating(Integer id, User master, Double ratingMark) {
    this.id = id;
    this.master = master;
    this.ratingMark = ratingMark;
  }

  public Integer getId() {
    return id;
  }

  public User getMaster() {
    return master;
  }

  public void setMaster(User master) {
    this.master = master;
  }

  public Double getRatingMark() {
    return ratingMark;
  }

  public void setRatingMark(Double ratingMark) {
    this.ratingMark = ratingMark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Rating)) return false;
    Rating rating1 = (Rating) o;
    return Objects.equals(master, rating1.master)
        && Objects.equals(ratingMark, rating1.ratingMark);
  }

  @Override
  public int hashCode() {
    return Objects.hash( master, ratingMark);
  }

  @Override
  public String toString() {
    return "Rating{" +
            "id=" + id +
            ", master=" + master +
            ", ratingMark=" + ratingMark +
            '}';
  }
}
