package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.TariffDao;
import com.example.final_project.model.entity.Direction;
import com.example.final_project.model.entity.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCTariffDao implements TariffDao {
    private Connection connection;


    public JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(Tariff entity) {

    }

    @Override
    public Tariff findById(long id) {
        Tariff tariff= new Tariff();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `tariff` where `id_tariff`=?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                tariff = new Tariff(res.getLong("id_tariff"),
                        res.getString("description_en"),
                        res.getString("description_uk"),
                        res.getDouble("for_volume"),
                        res.getDouble("for_weight"),
                        res.getString("name_en"),
                        res.getString("name_uk"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tariff;
    }

    @Override
    public Tariff findByName(String name) {
        return null;
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> tariffList = new CopyOnWriteArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `tariff`");
            ResultSet res = ps.executeQuery();
            while (res.next()) {

                tariffList.add(new Tariff(res.getLong("id_tariff"),
                        res.getString("description_en"),
                        res.getString("description_uk"),
                        res.getDouble("for_volume"),
                        res.getDouble("for_weight"),
                        res.getString("name_en"),
                        res.getString("name_uk")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tariffList;
    }

    @Override
    public void update(Tariff entity) {

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
