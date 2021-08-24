package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.entity.DeliveryRequest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCDeliveryRequestDao implements DeliveryRequestDao {
    private Connection connection;


    public JDBCDeliveryRequestDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(DeliveryRequest entity) {
        String sql = "INSERT INTO `request` (`date_of_arrival`, `type_en`, `type_uk`, `volume`," +
                "`weight`,`address_id_address`,`users_id_user`,`tarif_id_tariff`)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setDate(1, Date.valueOf(entity.getDateOfArrival().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            rs.setString(2, entity.getType_en());
            rs.setString(3, entity.getType_uk());
            rs.setDouble(4, entity.getVolume());
            rs.setDouble(5, entity.getWeight());
            rs.setLong(6, entity.getAddress().getId());
            rs.setLong(7,entity.getUser().getIdUser());
            rs.setLong(7,entity.getTariff().getId());
            rs.executeUpdate();
//            rs.execute();
            rs.close();
        } catch (SQLException throwables) {
            System.out.println("------------SAVE ER");
            throwables.printStackTrace();

        }
    }

    @Override
    public DeliveryRequest findById(long id) {
        return null;
    }

    @Override
    public DeliveryRequest findByName(String name) {
        return null;
    }

    @Override
    public CopyOnWriteArrayList<DeliveryRequest> findAll() {
        return null;
    }

    @Override
    public void update(DeliveryRequest entity) {

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
