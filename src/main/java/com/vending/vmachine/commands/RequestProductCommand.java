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
import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public class RequestProductCommand implements MachineCommand {

    @Override
    public CResponse execute(MachineContext context) {
        if (MachineState.PRODUCT_SELECTED.equals(context.getState())) {
            context.getData().getCoinsQueue().stream().forEach(coin -> {
                Integer currentCoinsQuantity = context.getData().getCoinsRepository().get(coin.getCode());
                context.getData().getCoinsRepository().put(coin.getCode(),
                        currentCoinsQuantity != null ? currentCoinsQuantity+1 : 1);
            });
            BigDecimal coinsSum = CommandUtil.getCoinsSum(context.getData().getCoinsQueue());
            BigDecimal refund = coinsSum.subtract(context.getData().getSelectedProduct().getPrice());
            
            context.getData().setRefund(refund);
            context.getData().getCoinsQueue().clear();
            context.getData().setSelectedProduct(null);
            context.setState(MachineState.PRODUCT_DELIVERED);
            return new CResponse(MachineConstants.PRODUCT_DELIVERED);
        } else {
            return new CResponse(MachineConstants.SELECT_PRODUCT_FIRST_PLEASE);
        }
    }
}
