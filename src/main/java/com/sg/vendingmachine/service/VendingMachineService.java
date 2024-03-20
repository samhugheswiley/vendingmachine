package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {

    // For Items
    void addItem(Item item);

    List<Item> getAllItems();

    Item getItem(String itemName);

    Item removeItem(String itemName);

    // For Coins

    void insertCoin(Coin coin);


    Change calculateChange(BigDecimal userMoney, BigDecimal itemCost);

    void vendItem(String itemName, BigDecimal userMoney) throws
            InsufficientFundsException;


}
