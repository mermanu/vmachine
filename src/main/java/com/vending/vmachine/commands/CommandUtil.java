/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.commands;

import com.vending.vmachine.coins.MachineCoin;
import java.math.BigDecimal;
import java.util.Queue;

/**
 *
 * @author manuelmerida
 */
public class CommandUtil {
    public static BigDecimal getCoinsSum(Queue<MachineCoin> coinsQueue){
        BigDecimal coinsSum = coinsQueue.stream().map(MachineCoin::getCoinValue).reduce(BigDecimal::add).get();
        return coinsSum;
    }
}
