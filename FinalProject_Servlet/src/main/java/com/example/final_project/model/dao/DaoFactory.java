package com.example.final_project.model.dao;

import com.example.final_project.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract AddressDao createAddressDao();
    public abstract TariffDao createTariffDao();
    public abstract DirectionDao createDirectionDao();
    public abstract DeliveryRequestDao createDeliveryRequestDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
