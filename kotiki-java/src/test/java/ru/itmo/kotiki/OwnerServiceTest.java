package ru.itmo.kotiki;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itmo.kotiki.dao.OwnerDaoImpl;
import ru.itmo.kotiki.model.Owner;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {
    @Mock
    OwnerDaoImpl dao;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getOwnerById() throws SQLException {
        var owner1 = new Owner();
        owner1.setId(1);

        when(dao.findById(anyInt())).thenReturn(owner1);

        var owner2 = dao.findById(anyInt());

        assertEquals(owner1, owner2);
    }

    @Test
    public void getAllOwners() throws SQLException {
        var owner1 = new Owner();
        var owner2 = new Owner();
        owner1.setId(1);
        owner2.setId(2);
        List<Owner> owners1 = new ArrayList<>();
        owners1.add(owner1);
        owners1.add(owner2);

        when(dao.findAll()).thenReturn(owners1);

        var owners2 = dao.findAll();

        assertEquals(owners1, owners2);
    }

    @Test
    public void addNewOwner() {
        var owner = new Owner();
        owner.setName("Anna");
        owner.setBirthdayDate(new Date(System.currentTimeMillis()));

        dao.add(owner);

        verify(dao, times(1)).add(owner);
    }

    @Test
    public void updateOwner() {
        var owner = new Owner();
        owner.setName("Anna");
        owner.setBirthdayDate(new Date(System.currentTimeMillis()));

        dao.add(owner);
        dao.update(any());

        verify(dao, times(1)).update(any());
    }

    @Test
    public void deleteOwner() {
        var owner = new Owner();
        owner.setName("Anna");
        owner.setBirthdayDate(new Date(System.currentTimeMillis()));

        dao.add(owner);
        dao.delete(owner);

        verify(dao, times(1)).delete(owner);
    }
}
