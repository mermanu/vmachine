/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine;

import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public interface MachineAdmin {
    String restartMachine() throws Exception;
    String addProductStock(String productCode, BigDecimal price, Integer quantity) throws Exception;
}
