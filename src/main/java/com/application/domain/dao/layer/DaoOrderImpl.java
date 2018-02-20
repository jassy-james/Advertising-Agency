package com.application.domain.dao.layer;

import com.application.domain.model.Client;
import com.application.domain.model.Employee;
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
@Repository("daoOrder")
public class DaoOrderImpl implements DaoOrder {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    //метод для получения всего списка заказов
    @Override
    public List<Order> getAllOrder() throws SQLException {
        Query query = getSession().createQuery("FROM Order");
        List<Order> orderList = query.list();
        return orderList;
    }

    //метод для получения заказа по его идентификатору
    @Override
    public Order getOrderById(Long id) throws SQLException {
        String sql = "SELECT * FROM ORDERS WHERE ORDER_ID = :id";
        Query query = getSession().createNativeQuery(sql).addEntity(Order.class);
        query.setParameter("id", id);
        Order order = (Order) query.getSingleResult();
        return order;
    }

    //добавление нового заказа
    @Override
    public void addOrder(Order order) throws SQLException {
        List<Product> listProduct =  order.getProducts();
        for (int i = 0; i < listProduct.size(); i++) {
            listProduct.get(i).setOrder(order);
        }
        getSession().save(order);
    }

    //метод обновления заказа, клиента, сотрудника, без обновления списка товаров
    @Override
    public void updateOrderWithoutProduct(Order order, Long id) throws SQLException {
        Client clientUpdate = (Client) getSession().load(Client.class, id);
        clientUpdate.setSurname(order.getClient().getSurname());
        clientUpdate.setName(order.getClient().getName());
        clientUpdate.setPatronymic(order.getClient().getPatronymic());
        clientUpdate.setInfo(order.getClient().getInfo());

        Employee employeeUpdate = (Employee) getSession().load(Employee.class, id);
        employeeUpdate.setSurname(order.getEmployee().getSurname());
        employeeUpdate.setName(order.getEmployee().getSurname());
        employeeUpdate.setPatronymic(order.getEmployee().getPatronymic());
        employeeUpdate.setPhoneNumber(order.getEmployee().getPhoneNumber());
        employeeUpdate.setPost(order.getEmployee().getPost());

        Order orderUpdate = (Order) getSession().load(Order.class, id);
        orderUpdate.setStatus(order.getStatus());
        orderUpdate.setClient(clientUpdate);
        orderUpdate.setEmployee(employeeUpdate);
        getSession().update(orderUpdate);
    }

    //метод удаления заказа
    @Override
    public void deleteOrder(Long id) throws SQLException {
        getSession().createQuery("DELETE FROM Product WHERE ORDER_ID = " + id).executeUpdate();
        getSession().createQuery("DELETE FROM Order WHERE ORDER_ID = " + id).executeUpdate();
        getSession().createQuery("DELETE FROM Client WHERE CLIENT_ID = " + id).executeUpdate();
        getSession().createQuery("DELETE FROM Employee WHERE EMPLOYEE_ID = " + id).executeUpdate();
    }
}
