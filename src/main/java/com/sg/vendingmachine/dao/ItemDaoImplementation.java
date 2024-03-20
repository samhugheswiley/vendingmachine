package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ItemDaoImplementation implements ItemDao {


    public static final String ITEMS_FILE = "items.txt";
    public static final String DELIMITER = ":";
    private Map<String, Item> items = new HashMap<>();


    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {

        loadItems();
        Item newItem = items.put(itemName, item);
        writeItems();
        return newItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {

        loadItems();
        return new ArrayList(items.values());
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {

        loadItems();
        return items.get(itemName);

    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {

        loadItems();
        Item removedItem = items.remove(itemName);
        writeItems();
        return removedItem;
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

    private String marshallItem(Item ourItem){

        String itemAsText = ourItem.getName() + DELIMITER;

        // add the rest of the properties in the correct order:

        // item Name
        itemAsText += ourItem.getName() + DELIMITER;

        // item Value
        itemAsText += ourItem.getCost() + DELIMITER;

        // item quantity
        itemAsText += ourItem.getInventory();

        // We have now turned a student to text! Return it!
        return itemAsText;
    }

    private void writeItems() throws VendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save item data.", e);
        }
        String itemAsText;
        List<Item> itemsList = this.getAllItems();
        for (Item currentItem : itemsList) {
            // turn a Student into a String
            itemAsText = marshallItem(currentItem);
            // write the Student object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
