package com.company;

public class Main {

    public static void main(String[] args) {
        int[] array = {3, 8, 1, 0, -5, 7,-2,0};
        int integerNumber = 3;


        PracticeStream practice = new PracticeStream();

        //Task 1
        practice.average(array);
        //Task 2
        practice.minElement(array);
        //Task 3
        practice.amountOfZeroElement(array);
        //Task 4
        practice.amountOfElementsMoreThanZero(array);
        //Task 5
        practice.multiplicationByNumber(array, integerNumber);
    }
}
