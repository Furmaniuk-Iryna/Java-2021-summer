package com.company;

public class Model {
    private int minBarrier;
    private int maxBarrier;

    private int secretValue;

    // [1-99]
    public void setSecretValue(){
        secretValue = (int)Math.ceil(Math.random()*
                (maxBarrier - minBarrier - 1) + minBarrier);
    }
}
