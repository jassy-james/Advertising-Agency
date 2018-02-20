package com.application.domain.dao.layer;

import com.application.domain.model.Order;
import com.application.domain.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виталий on 04.11.2017.
 */
public interface DaoProduct {

    //получение
    List<Product> getAllProduct() throws SQLException;
    Product getProductById(Long id) throws SQLException;
    Order getOrderByIdProduct(Long id) throws SQLException;

    //добавление
    void addProduct(Product product, Long id) throws SQLException;

    //изменение
    void updateProductInOrder(Product product, Long id) throws SQLException;

    //удаление
    void deleteProduct(Long id) throws SQLException;
}
