package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.*;

public class Model {
    Controller controller;
    private int x;
    private int dmin = 0;
    private int dmax = 100;
    private String answer;
    ArrayList<Integer> attempt = new ArrayList<Integer>();

    int y = (int) (Math.random()*101);


    public void Verification() {

    if (x==y) {
        setAnswer("win");
        attempt.add(x);
    } else
        if (x > y && x<dmax) {
            setAnswer("less");
            attempt.add(x);
            dmax=x;
        }
        else
            if (x < y && x>dmin) {
            setAnswer("more");
            attempt.add(x);
            dmin=x;
            }
            else setAnswer("ignore");
            }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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
