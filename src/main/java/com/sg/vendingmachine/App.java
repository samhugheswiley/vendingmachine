package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachine.java;
import com.sg.vendingmachine.ui.UserIO.java
import com.sg.vendingmachine.ui.UserIOImpl.java

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineController vendingMachine = new VendingMachineController(io);
        VendingMachineController.run();
    }
}
