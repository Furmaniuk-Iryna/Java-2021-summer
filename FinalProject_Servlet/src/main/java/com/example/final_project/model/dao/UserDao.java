package com.example.final_project.model.dao;

import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;

public interface UserDao extends GenericDao<User>{
    boolean pay(User user, Receipt receipt);
}
