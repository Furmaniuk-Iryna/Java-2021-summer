package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectionServiseTest {

    @Autowired
    private DirectionServise directionServise;


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