/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.commands;

import com.vending.vmachine.util.CommandUtil;
import com.vending.vmachine.common.MachineConstants;
import com.vending.vmachine.coins.CoinsEnum;
import com.vending.vmachine.coins.MachineCoin;
import com.vending.vmachine.context.MachineContext;
import com.vending.vmachine.context.MachineState;
import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public class InsertCoinCommand implements MachineCommand {

    private String coinCode;

    public InsertCoinCommand(String coinCode) {
        this.coinCode = coinCode;
    }

    @Override
    public CResponse execute(MachineContext context) {
        if (MachineState.WAITING_ORDER.equals(context.getState()) || 
                MachineState.COINS_IN_QUEUE.equals(context.getState())) {
            MachineCoin machineCoin = CoinsEnum.valueOf(coinCode);
            context.getData().getCoinsQueue().add(machineCoin);
            context.setState(MachineState.COINS_IN_QUEUE);
            BigDecimal coinsSum = CommandUtil.getCoinsSum(context.getData().getCoinsQueue());
            return new CResponse(coinsSum.toString());
        } else {
            return new CResponse(MachineConstants.PLEASE_WAIT_UNTIL_FINISH_THE_CURRENT_OPERATION);
        }
    }
}
