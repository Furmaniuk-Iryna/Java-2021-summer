package com.company.controller;

import com.company.model.Model;
import com.company.view.View;

import java.util.Scanner;

/**
 * Created by Iryna Furmaniuk on 04.07.2021.
 */
public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processUser() {
        Scanner sc = new Scanner(System.in);
        InputNoteToNotebook inputNoteToNotebook = new InputNoteToNotebook(view, sc, model);
        inputNoteToNotebook.inputNote();
        model.writeInNotebook();
    }
}



