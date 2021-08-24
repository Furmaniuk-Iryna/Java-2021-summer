package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.dao.TariffDao;
import com.example.final_project.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public TariffDao createTariffDao() {
        return new JDBCTariffDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public DeliveryRequestDao createDeliveryRequestDao() {
        return new JDBCDeliveryRequestDao(getConnection());
    }

    private Connection getConnection() {
        try {
            System.out.println("---------CONN" + dataSource.getConnection());
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("-----JDBCDaoFact");
            throw new RuntimeException(e);
        }
    }
}
