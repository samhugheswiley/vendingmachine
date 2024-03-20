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
    public void createItem(Item item) throws NoItemInventoryException {




    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Item getItem(String itemName) throws NoItemInventoryException {
        return null;
    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public void insertCoin(Coin coin) throws VendingMachinePersistenceException {

    }

    @Override
    public Change calculateChange(BigDecimal userMoney, BigDecimal itemCost) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public void vendItem(String itemName, BigDecimal userMoney) throws InsufficientFundsException {

    }
}
