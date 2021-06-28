package com.company;

public class View
{
    // Text's constants
    public static final String INPUT_INT_DATA = "Input INT value between ";

    public static final String WRONG_INPUT_DATA = "Wrong input! Repeat please!";
    public static final String MORE = "More than ";
    public static final String LESS = "Less than ";
    public static final String DIAPAZON = "Value in ";
    public static final String ATTEMPTS = "Attempts: ";
    public static final String POINTS = "..";
    public  static final String WIN = "You win! ";

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printMessageInLine(String message){
        System.out.print(message);
    }

    public void printAttempts(int e){
    System.out.print(e+" ");
}



}
