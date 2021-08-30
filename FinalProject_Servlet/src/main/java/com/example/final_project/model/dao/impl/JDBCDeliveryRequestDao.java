package com.example.final_project.model.dao.impl;

import com.example.final_project.model.dao.DeliveryRequestDao;
import com.example.final_project.model.entity.*;
import com.example.final_project.model.service.AddressService;
import com.example.final_project.model.service.DirectionService;
import com.example.final_project.model.service.TariffService;
import com.example.final_project.model.service.UserService;

import java.sql.*;
import java.time.LocalDate;
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
                "`weight`,`address_id_address`,`users_id_user`,`tariff_id_tariff`)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement rs = connection.prepareStatement(sql);
            rs.setDate(1, Date.valueOf(entity.getDateOfArrival()));
            rs.setString(2, entity.getType_en());
            rs.setString(3, entity.getType_uk());
            rs.setDouble(4, entity.getVolume());
            rs.setDouble(5, entity.getWeight());
            rs.setLong(6, entity.getAddress().getId());
            rs.setLong(7,entity.getUser().getIdUser());
            rs.setLong(8,entity.getTariff().getId());
            rs.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }

    @Override
    public DeliveryRequest findById(long id) {
       DeliveryRequest deliveryRequest = new DeliveryRequest();
        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        TariffService tariffService = new TariffService();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from `request` where `id_delivery_request`=?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                long id_user = res.getLong("users_id_user");
                long id_address = res.getLong("address_id_address");
                long id_tariff = res.getLong("tariff_id_tariff");
                User user = userService.getUserById(id_user);
                Address address = addressService.getAddressById(id_address);
                Tariff tariff = tariffService.findTariffById(id_tariff);
              deliveryRequest =  new DeliveryRequest(
                        res.getLong("id_delivery_request"),
                        res.getDate("date_of_arrival").toLocalDate(),
                        res.getString("type_en"),
                        res.getString("type_uk"),
                        res.getDouble("volume"),
                        res.getDouble("weight"),
                        address,user,tariff);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return deliveryRequest;
    }

    @Override
    public DeliveryRequest findByName(String name) {
        return null;
    }

    @Override
    public List<DeliveryRequest> findAll() {

        List<DeliveryRequest> deliveryRequestList = new CopyOnWriteArrayList<>();
        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        TariffService tariffService = new TariffService();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `request` LEFT JOIN `receipt` ON `request`.`id_delivery_request`=`receipt`.`request_id_delivery_request` WHERE `receipt`.`id` IS NULL");
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                long id_user = res.getLong("users_id_user");
                long id_address = res.getLong("address_id_address");
                long id_tariff = res.getLong("tariff_id_tariff");
                User user = userService.getUserById(id_user);
                Address address = addressService.getAddressById(id_address);
                Tariff tariff = tariffService.findTariffById(id_tariff);
                deliveryRequestList.add(new DeliveryRequest(
                        res.getLong("id_delivery_request"),
                        res.getDate("date_of_arrival").toLocalDate(),
                        res.getString("type_en"),
                        res.getString("type_uk"),
                        res.getDouble("volume"),
                        res.getDouble("weight"),
                        address, user, tariff));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deliveryRequestList;
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


    @Override
    public List<DeliveryRequest> findByUser(long idUser) {

        List<DeliveryRequest> deliveryRequestList = new CopyOnWriteArrayList<>();
        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        TariffService tariffService = new TariffService();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `request` WHERE `request`.`users_id_user`=?");
            ps.setLong(1, idUser);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                long id_user = res.getLong("users_id_user");
                long id_address = res.getLong("address_id_address");
                long id_tariff = res.getLong("tariff_id_tariff");
                User user = userService.getUserById(id_user);
                Address address = addressService.getAddressById(id_address);
                Tariff tariff = tariffService.findTariffById(id_tariff);
                deliveryRequestList.add(new DeliveryRequest(
                        res.getLong("id_delivery_request"),
                        res.getDate("date_of_arrival").toLocalDate(),
                        res.getString("type_en"),
                        res.getString("type_uk"),
                        res.getDouble("volume"),
                        res.getDouble("weight"),
                        address, user, tariff));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deliveryRequestList;
    }
}
