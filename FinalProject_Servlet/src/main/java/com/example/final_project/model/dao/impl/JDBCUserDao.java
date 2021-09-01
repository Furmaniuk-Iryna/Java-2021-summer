package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.UserDao;
import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCUserDao implements UserDao {
    private Connection connection;


    public JDBCUserDao(Connection connection) {
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
            throw new RuntimeException(throwables);

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
            throw new RuntimeException(ex);
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
            throw new RuntimeException(ex);

        }
        return user;
    }

    @Override
    public CopyOnWriteArrayList<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE `users` SET `balance` = ? WHERE (`id` = ?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setDouble(1,entity.getBalance());
            rs.setLong(2, entity.getIdUser());
            rs.execute();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);        }
    }

    public boolean pay(User user, Receipt receipt){
        try {
            connection.setAutoCommit(false);
            if (findByName(user.getUsername()).getBalance() <= receipt.getPrice()) return false;

            User userFromDB = findByName(user.getUsername());
            userFromDB.setBalance(userFromDB.getBalance() - receipt.getPrice());
            update(userFromDB);
            connection.commit();
            return true;
        } catch (Exception e) {
            try {
                connection.rollback();
            }catch (SQLException e1){}
            return false;
        }
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
