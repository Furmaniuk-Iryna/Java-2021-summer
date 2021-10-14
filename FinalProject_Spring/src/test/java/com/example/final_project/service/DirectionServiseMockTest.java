package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import com.example.final_project.entity.Tariff;
import com.example.final_project.repository.DirectionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class DirectionServiseMockTest {
    @Mock
    DirectionRepository directionRepository;
    @InjectMocks
    DirectionServise directionServise;

    @Test
    public void sortedDirectionsForUkLocale() {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(new Direction(1L,"Odessa","Одеса",490.0));
        directionList.add(new Direction(2L,"Lviv","Львів",510.0));
        Mockito.when(directionRepository.findAll()).thenReturn(directionList);
        ArrayList<Direction> result =(ArrayList<Direction>) directionServise.sortedDirectionsForUkLocale();
        Assert.assertEquals(2L, result.get(0).getId());
        Assert.assertEquals(1L, result.get(1).getId());
    }

    @Test
    public void sortedDirectionsForEnLocale() {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(new Direction(1L,"Odessa","Одеса",490.0));
        directionList.add(new Direction(2L,"Lviv","Львів",510.0));
        Mockito.when(directionRepository.findAll()).thenReturn(directionList);
        List<Direction> result = directionServise.sortedDirectionsForEnLocale();
        Assert.assertEquals(2L, result.get(0).getId());
        Assert.assertEquals(1L, result.get(1).getId());
    }

    @Test
    public void getNeededDirection() {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(new Direction(1L,"Odessa","Одеса",490.0));
        directionList.add(new Direction(2L,"Lviv","Львів",510.0));
        Mockito.when(directionRepository.findAll()).thenReturn(directionList);
        Direction result = directionServise.getNeededDirection("Lviv");
        Assert.assertEquals(2L, result.getId());
        Assert.assertThat(result.getCityUk(), is("Львів"));

    }

    @Test
    public void findDirectionsLike() {
        List<Direction> directionList = new ArrayList<>();
        directionList.add(new Direction(1L,"Odessa","Одеса",490.0));
        directionList.add(new Direction(2L,"Lviv","Львів",510.0));
        Mockito.when(directionRepository.findAll()).thenReturn(directionList);
        List<Direction> result = directionServise.findDirectionsLike("");
        Assert.assertEquals(2,result.size());
    }
}