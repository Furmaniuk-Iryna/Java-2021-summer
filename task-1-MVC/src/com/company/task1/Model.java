package com.company.task1;

public class Model {

    private String message;
    private String firstWord;
    private String secondWord;

    // The Program logic
    public String HelloWorld(){
        message = firstWord + " " + secondWord;
        return message;
    }


    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }
}
