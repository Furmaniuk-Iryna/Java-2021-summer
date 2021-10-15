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

/**
 * UserService is a service we'll be using to form response and
 * where there is all the business logic for the essence User
 * <p>
 * Please see the {@link org.springframework.security.core.userdetails.UserDetailsService} class for true identity
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long userId) {
        log.info("Service: Fetching user with id {}", userId);
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Service: Fetching user with username {}", username);
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public void saveUser(User user) {
        log.info("Service: Save user, id - {}", user.getIdUser());
        user.setBalance(0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

    @Transactional
    public Boolean pay(User user, Receipt receipt) {
        log.info("Service: Transaction with user id {}, receipt {}", user.getIdUser(), receipt);
        User userFromDB = userRepository.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
        if (userFromDB.getBalance() <= receipt.getPrice()) return false;

        userFromDB.setBalance(userFromDB.getBalance() - receipt.getPrice());
        userRepository.save(userFromDB);
        return true;
    }

    public void recharge(User user, int sum) {
        log.info("Service: Recharge user id - {}, sum - {}", user.getIdUser(), sum);
        User userFromDB = userRepository.findByUsername(user.getUsername()).orElseThrow(RuntimeException::new);
        userFromDB.setBalance(userFromDB.getBalance() + sum);
        userRepository.save(userFromDB);
    }
}