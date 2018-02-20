package com.application.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Виталий on 14.10.2017.
 */

//класс "Заказ"
@Entity
@Table (name = "ORDERS")
public class Order implements Serializable {

    //идентификационный номер
    @Column (name = "ORDER_ID", nullable = false)
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    //статус
    @Column (name = "STATUS", nullable = false)
    private String status;

    //список товаров
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;

    //клиент
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "CLIENT_ID")
    private Client client;

    //сотрудник - исполнитель
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "EMPLOYEE_ID")
    private Employee employee;

    //сумма всего заказа
    @Column (name = "SUMM")
    private Integer summ;

    //сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    //геттер для id
    public Long getId() {
        return id;
    }

    //сеттер для статуса
    public void setStatus(String status) {
        this.status = status;
    }

    //геттер для статуса
    public String getStatus() {
        return status;
    }

    //сеттер для листа товаров
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    //геттер для листа товаров
    public List<Product> getProducts() {
        return products;
    }

    //сеттер для клиента
    public void setClient(Client client) {
        this.client = client;
    }

    //геттер для клиента
    public Client getClient() {
        return client;
    }

    //сеттер для сотрудника - исполнителя
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    //геттер для сотрудника - исполнителя
    public Employee getEmployee() {
        return employee;
    }

    //сеттер для суммы заказа
    public void setSumm(Integer summ) {
        this.summ = summ;
    }

    //геттер для суммы заказа
    public Integer getSumm() {
        return summ;
    }

    //конструктор по умолчанию
    public Order() {}
}
