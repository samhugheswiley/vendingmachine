package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ItemDaoImplementation implements ItemDao {


    public static final String ITEMS_FILE = "items.txt";
    public static final String DELIMITER = ":";
    private Map<String, Item> items = new HashMap<>();


    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {


        return null;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Change getChange() throws VendingMachinePersistenceException {
        return null;
    }


    private Item unmarshallItem(String itemAsText){

        String[] itemTokens = itemAsText.split(DELIMITER);

        String itemID = itemTokens[0];


        Item itemFromFile = new Item(itemID);

        // Index 1 - Item Name
        itemFromFile.setName(itemTokens[0]);

        // Index 2 - Price
        BigDecimal stringToBigDec = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(stringToBigDec);

        // Index 3 - Stock
        itemFromFile.setInventory(Integer.parseInt(itemTokens[2]));

        // We have now created a student! Return it!
        return itemFromFile;
    }
    private void loadItems() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load Items data into memory.", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }



}
