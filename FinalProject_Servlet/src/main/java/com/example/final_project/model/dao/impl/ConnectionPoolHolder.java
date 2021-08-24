package com.example.final_project.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){
        System.out.println("DATA --------------------------");
        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/cargo_delivery_db?serverTimezone=UTC");
                    ds.setUsername("root");
                    ds.setPassword("rootroot");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                    System.out.println("------DB______________");
                }
            }
        }
        return dataSource;
    }
}
