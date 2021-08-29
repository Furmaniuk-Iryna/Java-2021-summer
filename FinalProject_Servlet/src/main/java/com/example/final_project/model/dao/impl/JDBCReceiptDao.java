package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.ReceiptDao;
import com.example.final_project.model.entity.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCReceiptDao implements ReceiptDao {
    private Connection connection;

    public JDBCReceiptDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Receipt entity) {
        String sql = "INSERT INTO `receipt` ( `price`, `status`, `request_id_delivery_request`) VALUES (?,?,?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setDouble(1, entity.getPrice());
            rs.setString(2, entity.getStatus());
            rs.setLong(3, entity.getDeliveryRequest().getId());
            rs.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Receipt findById(long id) {
        return null;
    }

    @Override
    public Receipt findByName(String name) {
        return null;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public void update(Receipt entity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void close() {

    }
}
