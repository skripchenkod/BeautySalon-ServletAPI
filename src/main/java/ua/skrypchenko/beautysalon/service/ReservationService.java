package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.ReservationDao;
import ua.skrypchenko.beautysalon.dao.impl.ReservationImpl;
import ua.skrypchenko.beautysalon.entity.Reservation;

import java.util.List;

public class ReservationService {

    private final ReservationDao reservationDao = new ReservationImpl();

    public void setReservation(Reservation reservation) {
        reservationDao.setReservation(reservation);
    }

    public void deleteProcedure(Reservation reservation) {
        reservationDao.deleteProcedure(reservation);
    }

    public List<Reservation> getReservationByClient(String clientName) {
        return reservationDao.getReservationByClient(clientName);
    }

    public void updateReservation(Reservation reservation) {
        reservationDao.updateReservation(reservation);
    }

    public void deleteReservation(int reservationId) {
        reservationDao.deleteReservation(reservationId);
    }


}
