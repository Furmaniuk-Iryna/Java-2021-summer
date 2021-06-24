package com.company.task1;

import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model  = model;
        this.view = view;
    }

    // The Work method
    public void processUser(){
        Scanner sc1 = new Scanner (System.in);
        Scanner sc2 = new Scanner (System.in);
        model.setFirstWord(inputFirstValueWithScanner(sc1));
        model.setSecondWord(inputSecondValueWithScanner(sc2));

        view.printMessage(model.HelloWorld());
    }

    // The Utility methods
    public String inputFirstValueWithScanner(Scanner sc1) {
        view.printMessage(View.FIRST_WORD);
        while( ! "Hello".equals(sc1.next())) {
            view.printMessage(View.WRONG_INPUT + View.FIRST_WORD);
        }
        return "Hello";
    }

    public String inputSecondValueWithScanner(Scanner sc2) {
        view.printMessage(View.SECOND_WORD);
        while( ! "world!".equals(sc2.next())) {
            view.printMessage(View.WRONG_INPUT + View.SECOND_WORD);
        }
        return "world!";
    }
}
