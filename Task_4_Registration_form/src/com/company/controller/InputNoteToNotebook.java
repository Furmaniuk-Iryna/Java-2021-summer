package com.company.controller;

import com.company.model.Model;
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
    private Model model;


    public InputNoteToNotebook(View view, Scanner sc, Model model) {
        this.view = view;
        this.sc = sc;
        this.model = model;
    }

    public void inputNote() {
        UtilityController utilityController =
                new UtilityController(sc, view);
        String str = (String.valueOf(View.bundle.getLocale()).equals("ua"))
                ? REGEX_SURNAME_UKR : REGEX_SURNAME_LAT;

        model.setSurname((utilityController.inputStringValueWithScanner
                (SURNAME_DATA, str)));

        model.setNickname((utilityController.inputStringValueWithScanner
                (NICKNAME_DATA, REGEX_NICKNAME)));

    }
}
