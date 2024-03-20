package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {

    // For Items
    void createItem(Item item) throws
            VendingMachinePersistenceException;

    List<Item> getAllItems() throws
            VendingMachinePersistenceException;

    Item getItem(String itemName) throws
            NoItemInventoryException;

    Item removeItem(String itemName) throws
            VendingMachinePersistenceException;


// Not sure this is needed
    void insertCoin(Coin coin) throws
            VendingMachinePersistenceException;


    Change calculateChange(BigDecimal userMoney, BigDecimal itemCost) throws
            VendingMachinePersistenceException;

    void vendItem(String itemName, BigDecimal userMoney) throws
            InsufficientFundsException, NoItemInventoryException;


}
