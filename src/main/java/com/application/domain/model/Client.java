package com.application.domain.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Виталий on 04.10.2017.
 */

//класс Клиент , наследуется от Человека
@Entity
@Table(name = "CLIENT")
public class Client extends Human implements Serializable {
    //первичый ключ
    @Column (name = "CLIENT_ID", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //поле контактная информация
    @Column(name = "INFO", nullable = false)
    private String info;

    //сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    //геттер для id
    public Long getId() {
        return id;
    }

    //сеттер для контактной информации (info)
    public void setInfo(String info) {
        this.info = info;
    }

    //геттер для контактной информации (info)
    public String getInfo() {
        return info;
    }

    //конструктор по умолчанию
    public Client() {}
}
