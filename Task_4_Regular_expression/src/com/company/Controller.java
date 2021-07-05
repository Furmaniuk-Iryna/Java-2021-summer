package com.company;

import java.util.Scanner;

/**
 * Created by Iryna Furmaniuk on 04.07.2021.
 */

public class Controller {
    View view = new View();
    EntryInNotebook entryInNotebook = new EntryInNotebook();

    public Controller(View view, EntryInNotebook entryInNotebook) {
        this.view = view;
        this.entryInNotebook = entryInNotebook;
    }

    public void processUser() {
        Scanner input = new Scanner(System.in);
        entryInNotebook.setLastName(checkLastName(input));
        entryInNotebook.setNickname(checkNickname(input));
        entryInNotebook.writeInNotebook();
    }

    private String checkLastName(Scanner input) {
        view.printMessage(View.INPUT_LAST_NAME);
        while (!input.hasNext("[A-ZА-ЯІЇЄҐ]{1}[a-zа-яіїєґ]{1,}")) {
            view.printMessage(View.WRONG_INPUT_INT_DATA + View.INPUT_LAST_NAME);
            input.next();
        }
        return input.next();
    }

    private String checkNickname(Scanner input) {
        view.printMessage(View.INPUT_NICKNAME);
        while (!input.hasNext("[а-яА-Яa-zA-Z0-9_-]{1,30}")) {
            view.printMessage(View.WRONG_INPUT_INT_DATA + View.INPUT_NICKNAME);
            input.next();
        }
        return input.next();
    }

}



