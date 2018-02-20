package com.application.domain.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Виталий on 04.10.2017.
 */

//класс Employee(Сотрудник), наследуется от класса Human(Человек)
@Entity
@Table (name = "EMPLOYEE")
public class Employee extends Human implements Serializable {

    //первичный ключ
    @Column (name = "EMPLOYEE_ID", nullable = false)
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    //поле должность сотрудника
    @Column (name = "POST", nullable = false)
    private String post;

    //поле номер телефона
    @Column (name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    //сеттер для id
    public void setId(Long id) {
        this.id = id;
    }

    //геттер для id
    public Long getId() {
        return id;
    }

    //сеттер для должности
    public void setPost(String post) {
        this.post = post;
    }

    //геттер для должности
    public String getPost() {
        return post;
    }

    //сеттер для номера телефона
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //геттер для номера телефона
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //конструктор по умолчанию
    public Employee() {}
}
