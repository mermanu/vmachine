/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.commands;

import com.vending.vmachine.util.CommandUtil;
import com.vending.vmachine.common.MachineConstants;
import com.vending.vmachine.context.MachineContext;
import com.vending.vmachine.context.MachineState;

/**
 *
 * @author manuelmerida
 */
public class CancelSelectionCommand implements MachineCommand{

    @Override
    public CResponse execute(MachineContext context) {
        if (MachineState.PRODUCT_SELECTED.equals(context.getState())) {
            context.setState(MachineState.WAITING_ORDER);
            context.getData().setRefund(CommandUtil.getCoinsSum(context.getData().getCoinsQueue()));
            context.setState(MachineState.SELECTION_CANCELED);
            return new CResponse(MachineConstants.OPERATION_CANCELED);
        }else{
            return new CResponse(MachineConstants.THERE_IS_NO_SELECTION);
        }
    }
    
}
