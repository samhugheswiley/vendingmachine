package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineService {

    // For Items
    void addItem(Item item);

    List<Item> getAllItems();

    Item getItem(String itemName);

    Item removeItem(String itemName);

    Change calculateChange(BigDecimal userMoney, BigDecimal itemCost);

    // For Coins

    void insertCoin(Coin coin);


    // For Change
    List<Change> changeToReturn();

}
