package ua.skrypchenko.beautysalon.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;
import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.Rating;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MasterScheduleServiceTest {
    private TimeSlotFactory timeSlotFactory = new TimeSlotFactory();
    @Rule
    public ExpectedException exception;
    @InjectMocks
    MasterScheduleService masterScheduleService;
    @Mock
    MasterScheduleDao masterScheduleDao;

    @Test
    public void getFreeTimeSlot() {

        List<String> busySlots = new ArrayList<>(Arrays.asList("15:00:00", "1"));
        List<String> freeSlots = new ArrayList<>(Arrays.asList("14:00:00", "15:00:00", "16:00:00"));

        when(masterScheduleDao.getBusyTimeSlotsWithDuration("ira", Date.valueOf("2022-12-12"))).thenReturn(busySlots);
        when(masterScheduleDao.getScheduleForFreeTimeSlots("ira", Date.valueOf("2022-12-12"))).thenReturn(freeSlots);

        verify(masterScheduleDao, times(1)).getScheduleForFreeTimeSlots("ira", Date.valueOf("2022-12-12"));

        List<String> actual = masterScheduleService.getFreeTimeSlot("ira", "2022-12-12");

        verify(masterScheduleDao, times(1)).getBusyTimeSlotsWithDuration("ira", Date.valueOf("2022-12-12"));


        Assert.assertThat(actual, is(new ArrayList<>(Arrays.asList("14:00:00", "16:00:00"))));
    }

    @Test
    public void getScheduleForMaster() {
        String masterName = "ira";

        MastersScheduleDto mastersScheduleDto = new MastersScheduleDto(Date.valueOf("2022-09-01"), Time.valueOf("09:00:00"), Time.valueOf("17:00:00"));
        List<MastersScheduleDto> masterScheduleDtos = new ArrayList<>();
        masterScheduleDtos.add(mastersScheduleDto);

        when(masterScheduleDao.getScheduleForMaster(masterName)).thenReturn(masterScheduleDtos);

        List<MastersScheduleDto> actual = masterScheduleService.getScheduleForMaster(masterName);

        Assert.assertThat(actual, is(masterScheduleDtos));
    }


}
