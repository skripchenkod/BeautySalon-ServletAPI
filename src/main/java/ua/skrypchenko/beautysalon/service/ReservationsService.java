package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.ReservationDao;
import ua.skrypchenko.beautysalon.dao.impl.ReservationImpl;
import ua.skrypchenko.beautysalon.entity.Reservation;

public class ReservationsService {
    ReservationDao reservationDao = new ReservationImpl();

    public void deleteProcedure(Reservation reservation) {
        reservationDao.deleteProcedure(reservation);
    }
}
