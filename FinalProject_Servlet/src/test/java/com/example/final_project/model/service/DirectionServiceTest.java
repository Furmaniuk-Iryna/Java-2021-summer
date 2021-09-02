package com.example.final_project.model.service;

import com.example.final_project.model.entity.Direction;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionServiceTest {

    private final DirectionService directionServise = new DirectionService();


    @Test
    public void sortedDirectionsForUkLocale() {
        Assert.assertNotNull(directionServise.sortedDirectionsForUkLocale());
    }

    @Test
    public void sortedDirectionsForEnLocale() {
        Assert.assertNotNull(directionServise.sortedDirectionsForEnLocale());
    }

    @Test
    public void getNeededDirectionWithCorrectCity() {
        Direction direction = directionServise.getNeededDirection("Lviv");
        Assert.assertEquals("Lviv", direction.getCityEn());
    }

    @Test(expected = RuntimeException.class)
    public void getNeededDirectionWithNullSity() {
        directionServise.getNeededDirection(null);

    }

    @Test(expected = RuntimeException.class)
    public void getNeededDirectionWithEmptySity() {
        directionServise.getNeededDirection("");
    }

    @Test(expected = RuntimeException.class)
    public void getNeededDirectionWithIncorrectSity() {
        directionServise.getNeededDirection("Lvv");
    }
}