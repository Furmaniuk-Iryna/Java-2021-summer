package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.UserDao;
import com.example.final_project.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public User getUserByUsername(String username){
        try (UserDao dao = daoFactory.createUserDao()) {
            System.out.println("FIND BY NAME ---"+dao.findByName(username));
            return Optional.ofNullable(dao.findByName(username)).orElse(new User());
        }
    }
    public void saveUser(User user){
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.save(user);
        }
    }
}
