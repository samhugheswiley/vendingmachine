package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.util.List;

public interface ItemDao {


    Item addItem(String itemName, Item item)
        throws VendingMachinePersistenceException;



    List<Item> getAllItems()
        throws VendingMachinePersistenceException;


    Item getItem(String itemName)
        throws VendingMachinePersistenceException;


    Item removeItem(String itemName)
        throws VendingMachinePersistenceException;

    Change getChange()
        throws VendingMachinePersistenceException;

}
