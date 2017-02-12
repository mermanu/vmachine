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
public class RequestRefundCommand implements MachineCommand{

    @Override
    public CResponse execute(MachineContext context) {
        if (MachineState.PRODUCT_DELIVERED.equals(context.getState()) 
                || MachineState.SELECTION_CANCELED.equals(context.getState())
                || MachineState.COINS_IN_QUEUE.equals(context.getState())) {
            context.setState(MachineState.WAITING_ORDER);
            if(context.getData().getCoinsQueue()!=null && !context.getData().getCoinsQueue().isEmpty()){
                context.getData().setRefund(CommandUtil.getCoinsSum(context.getData().getCoinsQueue()));
                context.getData().getCoinsQueue().clear();
            }
            context.getData().setSelectedProduct(null);
            return new CResponse(context.getData().getRefund().toString());
        }else{
            return new CResponse(MachineConstants.PLEASE_WAIT_UNTIL_FINISH_THE_CURRENT_OPERATION);
        }
    }
}
