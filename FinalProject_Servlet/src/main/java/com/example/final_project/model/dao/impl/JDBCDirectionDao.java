package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.DirectionDao;
import com.example.final_project.model.entity.Direction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JDBCDirectionDao implements DirectionDao {
    private Connection connection;

    public JDBCDirectionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Direction entity) {

    }

    @Override
    public Direction findById(long id) {
        Direction direction = new Direction();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `direction` where `id_direction`=?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                direction = new Direction(
                        res.getLong("id_direction"),
                        res.getString("city_en"),
                        res.getString("city_uk"),
                        res.getDouble("distance"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return direction;
    }

    @Override
    public Direction findByName(String name) {
        return null;
    }

    @Override
    public List<Direction> findAll() {
        List<Direction> directionList = new CopyOnWriteArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `direction`");
            ResultSet res = ps.executeQuery();
            while (res.next()) {

                directionList.add(new Direction(res.getLong("id_direction"),
                        res.getString("city_en"),
                        res.getString("city_uk"),
                        res.getDouble("distance")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return directionList;
    }

    @Override
    public void update(Direction entity) {

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
