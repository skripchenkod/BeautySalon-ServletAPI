package ua.skrypchenko.beautysalon.dao;

import ua.skrypchenko.beautysalon.entity.Reservation;

import java.util.List;

public interface ReservationDao {

    List<Reservation> getAll();

    void setReservation(Reservation reservation);
    void deleteProcedure(Reservation reservation);
}
