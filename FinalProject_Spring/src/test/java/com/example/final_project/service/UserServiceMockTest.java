package com.example.final_project.service;

import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.Role;
import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test()
    public void findUserById() {
        Optional<User> user = Optional.of(new User());
        user.get().setIdUser(110L);
        user.get().setUsername("username");
        Mockito.when(userRepository.findById(110L)).thenReturn(user);
        User result = userService.findUserById(110L);
        Assert.assertEquals(result.getUsername(), user.get().getUsername());
    }

    @Test(expected = RuntimeException.class)
    public void findUserByIdIfEmpty() {
        Optional<User> user = Optional.empty();
        Mockito.when(userRepository.findById(110L)).thenReturn(user);
        userService.findUserById(110L);
    }

    @Test
    public void loadUserByUsername() {
        Optional<User> user = Optional.of(new User());
        user.get().setUsername("username");
        Mockito.when(userRepository.findByUsername("username")).thenReturn(user);
        UserDetails result = userService.loadUserByUsername("username");
        Assert.assertEquals("username", result.getUsername());
    }

    @Test
    public void saveUser() {
        User user = new User();
        userService.saveUser(user);
        user.setBalance(0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void pay() {
        User user = User.builder().idUser(1L).username("username").password("12345").name("name").surname("surname").balance(0.0).active(true).roles(null).build();
        user.setBalance(1000);
        Receipt receipt = new Receipt();
        receipt.setPrice(900);
        Mockito.when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        boolean result = userService.pay(user, receipt);
        user.setBalance(100);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Assert.assertTrue(result);
    }

    @Test
    public void recharge() {
        User user = User.builder().idUser(1L).username("username").password("12345").name("name").surname("surname").balance(0.0).active(true).roles(null).build();
        user.setBalance(1000);
        Mockito.when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        userService.recharge(user, 1000);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}