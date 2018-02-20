package com.application.rest;

import com.application.domain.ServiceAgency;
import com.application.domain.model.Order;
import com.application.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Виталий on 05.10.2017.
 */

@RestController
public class ControllerRest {

    @Autowired
    @Qualifier("serviceAgency")
    private ServiceAgency serviceAgency;

    //РАБОТАЕТ
    //получение всех заказов в формате JSON
    @RequestMapping(value = "/main/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrder() throws SQLException {
        return serviceAgency.getAllOrder();
    }

    //РАБОТАЕТ
    //получение всех товаров в формате JSON
    @RequestMapping(value = "/main/orders/products", method = RequestMethod.GET)
    public List<Product> getAllProduct() throws SQLException {
        return serviceAgency.getAllProduct();
    }

    //РАБОТАЕТ
    //получение заказа по id
    @RequestMapping(value = "/main/orders/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) throws SQLException {
        Order order = serviceAgency.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    //РАБОТАЕТ
    //получение товара по id
    @RequestMapping(value = "/main/orders/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws SQLException {
        Product product = serviceAgency.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //РАБОТАЕТ
    //добавление заказа полностью
    @RequestMapping(value = "/main/orders", method = RequestMethod.POST)
    public ResponseEntity<Void> addOrder(@RequestBody Order order, UriComponentsBuilder uriComponentsBuilder)
            throws SQLException {
        serviceAgency.addOrder(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //РАБОТАЕТ
    //добавление товара в заказ
    @RequestMapping(value = "/main/orders/products/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> addProduct(@PathVariable("id") long id, @RequestBody Product product, UriComponentsBuilder uriComponentsBuilder)
            throws SQLException {
        serviceAgency.addProduct(product, id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/orders/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //РАБОТАЕТ
    //изменение заказа по его id без изменения товаров и суммы
    @RequestMapping(value = "/main/orders/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrderWithoutProduct(@PathVariable("id") long id, @RequestBody Order order)
            throws SQLException {
        Order currentOrder = serviceAgency.getOrderById(id);
        if (currentOrder == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        currentOrder.setStatus(order.getStatus());
        currentOrder.setClient(order.getClient());
        currentOrder.setEmployee(order.getEmployee());
        serviceAgency.updateOrderWithoutProduct(currentOrder, id);
        return new ResponseEntity<Order>(currentOrder, HttpStatus.OK);
    }

    //РАБОТАЕТ
    //Изменение товара в заказе
    @RequestMapping(value = "/main/orders/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProductInOrder(@PathVariable("id") long id, @RequestBody Product product)
            throws SQLException {
        Product currentProduct = serviceAgency.getProductById(id);
        if (currentProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setName(product.getName());
        currentProduct.setCost(product.getCost());
        currentProduct.setDescription(product.getDescription());
        serviceAgency.updateProductInOrder(currentProduct, id);
        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
    }

    //РАБОТАЕТ
    //удаление заказа со всеми его товарами
    @RequestMapping(value = "/main/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") long id) throws SQLException {
        Order order = serviceAgency.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        serviceAgency.deleteOrder(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }


    //удаление товара
    //РАБОТАЕТ
    @RequestMapping(value = "/main/orders/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) throws SQLException {
        Product product = serviceAgency.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        serviceAgency.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
}

