/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.coins;

import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public enum CoinsEnum implements MachineCoin {

    TWO(BigDecimal.valueOf(2), "TWO"),
    ONE(BigDecimal.valueOf(1),"ONE"),
    HALF(BigDecimal.valueOf(0.50),"HALF"),
    FIFTH(BigDecimal.valueOf(0.20),"FIFTH"),
    DIME(BigDecimal.valueOf(0.10),"DIME"),
    NICKEL(BigDecimal.valueOf(0.05),"NICKEL");

    private final BigDecimal coinValue;
    private final String code;

    private CoinsEnum(BigDecimal coinValue, String code) {
        this.coinValue = coinValue;
        this.code = code;
    }

    @Override
    public BigDecimal getCoinValue() {
        return coinValue;
    }

    @Override
    public String getCode() {
        return code;
    }

}
