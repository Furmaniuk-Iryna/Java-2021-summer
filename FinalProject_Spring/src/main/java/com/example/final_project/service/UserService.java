package com.example.final_project.service;

import com.example.final_project.entity.User;
import com.example.final_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        //TODO optional
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


//    public boolean deleteUser(Long userId) {
//        if (userRepository.findById(userId).isPresent()) {
//            userRepository.deleteById(userId);
//            return true;
//        }
//        return false;
//    }
//    public boolean saveUser(User user) {
//        User userFromDB = userRepository.findByUsername(user.getUsername());
//        if (userFromDB != null) {
//            return false;
//        }
//        user.setRoles(Role.USER);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
}