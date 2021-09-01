package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.AddressDao;
import com.example.final_project.model.entity.Address;
import com.example.final_project.model.entity.Direction;
import com.example.final_project.model.entity.Tariff;
import com.example.final_project.model.service.DirectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCAddressDao implements AddressDao {
    private Connection connection;

    public JDBCAddressDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Address entity) {

    }

    @Override
    public Address findById(long id) {
      Address address = new Address();
        DirectionService directionService = new DirectionService();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `address` where `id_address`=?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                long id_direction  = res.getLong("direction_id_direction");

                Direction direction = directionService.getDirectionById(id_direction);

                address = new Address(
                        res.getLong("id_address"),
                        res.getString("address_en"),
                        res.getString("address_uk"),
                        direction);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return address;
    }

    @Override
    public Address findByName(String name) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addressList = new CopyOnWriteArrayList<>();
        DirectionService directionService = new DirectionService();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `address`");
            ResultSet res = ps.executeQuery();

            while (res.next()) {
           long id_direction  = res.getLong("direction_id_direction");

                Direction direction = directionService.getDirectionById(id_direction);

                addressList.add(new Address(
                        res.getLong("id_address"),
                        res.getString("address_en"),
                        res.getString("address_uk"),
                        direction));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return addressList;
    }

    @Override
    public void update(Address entity) {

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
