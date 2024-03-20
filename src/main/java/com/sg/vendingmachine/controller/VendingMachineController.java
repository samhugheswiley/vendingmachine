package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {

    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineService service;

    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            try {
                List<Item> itemList = service.getAllItems();
                displayItems(itemList);
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        vendItem();
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            } catch (InsufficientFundsException | NoItemInventoryException e) {
                io.print(e.getMessage());
            } catch (VendingMachinePersistenceException e) {
                throw new RuntimeException(e);
            }
        }
        exitMessage();
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