package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.ReceiptDao;
import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.DeliveryRequest;
import com.example.final_project.model.entity.Direction;
import com.example.final_project.model.entity.Receipt;
import com.example.final_project.model.service.DeliveryRequestService;
import com.example.final_project.model.service.DirectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public Receipt findById(long idRequest) {
     Receipt receipt = new Receipt();
     DeliveryRequestService deliveryRequestService=new DeliveryRequestService();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `receipt` where `request_id_delivery_request`=?");
            ps.setLong(1, idRequest);
            DeliveryRequest deliveryRequest = deliveryRequestService.getDeliveryRequestById(idRequest);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                receipt = new Receipt(
                        res.getLong("id"),
                        deliveryRequest,
                        res.getDouble("price"),
                        res.getString("status"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return receipt;
    }

    @Override
    public Receipt findByName(String name) {
        return null;
    }

    @Override
    public List<Receipt> findAll() {
        List<Receipt> receiptList= new ArrayList<>();
        DeliveryRequestService deliveryRequestService = new DeliveryRequestService();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `receipt`");
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                long id_delivery_request  = res.getLong("request_id_delivery_request");

                DeliveryRequest deliveryRequest = deliveryRequestService.getDeliveryRequestById(id_delivery_request);

                receiptList.add(new Receipt(
                        res.getLong("id"),
                        deliveryRequest,
                        res.getDouble("price"),
                        res.getString("status")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return receiptList;
    }

    @Override
    public void update(Receipt entity) {
        String sql = "UPDATE `receipt` SET `status` = ? WHERE (`id` = ?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setString(1,entity.getStatus());
            rs.setLong(2, entity.getId());
            rs.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
