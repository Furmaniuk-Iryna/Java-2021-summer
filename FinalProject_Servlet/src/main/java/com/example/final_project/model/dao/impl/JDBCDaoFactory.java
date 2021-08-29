package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.*;

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
    public DirectionDao createDirectionDao() {
        return  new JDBCDirectionDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
      //  System.out.println("---createUserDao");
        return new JDBCUserDao(getConnection());
    }

    @Override
    public AddressDao createAddressDao() {
        return new JDBCAddressDao(getConnection());
    }

    @Override
    public ReceiptDao createReceiptDao() {
        return new JDBCReceiptDao(getConnection());
    }

    @Override
    public DeliveryRequestDao createDeliveryRequestDao() {
        return new JDBCDeliveryRequestDao(getConnection());
    }

    private Connection getConnection() {
        //TODO check
        try {
       //     System.out.println("----getConnection---" + dataSource.getConnection());
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
