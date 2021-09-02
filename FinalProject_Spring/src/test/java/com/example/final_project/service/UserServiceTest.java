package com.example.final_project.service;

import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test(expected = RuntimeException.class)
    public void findUserByIdWhenIdIsIncorrect() {
        userService.findUserById(-1L);
    }

    @Test(expected = RuntimeException.class)
    public void findUserByIdWhenIdIsNull() {
        userService.findUserById(null);
    }

    @Test
    public void findUserByIdWhenIdIsCorrect() {
        Assert.assertEquals(1L, userService.findUserById(1L).getIdUser());
    }

    @Test(expected = RuntimeException.class)
    public void pay() {
        userService.pay(new User(), new Receipt());
    }

    @Test(expected = RuntimeException.class)
    public void recharge() {
        userService.recharge(new User(),50);
    }
}