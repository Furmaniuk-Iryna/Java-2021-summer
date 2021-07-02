package com.company;

import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;
    private  int value;

public Controller(Model model, View view) {
    this.model = model;
    this.view = view;
}

public  void processUser(){
    model.setSecretValue();
    checkValue();
    view.printMessageInLine(View.ATTEMPTS);
    attempts();
}
public  void checkValue(){
    Scanner sc = new Scanner(System.in);
    model.setUserValue(inputIntValueWithScanner(sc));
    model.Verification();
    answerToView();
}
public void message(){

    view.printMessage(View.RANGE+model.getDmin() +View.POINTS+model.getDmax());
    checkValue();
}

public void  answerToView(){
    if (model.getAnswer()=="win"){
        view.printMessage(View.WIN); }
    else if (model.getAnswer()=="less"){
            view.printMessage(View.LESS + model.getUserValue()); message();}
            else if (model.getAnswer()=="more"){
                    view.printMessage(View.MORE +model.getUserValue());message();
                 } else {
                checkValue();}

}

public int inputIntValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_INT_DATA +model.getDmin() +View.POINTS+model.getDmax());
        while(  !sc.hasNextInt() ) {
            view.printMessage(View.WRONG_INPUT_DATA + View.INPUT_INT_DATA +model.getDmin() +View.POINTS+model.getDmax());
            sc.next();}
        value = sc.nextInt();
        return inputIntValueRange(sc);
}

public int inputIntValueRange(Scanner sc) {
    while ((value>100) || (value<0)) {
        view.printMessage(View.WRONG_INPUT_DATA);
        inputIntValueWithScanner(sc);
    }
    return value;
}

public void attempts(){
    for (int e: model.attempt){
       view.printAttempts(e);
    }
}

}
