package ua.skrypchenko.beautysalon.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skrypchenko.beautysalon.dao.ProcedureDao;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.entity.Procedure;
import ua.skrypchenko.beautysalon.mapper.ProcedureMapper;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcedureServiceTest {
    @Rule
    public ExpectedException exception;
    @InjectMocks
    ProcedureService service;
    @Mock
    ProcedureDao procedureDao;



    @Test
    public void getAll(){

        Procedure procedure = new Procedure("full", "f", 1);

        when(procedureDao.getAll()).thenReturn(Collections.singletonList(procedure));

        List<ProcedureDto> actual = service.getAll();

        verify(procedureDao, times(1)).getAll();

        Assert.assertThat(actual, is(not(Collections.emptyList())));

    }
}
