package com.company;

import com.company.controller.Controller;
import com.company.model.Model;
import com.company.view.View;

public class Main {

    public static void main(String[] args)  {
        Controller controller = new Controller(new Model(), new View());
        controller.processUser();
    }
}
