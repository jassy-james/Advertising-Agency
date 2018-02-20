package com.application.domain;

import com.application.domain.dao.layer.DaoOrder;
import com.application.domain.dao.layer.DaoProduct;
import com.application.domain.model.Order;
import com.application.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виталий on 26.10.2017.
 */

@Service("serviceAgency")
@Transactional
public class ServiceImpl implements ServiceAgency {

    @Autowired
    @Qualifier("daoOrder")
    private DaoOrder daoOrder;

    @Autowired
    @Qualifier("daoProduct")
    private DaoProduct daoProduct;

    //добавление нового заказа
    @Override
    public void addOrder(Order order) throws SQLException {
        summ(order);
        daoOrder.addOrder(order);
    }

    //добавление товара в заказ по идентификатору
    @Override
    public void addProduct(Product product, Long id) throws SQLException {
        daoProduct.addProduct(product, id);
        //перерасчет суммы заказа
        Order order = daoOrder.getOrderById(id);
        Integer summa = order.getSumm() + product.getCost();
        order.setSumm(summa);
    }

    //метод для получения всего списка товаров
    @Override
    public List<Product> getAllProduct() throws SQLException {
        return daoProduct.getAllProduct();
    }

    //метод для получения всего списка заказов
    @Override
    public List<Order> getAllOrder() throws SQLException {
        return daoOrder.getAllOrder();
    }

    //метод для получения товара по его идентификатору
    @Override
    public Product getProductById(Long id) throws SQLException {
        return daoProduct.getProductById(id);
    }

    //метод для получения заказа по его идентификатору
    @Override
    public Order getOrderById(Long id) throws SQLException {
        return daoOrder.getOrderById(id);
    }

    //метод изменения товара в заказе
    @Override
    public void updateProductInOrder(Product product, Long id) throws SQLException {
        //получили товар по id
        Product productBefore = daoProduct.getProductById(id);
        //нашли стоимость товара
        Integer productPriceBefore = productBefore.getCost();
        //нашли стоимость заказа, в который входил товар
        Integer orderPriceBefore = product.getOrders().getSumm();
        //нашли стоимость заказа без данного товара
        Integer orderPriceAfter = orderPriceBefore - productPriceBefore;
        daoProduct.updateProductInOrder(product, id);
        //посчитали новую сумму
        Integer summa = orderPriceAfter + product.getCost();
        Order order = daoProduct.getOrderByIdProduct(id);
        //добавили новую сумму в заказ
        order.setSumm(summa);
    }

    //метод обновления заказа, клиента, сотрудника, без обновления списка товаров
    @Override
    public void updateOrderWithoutProduct(Order order, Long id) throws SQLException {
        daoOrder.updateOrderWithoutProduct(order, id);
    }

    //метод удаления товара
    @Override
    public void deleteProduct(Long id) throws SQLException {
        Integer price = daoProduct.getProductById(id).getCost();
        //перерасчет суммы при удалении (-)
        Order order = daoProduct.getOrderByIdProduct(id);
        Integer summa = order.getSumm() - price;
        order.setSumm(summa);
        //удаление
        daoProduct.deleteProduct(id);
    }

    //метод удаления заказа
    @Override
    public void deleteOrder(Long id) throws SQLException {
        daoOrder.deleteOrder(id);
    }

    //метод для подсчета суммы заказа
    @Override
    public void summ(Order order) throws SQLException {
        Integer summ = 0;
        for (int i = 0; i < order.getProducts().size(); i++) {
            summ += order.getProducts().get(i).getCost();
        }
        order.setSumm(summ);
    }
}