package com.company.controller;

import com.company.model.entity.NoteBook;
import com.company.view.View;

import java.util.Scanner;

import static com.company.controller.RegexContainer.*;
import static com.company.view.TextConstant.NICKNAME_DATA;
import static com.company.view.TextConstant.SURNAME_DATA;

/**
 * Created by Iryna Furmaniuk on 07.07.2021.
 */
public class InputNoteToNotebook {
    private View view;
    private Scanner sc;
    private String surname;
    private String nickname;

    public InputNoteToNotebook(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
    }

    public void inputNote() {
        UtilityController utilityController =
                new UtilityController(sc, view);
        String str = (String.valueOf(View.bundle.getLocale()).equals("ua"))
                ? REGEX_SURNAME_UKR : REGEX_SURNAME_LAT;

        setSurname((utilityController.inputStringValueWithScanner
                (SURNAME_DATA, str)));
        setNickname((utilityController.inputStringValueWithScanner
                (NICKNAME_DATA, REGEX_NICKNAME)));
    }

    public void inputNickname() {
        UtilityController utilityController =
                new UtilityController(sc, view);

        setNickname((utilityController.inputStringValueWithScanner
                (NICKNAME_DATA, REGEX_NICKNAME)));
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
