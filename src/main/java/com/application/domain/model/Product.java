package com.application.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Виталий on 04.10.2017.
 */

//класс Товар
@Entity
@Table (name = "PRODUCT")
//@Proxy(lazy = false)
public class Product implements Serializable {

    //идентификационный номер
    @Column (name = "PRODUCT_ID", nullable = false)
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    //поле наименование
    @Column (name = "NAME", nullable = false)
    private String name;

    //поле цена
    @Column (name = "COST", nullable = false)
    private Integer cost;

    //поле описание
    @Column (name = "DESCRIPTION", nullable = false)
    private String description;

    //ссылка на заказ
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "ORDER_ID")
    private Order order;

    //сеттер для идентификационного номера
    public void setId(Long id) {
        this.id = id;
    }

    //геттер для идентификационного номера
    public Long getId() {
        return id;
    }

    //сеттер для наименования
    public void setName(String order) {
        this.name = order;
    }

    //геттер для наименования
    public String getName() { return name; }

    //сеттер для цены
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    //геттер для цены
    public Integer getCost() {
        return cost;
    }

    //сеттер для описания
    public void setDescription(String description) {
        this.description = description;
    }

    //геттер для описания
    public String getDescription() {
        return description;
    }

    //сеттер для листа заказов
    public void setOrder(Order order) {
        this.order = order;
    }

    //геттер для листа заказов
    @JsonIgnore //для правильного количества вывода (иначе будет бесконечный цикл)
    public Order getOrders() {
        return order;
    }

    //конструктор по умолчанию
    public Product() {}
}
