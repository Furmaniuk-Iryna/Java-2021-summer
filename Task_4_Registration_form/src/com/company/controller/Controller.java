package com.company.controller;

import com.company.model.Model;
import com.company.model.entity.NotUniqueNicknameException;
import com.company.model.entity.NoteBook;
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
        InputNoteToNotebook inputNoteToNotebook = new InputNoteToNotebook(view, sc);
        inputNoteToNotebook.inputNote();

        NoteBook noteBook = getNoteBook(inputNoteToNotebook);
        System.out.println(noteBook);
    }

    private NoteBook getNoteBook(InputNoteToNotebook inputNoteToNotebook) {
        NoteBook noteBook = null;
        while (true) {
            try {
                noteBook = new NoteBook(inputNoteToNotebook.getSurname(), inputNoteToNotebook.getNickname());
                break;
            } catch (NotUniqueNicknameException e) {
                e.printStackTrace();
                System.out.println(e.getNicknameData() + " nickname is not unique ");
                inputNoteToNotebook.inputNickname();
            }
        }
        return noteBook;
    }
}



