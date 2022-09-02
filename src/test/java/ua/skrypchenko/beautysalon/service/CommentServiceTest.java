package ua.skrypchenko.beautysalon.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.skrypchenko.beautysalon.dao.CommentDao;
import ua.skrypchenko.beautysalon.dao.MasterScheduleDao;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
    @Rule
    public ExpectedException exception;
    @InjectMocks
    CommentService service;
    @Mock
    CommentDao commentDao;
}
