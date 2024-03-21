package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class VendingMachineView {
    private UserIO UI ;
    @Autowired
    public VendingMachineView(UserIO i){
        this.UI = i;

    }
    public void displayErrorMessage(String m){
        UI.print(m);
    }
   public void displayItems(List<Item> items) {
       UI.print("==============Vending Machine Items=============================================");
       items.forEach(i -> UI.print("Name: " + i.getName() + " Cost: " + i.getCost().toString() + " Amount Left: " + String.valueOf(i.getInventory()) + "\n"));

   }
   public void displayItem(Item items){
        UI.print("Name: " + items.getName() + " Cost: " + items.getCost().toString() + " Amount Left: " + String.valueOf(items.getInventory()));

   }
   public int getInput(){
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


    public void displayExitBanner() {
        UI.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        UI.print("Unknown Command!!!");
    }

    public void displayErrorMessage() {
        UI.print("=== ERROR ===");
        //Test - delete
    }
}
