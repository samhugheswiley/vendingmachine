package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    private UserIO UI ;
    public VendingMachineView(UserIO i){
        this.UI = i;

    }
    public void displayErrorMessage(String m){
        UI.print(m);
    }
    public int displayItems(List<Item> items) {
        UI.print("==============Vending Machine Items=============================================");
        items.forEach(i -> UI.print("Name: " + i.getName() + " Cost: " + i.getCost().toString() + " Amount Left: " + String.valueOf(i.getInventory()) + "\n"));
        int input = UI.readInt("Enter 1 to pick your item or 2 to quit");
        return input;
    }
    public Integer[] insertCoin() {
        Integer[] coinCounts = new Integer[4];
        coinCounts[0] = UI.readInt("How many Quarters do you wanna put in");
        coinCounts[1] = UI.readInt("How many Nickels do you wanna put in");
        coinCounts[2] = UI.readInt("How many Dimes do you wanna put in");
        coinCounts[3] = UI.readInt("How many Pennies do you wanna put in");
        return coinCounts;
    }
    public String pickItem(){
        return UI.readString("Please an item from the vending machine");
    }

    public int getMenuSelection() {
        return UI.readInt("Enter your choice: ");
    }

    public void displaySuccess(String message) {
        UI.print("SUCCESS: " + message);
    }



    public void unknownCommand(String message) {
        UI.print("Unknown Command " + message);
    }

    public void exitMessage(String message) {
        UI.print("Good Bye " + message);
    }

}