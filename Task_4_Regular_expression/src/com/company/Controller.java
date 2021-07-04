package com.company;

import java.util.Scanner;

public class Controller {
    View view = new View();
    EntryInNotebook entryInNotebook = new EntryInNotebook();

    public Controller(View view, EntryInNotebook entryInNotebook) {
        this.view = view;
        this.entryInNotebook = entryInNotebook;
    }

    public void processUser() {
        Scanner firstInput = new Scanner(System.in);
        Scanner secondInput = new Scanner(System.in);
        entryInNotebook.setLastName(checkLastName(firstInput));
        entryInNotebook.setNickname(checkNickname(secondInput));
        entryInNotebook.writeInNotebook();
    }

    private String checkLastName(Scanner firstInput) {
        view.printMessage(View.INPUT_LAST_NAME);
        while (!firstInput.hasNext("[A-Z]{1}[a-z]{1,}")) {
            view.printMessage(View.WRONG_INPUT_INT_DATA + View.INPUT_LAST_NAME);
            firstInput.next();
        }
        return firstInput.next();
    }

    private String checkNickname(Scanner secondInput) {
        view.printMessage(View.INPUT_NICKNAME);
        while (!secondInput.hasNext("^[а-яА-Яa-zA-Z0-9_-]{1,}$")) {
            view.printMessage(View.WRONG_INPUT_INT_DATA + View.INPUT_NICKNAME);
            secondInput.next();
        }
        return secondInput.next();
    }

}



