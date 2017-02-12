/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.commands;

import com.vending.vmachine.MachineConstants;
import com.vending.vmachine.context.MachineContext;
import com.vending.vmachine.context.MachineState;
import com.vending.vmachine.products.MachineProduct;
import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public class SelectProductCommand implements MachineCommand {

    private String productCode;

    public SelectProductCommand(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public CResponse execute(MachineContext context) {
        if (MachineState.COINS_IN_QUEUE.equals(context.getState())) {
            MachineProduct machineProduct = context.getData().getProducts().get(productCode);
            if (machineProduct != null) {
                if (context.getData().getProductStock().get(productCode) > 0) {
                    BigDecimal coinsSum = CommandUtil.getCoinsSum(context.getData().getCoinsQueue());
                    if (coinsSum.subtract(machineProduct.getPrice()).compareTo(BigDecimal.ZERO) >= 0) {
                        context.getData().setSelectedProduct(machineProduct);
                        context.setState(MachineState.PRODUCT_SELECTED);
                        return new CResponse(MachineConstants.PRODUCT_SELECTED + machineProduct.getCode());
                    } else {
                        return new CResponse(MachineConstants.INSERT_MORE_COINS_PRICE_IS + machineProduct.getPrice());
                    }
                } else {
                    return new CResponse(MachineConstants.THERE_IS_NO_MORE + productCode);
                }
            } else {
                return new CResponse(MachineConstants.PRODUCT_CODE_DOES_NOT_EXIST);
            }
        } else {
            return new CResponse(MachineConstants.PLEASE_INSERT_COINS_TO_SELECT_THE_PRODUCT);
        }
    }
}
