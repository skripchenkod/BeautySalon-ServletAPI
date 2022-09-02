package ua.skrypchenko.beautysalon.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skrypchenko.beautysalon.dao.ReservationDao;
import ua.skrypchenko.beautysalon.entity.Reservation;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {
    @Rule
    public ExpectedException exception;
    @InjectMocks
    ReservationService service;
    @Mock
    ReservationDao reservationDao;

    public void getClientName (Reservation reservation){

    }
}
