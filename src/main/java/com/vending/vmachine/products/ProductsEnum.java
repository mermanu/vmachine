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
public enum ProductsEnum implements ProductFactory {

    COKE(new MachineProduct(BigDecimal.valueOf(1.50), "COKE")),
    PEPSI(new MachineProduct(BigDecimal.valueOf(1.45), "PEPSI")),
    WATER(new MachineProduct(BigDecimal.valueOf(0.90), "WATER"));

    private final MachineProduct machineProduct;

    private ProductsEnum(MachineProduct machineProduct) {
        this.machineProduct = machineProduct;
    }

    @Override
    public MachineProduct getMachineProduct() {
        return machineProduct;
    }
}
