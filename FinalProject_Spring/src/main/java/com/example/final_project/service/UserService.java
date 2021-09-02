package com.example.final_project.service;

import com.example.final_project.entity.Receipt;
import com.example.final_project.entity.Role;
import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void saveUser(User user){
        user.setBalance(0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }
    @Transactional
    public Boolean pay(User user, Receipt receipt) {
        User userFromDB = userRepository.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
        if (userFromDB.getBalance() <= receipt.getPrice()) return false;

        userFromDB.setBalance(userFromDB.getBalance() - receipt.getPrice());
        userRepository.save(userFromDB);
        return true;
    }

    public void recharge(User user, int sum) {
        User userFromDB = userRepository.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
        userFromDB.setBalance(userFromDB.getBalance() + sum);
        userRepository.save(userFromDB);
    }
}