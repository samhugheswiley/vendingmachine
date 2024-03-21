package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Component
public class ItemDaoImplementation implements ItemDao {


    public static final String ITEMS_FILE = "Items.txt";
    public static final String DELIMITER = ":";
    private Map<String, Item> items = new HashMap<>();

    private List<Change> change = new ArrayList<>();


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
    public Item removeStock(String itemName) throws VendingMachinePersistenceException {
        loadItems();
        int previousStock = items.get(itemName).getInventory();
        items.get(itemName).setInventory(previousStock - 1);
        writeItems();
        return items.get(itemName);
    }


    // Change stuff - this is basically the accessing to a Database
    @Override
    public List<Change> getListOfChange() throws VendingMachinePersistenceException {
        return change;
    }
    public void addChange(Change change){
        this.change.add(change);
    }

    public void removeChangeFromChangeList(BigDecimal itemPrice){
        // need to add up the change value of our list of Change
        // Then we take the Item price, and if enough change is in the change list
        // we remove the change so that our new change is correct, if not return an exception saying not enough money
        // or something
    }


    private Item unmarshallItem(String itemAsText){

        String[] itemTokens = itemAsText.split(DELIMITER);

        String itemID = itemTokens[0];

        Item itemFromFile = new Item(itemID);

        // Index 1 - Item Name
        itemFromFile.setName(itemTokens[0]);

        // Index 2 - Price - Not working
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

        String itemAsText = ""; // = ourItem.getName() + DELIMITER;

        // add the rest of the properties in the correct order:

        // item Name
        itemAsText += ourItem.getName() + DELIMITER;

        // item Value
        itemAsText += ourItem.getCost() + DELIMITER;

        // item quantity
        itemAsText += ourItem.getInventory();

        // We have now turned an Item to text! Return it!
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
            // turn an Item into a String
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
