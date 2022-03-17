package ru.itmo.kotiki;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itmo.kotiki.dao.KotikDaoImpl;
import ru.itmo.kotiki.enums.Color;
import ru.itmo.kotiki.model.Kotik;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class KotikServiceTest {
    @Mock
    KotikDaoImpl dao;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getKotikById() throws SQLException {
        var kotik1 = new Kotik();
        kotik1.setId(1);

        when(dao.findById(anyInt())).thenReturn(kotik1);

        var kotik2 = dao.findById(anyInt());

        assertEquals(kotik1, kotik2);
    }

    @Test
    public void getAllKotiki() throws SQLException {
        var kotik1 = new Kotik();
        var kotik2 = new Kotik();
        kotik1.setId(1);
        kotik2.setId(2);
        List<Kotik> kotiki1 = new ArrayList<>();
        kotiki1.add(kotik1);
        kotiki1.add(kotik2);

        when(dao.findAll()).thenReturn(kotiki1);

        var kotiki2 = dao.findAll();

        assertEquals(kotiki1, kotiki2);
    }

    @Test
    public void addNewKotik() {
        var kotik = new Kotik();
        kotik.setName("Bob");
        kotik.setBreed("British");
        kotik.setColor(Color.BROWN);
        kotik.setBirthdayDate(new Date(System.currentTimeMillis()));

        dao.add(kotik);

        verify(dao, times(1)).add(kotik);
    }

    @Test
    public void updateKotik() {
        var kotik = new Kotik();
        kotik.setName("Bob");
        kotik.setBreed("British");
        kotik.setColor(Color.BROWN);
        kotik.setBirthdayDate(new Date(System.currentTimeMillis()));

        dao.add(kotik);
        dao.update(any());

        verify(dao, times(1)).update(any());
    }

    @Test
    public void deleteKotik() {
        var kotik = new Kotik();
        kotik.setName("Bob");
        kotik.setBreed("British");
        kotik.setColor(Color.BROWN);
        kotik.setBirthdayDate(new Date(System.currentTimeMillis()));

        dao.add(kotik);
        dao.delete(kotik);

        verify(dao, times(1)).delete(kotik);
    }
}
