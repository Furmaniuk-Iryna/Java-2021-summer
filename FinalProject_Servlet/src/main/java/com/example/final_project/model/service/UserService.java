package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.ReceiptDao;
import com.example.final_project.model.dao.UserDao;
import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;

import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public User getUserByUsername(String username){
        try (UserDao dao = daoFactory.createUserDao()) {
          //  System.out.println("FIND BY NAME ---"+dao.findByName(username));
            return Optional.ofNullable(dao.findByName(username)).orElse(new User());
        }
    }

    public void saveUser(User user){
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.save(user);
        }
    }
    public User getUserById(long id){
        try (UserDao dao = daoFactory.createUserDao()) {
            return Optional.ofNullable(dao.findById(id)).orElse(new User());
        }
    }

    public void recharge(User user, int sum){
        try (UserDao dao = daoFactory.createUserDao()) {
            User userFromDB = Optional.ofNullable(dao.findByName(user.getUsername())).orElseThrow(()->new RuntimeException("user not found"));
            userFromDB.setBalance(userFromDB.getBalance() + sum);
            dao.update(userFromDB);
        }
    }

    //TODO @Transactional
    public Boolean pay(User user, Receipt receipt) {
        try (UserDao dao = daoFactory.createUserDao()) {
            if (dao.findByName(user.getUsername()).getBalance() <= receipt.getPrice()) return false;

            User userFromDB = dao.findByName(user.getUsername());
            userFromDB.setBalance(userFromDB.getBalance() - receipt.getPrice());
            dao.update(userFromDB);
            return true;
        }}


}
