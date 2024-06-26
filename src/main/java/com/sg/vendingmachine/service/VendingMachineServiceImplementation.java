package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemDao;
import com.sg.vendingmachine.dao.VendingAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceImplementation implements VendingMachineService {

    private VendingAuditDao auditDao;
    ItemDao dao;

    // Constructor
    public VendingMachineServiceImplementation(VendingAuditDao auditDao, ItemDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }



    @Override
    public void createItem(Item item) throws VendingMachinePersistenceException {
        if (dao.addItem(item.getName(), item) != null) {
            throw new VendingMachinePersistenceException(
                    "ERROR");
            /*
            "ERROR: Could not create student.  Student Id "
                            + student.getStudentId()
                            + " already exists");
             */
        }

    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
         return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemName) throws NoItemInventoryException {
        Item item = dao.getItem(itemName);
        if (item == null) {
            throw new NoItemInventoryException("Item can't be null");
        }
        return item;
    }


    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        return dao.removeItem(itemName);
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachinePersistenceException {

    }

    @Override
    public Change calculateChange(BigDecimal userMoney, BigDecimal itemCost) throws VendingMachinePersistenceException {
        BigDecimal change = userMoney.subtract(itemCost);
        int changeInPennies = change.multiply(new BigDecimal("100")).intValueExact();
        return new Change(changeInPennies);
    }

    @Override
    public void vendItem(String itemName, BigDecimal userMoney) throws InsufficientFundsException, NoItemInventoryException {
        Item item = getItem(itemName);
        if (item.getCost().compareTo(userMoney) > 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        if (item.getInventory() <= 0) {
            throw new NoItemInventoryException("Item is out of stock");
        }
        item.setInventory(item.getInventory() - 1);
        dao.updateItem(item);
    }

}
