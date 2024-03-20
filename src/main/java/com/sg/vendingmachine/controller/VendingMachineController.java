package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Coin;
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
        BigDecimal total = BigDecimal.ZERO;
        total = total.add(BigDecimal.valueOf(coinCounts[0] * Coin.QUARTER.getValue()));
        total = total.add(BigDecimal.valueOf(coinCounts[1] * Coin.NICKEL.getValue()));
        total = total.add(BigDecimal.valueOf(coinCounts[2] * Coin.DIME.getValue()));
        total = total.add(BigDecimal.valueOf(coinCounts[3] * Coin.PENNY.getValue()));
        return total.divide(BigDecimal.valueOf(100)); // Convert pennies to dollars
    }

    private void displayItems() {
        List<Item> itemList = service.getAllItems();
        view.displayItems(itemList);
    }

    private int getMenuSelection() {
        return view.getMenuSelection();
    }

    private void vendItem() {
        Integer[] coinCounts = view.insertCoin();
        BigDecimal userMoney = calculateUserMoney(coinCounts);
        String itemName = view.pickItem();
        try {
            service.vendItem(itemName, userMoney);
            view.displaySuccess("Item successfully vended");
        } catch (InsufficientFundsException e) {
            view.displayErrorMessage("Insufficient funds. Please insert more coins.");
        } catch (NoItemInventoryException e) {
            view.displayErrorMessage("Item is out of stock.");
        }
    }


    private void unknownCommand() {
        io.print("Unknown Command");
    }

    private void exitMessage() {
        io.print("Good Bye");
    }
}