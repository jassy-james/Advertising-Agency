package com.application.domain.dao.layer;

import com.application.domain.model.Order;
import com.application.domain.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виталий on 04.11.2017.
 */
@Repository("daoProduct")
public class DaoProductImpl implements DaoProduct {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    //метод для получения всего списка товаров
    @Override
    public List<Product> getAllProduct() throws SQLException {
        String sql = "SELECT * FROM PRODUCT";
        Query query = getSession().createNativeQuery(sql).addEntity(Product.class);
        List<Product> productList = query.list();
        return productList;
    }

    //метод для получения товара по его идентификатору
    @Override
    public Product getProductById(Long id) throws SQLException {
        String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = :id";
        Query query = getSession().createNativeQuery(sql).addEntity(Product.class);
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();
        return product;
    }

    //добавление товара в заказ по идентификатору
    @Override
    public void addProduct(Product product, Long id) throws SQLException {
        String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = :id";
        Query query = getSession().createNativeQuery(sql).addEntity(Order.class);
        query.setParameter("id", id);
        Order order = (Order) query.getSingleResult();
        product.setOrder(order);
        getSession().save(product);
    }


    //метод изменения товара в заказе
    @Override
    public void updateProductInOrder(Product product, Long id) {
        Product productUpdate = (Product) getSession().load(Product.class, id);
        productUpdate.setName(product.getName());
        productUpdate.setCost(product.getCost());
        productUpdate.setDescription(product.getDescription());
        getSession().update(productUpdate);
    }

    //метод удаления товара
    @Override
    public void deleteProduct(Long id) throws SQLException {
        getSession().createQuery("DELETE FROM Product WHERE PRODUCT_ID = " + id).executeUpdate();
    }

    //метод для получения id заказа по id товара
    @Override
    public Order getOrderByIdProduct(Long id) throws SQLException {
        String sql = "SELECT ORDER_ID FROM PRODUCT WHERE PRODUCT_ID = :id";
        Query query = getSession().createNativeQuery(sql).addEntity(Order.class);
        query.setParameter("id", id);
        Order order = (Order) query.getSingleResult();
        return order;
    }
}
