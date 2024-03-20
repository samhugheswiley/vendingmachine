package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.ItemDao;
import com.sg.vendingmachine.dao.VendingAuditDao;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Coin;
import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceImplementation implements VendingMachineService {

    private VendingAuditDao auditDao;
    ItemDao dao;

    public VendingMachineServiceImplementation(VendingAuditDao auditDao, ItemDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public Item getItem(String itemName) {
        return null;
    }

    @Override
    public Item removeItem(String itemName) {
        return null;
    }

    @Override
    public void insertCoin(Coin coin) {

    }

    @Override
    public Change calculateChange(BigDecimal userMoney, BigDecimal itemCost) {
        return null;
    }

    @Override
    public void vendItem(String itemName, BigDecimal userMoney) throws InsufficientFundsException {

    }
}
