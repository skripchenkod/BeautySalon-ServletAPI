package ua.skrypchenko.beautysalon.dto;

import ua.skrypchenko.beautysalon.entity.User;

public class RatingDto {

    private User master;
    private Double ratingMark;

    public RatingDto(User master, Double ratingMark) {
        this.master = master;
        this.ratingMark = ratingMark;
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
}
