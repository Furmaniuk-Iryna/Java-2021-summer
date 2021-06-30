package com.company;

import java.util.ArrayList;
import java.util.List;

public class Model {
    // Data
    private int minBarrier;
    private int maxBarrier;

    private int secretValue;

    private List<Integer> yourWay = new ArrayList<Integer>();

    // [1-99]
    public void setSecretValue(){
        secretValue = (int)Math.ceil(Math.random()*
                (maxBarrier - minBarrier - 1) + minBarrier);
    }




    public void setPrimaryBarrier(int minBarrier, int maxBarrier){
        this.minBarrier = minBarrier;
        this.maxBarrier = maxBarrier;
    }

    public int getSecretValue() {
        return secretValue;
    }
    public int getMinBarrier() {
        return minBarrier;
    }
    public int getMaxBarrier() {
        return maxBarrier;
    }
    public List<Integer> getYourWay() {
        return yourWay;
    }

}
