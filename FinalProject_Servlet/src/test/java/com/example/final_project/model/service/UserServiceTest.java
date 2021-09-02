package com.example.final_project.model.service;

import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTest {
    private final UserService userService= new UserService();

    @Test
    public void findUserByIdWhenIdIsIncorrect() {
        Assert.assertEquals(0, userService.getUserById(-1L).getIdUser());
    }

    @Test
    public void findUserByIdWhenIdIsCorrect() {
        Assert.assertEquals(1L, userService.getUserById(1L).getIdUser());
    }

    @Test
    public void pay() {
       Assert.assertFalse(userService.pay(new User(), new Receipt()));
    }
}