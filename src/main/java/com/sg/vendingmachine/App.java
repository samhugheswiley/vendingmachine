package com.sg.vendingmachine;


import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.AuditDao;
import com.sg.vendingmachine.dao.ItemDao;
import com.sg.vendingmachine.dao.ItemDaoImplementation;
import com.sg.vendingmachine.dao.VendingAuditDao;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceImplementation;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) throws NoItemInventoryException {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView VendingMachineView = new VendingMachineView(io);
        ItemDao myDao = new ItemDaoImplementation();
        VendingAuditDao myAudit = new AuditDao();
        VendingMachineService Service = new VendingMachineServiceImplementation(myAudit,myDao);
        VendingMachineController vendingMachine = new VendingMachineController(Service,VendingMachineView);


        vendingMachine.run();
    }
}
