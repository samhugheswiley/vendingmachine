package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {

    private VendingMachineService service;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            try {
                List<Item> itemList = service.getAllItems();
                menuSelection = view.displayItems(itemList);

                switch (menuSelection) {
                    case 1:
                        Integer[] coinCounts = view.insertCoin();
                        BigDecimal userMoney = calculateUserMoney(coinCounts);
                        String itemName = view.pickItem();
                        service.vendItem(itemName, userMoney);
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            } catch (InsufficientFundsException e) {
                view.displayErrorMessage(e.getMessage());
            } catch (VendingMachinePersistenceException e) {
                throw new RuntimeException(e);
            }
        }
        exitMessage();
    }

    private BigDecimal calculateUserMoney(Integer[] coinCounts) {
        // Implement money calculation logic here
    }

    private void displayItems(List<Item> itemList) {
        // To Implement
    }

    private int getMenuSelection() {
        return 0;
        // To Implement
    }

    private void vendItem() throws InsufficientFundsException, NoItemInventoryException {
        // To Implement
    }

    private void unknownCommand() {
        io.print("Unknown Command");
    }

    private void exitMessage() {
        io.print("Good Bye");
    }
}