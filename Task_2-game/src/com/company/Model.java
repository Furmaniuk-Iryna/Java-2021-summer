package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.*;

public class Model {

    private int userValue;
    private int secretValue;
    private int dmin = 0;
    private int dmax = 100;
    private String answer;
    ArrayList<Integer> attempt = new ArrayList<Integer>();

public void setSecretValue(){
     secretValue = (int) (Math.random()*98+1);}



    public void Verification() {

    if (userValue==secretValue) {
        setAnswer("win");
        attempt.add(userValue);
    } else
        if (userValue > secretValue && userValue<dmax) {
            setAnswer("less");
            attempt.add(userValue);
            setDmax(userValue);
        }
        else
            if (userValue < secretValue && userValue>dmin) {
            setAnswer("more");
            attempt.add(userValue);
            setDmin(userValue);
            }
            else setAnswer("ignore");
            }

    public int getSecretValue() {
        return secretValue;
    }

    public void setDmin(int dmin) {
        this.dmin = dmin;
    }

    public void setDmax(int dmax) {
        this.dmax = dmax;
    }

    public int getUserValue() {
        return userValue;
    }

    public void setUserValue(int userValue) {
        this.userValue = userValue;
    }

    public int getDmin() {
        return dmin;
    }

    public int getDmax() {
        return dmax;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
