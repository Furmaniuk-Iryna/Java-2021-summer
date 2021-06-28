package com.company;

import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;
    private  int c;

public Controller(Model model, View view) {
    this.model = model;
    this.view = view;
}

public  void processUser(){
    Scanner sc = new Scanner(System.in);
    model.setX(inputIntValueWithScanner(sc));
    model.Verification();
    answerToView();
}

public void message(){
    view.printMessage(View.RANGE+model.getDmin() +View.POINTS+model.getDmax());
    view.printMessageInLine(View.ATTEMPTS);
    attempts();
    view.printMessage(" ");
    processUser();
}

public void  answerToView(){
        switch (model.getAnswer()){
            case "win" : view.printMessage(View.WIN);view.printMessageInLine(View.ATTEMPTS);attempts();break;
            case "less" : view.printMessage(View.LESS + model.getX()); message();break;
            case "more" : view.printMessage(View.MORE +model.getX());message();break;
            case "ignore" : processUser();break;
        }
}

public int inputIntValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_INT_DATA +model.getDmin() +View.POINTS+model.getDmax());
        while(  !sc.hasNextInt() ) {
            view.printMessage(View.WRONG_INPUT_DATA + View.INPUT_INT_DATA +model.getDmin() +View.POINTS+model.getDmax());
            sc.next();}
        c= sc.nextInt();
        return inputIntValueRange(sc);
}

public int inputIntValueRange(Scanner sc) {
    while ((c>100) || (c<0)) {
        view.printMessage(View.WRONG_INPUT_DATA);
        inputIntValueWithScanner(sc);
    }
    return c;
}

public void attempts(){
    for (int e: model.attempt){
       view.printAttempts(e);
    }
}

}
