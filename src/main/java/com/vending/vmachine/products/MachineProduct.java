/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.products;

import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public class MachineProduct {

    private final BigDecimal price;
    private final String code;

    public MachineProduct(BigDecimal price, String code) {
        this.price = price;
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

}
