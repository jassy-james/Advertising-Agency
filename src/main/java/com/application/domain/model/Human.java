package com.application.domain.model;

import javax.persistence.*;

/**
 * Created by Виталий on 04.10.2017.
 */

//абстрактный класс Человек
@MappedSuperclass
public abstract class Human {

    //поле Фамилия
    @Column (name = "SURNAME", nullable = false)
    protected String surname;

    //поле Имя
    @Column (name = "NAME", nullable = false)
    protected String name;

    //поле Отчество
    @Column (name = "PATRONYMIC", nullable = true)
    protected String patronymic;

    //сеттер для Фамилии
    public void setSurname(String surname) {
        this.surname = surname;
    }

    //геттер для Фамилии
    public String getSurname() {
        return surname;
    }

    //сеттер для Имени
    public void setName(String name) {
        this.name = name;
    }

    //геттер для Имени
    public String getName() {
        return name;
    }

    //сеттер для Отчества
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    //геттер для Отчества
    public String getPatronymic() {
        return patronymic;
    }
}
