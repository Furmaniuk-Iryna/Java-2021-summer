package com.company.model;

import java.util.ArrayList;

/**
 * Created by Iryna Furmaniuk on 04.07.2021.
 */
public class Model {
    private String surname;
    private String nickname;
    private ArrayList<Model> entriesInTheNotebook = new ArrayList<>();

    public Model() {
    }

    public Model(String surname, String nickname) {
        this.surname = surname;
        this.nickname = nickname;
    }

    public void writeInNotebook() {
        entriesInTheNotebook.add(new Model(getSurname(), getNickname()));
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
