package com.company;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new View(), new EntryInNotebook());
        controller.processUser();
    }
}
