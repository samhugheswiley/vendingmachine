package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    UserIO UI = new UserIOConsoleImpl();
   public int displayItems(List<Item> items) {
       UI.print("==============Vending Machine Items=============================================");
       items.forEach(i -> UI.print("Name: " + i.getName() + " Cost: " + i.getCost().toString() + " Amount Left: " + String.valueOf(i.getInventory()) + "\n"));
       int input = UI.readInt("Enter 1 to pick your item or 2 to quit");
        return input;
   }
   public String insertCoin(){
       
      return UI.readString("Enter coins Q for Quarter,N for Nickel,D for dime,N for nickel");
   }
}
