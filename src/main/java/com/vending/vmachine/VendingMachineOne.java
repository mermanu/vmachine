/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine;

import com.vending.vmachine.context.MachineContext;
import com.vending.vmachine.commands.CancelSelectionCommand;
import com.vending.vmachine.commands.InsertCoinCommand;
import com.vending.vmachine.commands.MachineCommand;
import com.vending.vmachine.commands.RequestProductCommand;
import com.vending.vmachine.commands.RequestRefundCommand;
import com.vending.vmachine.commands.SelectProductCommand;

/**
 *
 * @author manuelmerida
 */
public class VendingMachineOne implements VendingMachine {

    private MachineContext machineContext;

    public VendingMachineOne(MachineContext machineContext) {
        this.machineContext = machineContext;
    }

    @Override
    public String insertCoin(String coinCode) throws Exception {
        return executeCommand(new InsertCoinCommand(coinCode));
    }

    @Override
    public String selectProduct(String productCode) throws Exception {
        return executeCommand(new SelectProductCommand(productCode));
    }

    @Override
    public String requestProduct() throws Exception {
        return executeCommand(new RequestProductCommand());
    }

    @Override
    public String cancelSelection() throws Exception {
        return executeCommand(new CancelSelectionCommand());
    }

    @Override
    public String refundCoins() throws Exception {
        return executeCommand(new RequestRefundCommand());
    }

    private String executeCommand(MachineCommand command) throws Exception {
        return command.execute(machineContext).getResponse();
    }
}
