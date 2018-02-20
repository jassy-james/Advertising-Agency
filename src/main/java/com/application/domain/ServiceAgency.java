package com.application.domain;

import com.application.domain.model.Order;
import com.application.domain.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виталий on 26.10.2017.
 */

public interface ServiceAgency {

    //получение
    List<Order> getAllOrder() throws SQLException;
    List<Product> getAllProduct() throws SQLException;

    Order getOrderById(Long id) throws SQLException;
    Product getProductById(Long id) throws SQLException;

    //добавление
    void addOrder(Order order) throws SQLException;
    void addProduct(Product product, Long id) throws SQLException;

    //изменение
    void updateOrderWithoutProduct(Order order, Long id) throws SQLException;
    void updateProductInOrder(Product product, Long id) throws SQLException;

    //удаление
    void deleteOrder(Long id) throws SQLException;
    void deleteProduct(Long id) throws SQLException;

    //подсчет суммы заказа
    void summ(Order order) throws SQLException;
}
