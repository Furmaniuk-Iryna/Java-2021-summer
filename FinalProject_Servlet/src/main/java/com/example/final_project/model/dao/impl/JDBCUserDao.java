package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.UserDao;
import com.example.final_project.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCUserDao implements UserDao {
    private Connection connection;


    public JDBCUserDao(Connection connection) {
     //   System.out.println(" JDBCUserDao constr");
        this.connection = connection;
    }


    @Override
    public void save(User entity) {
        String sql = "INSERT INTO `users` (`balance`, `name`, `surname`, `username`, `password`,`role`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setDouble(1, entity.getBalance());
            rs.setString(2, entity.getName());
            rs.setString(3, entity.getSurname());
            rs.setString(4, entity.getUsername());
            rs.setString(5, entity.getPassword());
            rs.setString(6, entity.getRole());
            rs.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    @Override
    public User findById(long id) {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `users` where `id`=?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                user = new User(
                        res.getLong("id"),
                        res.getDouble("balance"),
                        res.getString("name"),
                        res.getString("surname"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByName(String username) {
        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `users` where `username`=?");
            ps.setString(1,username);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                user = new User(
                        res.getLong("id"),
                        res.getDouble("balance"),
                        res.getString("name"),
                        res.getString("surname"),
                        res.getString("username"),
                        res.getString("password"),
                        res.getString("role"));
            }
        } catch (SQLException ex) {
           // System.out.println("---FIND BY NAME EXCEPTION");
            ex.printStackTrace();

        }
        return user;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
