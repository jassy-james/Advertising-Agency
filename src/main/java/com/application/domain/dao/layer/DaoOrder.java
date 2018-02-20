package com.application.domain.dao.layer;

import com.application.domain.model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виталий on 04.11.2017.
 */

public interface DaoOrder {

    //получение
    List<Order> getAllOrder() throws SQLException;
    Order getOrderById(Long id) throws SQLException;

    //добавление
    void addOrder(Order order) throws SQLException;

    //изменение
    void updateOrderWithoutProduct(Order order, Long id) throws SQLException;

    //удаление
    void deleteOrder(Long id) throws SQLException;
}
