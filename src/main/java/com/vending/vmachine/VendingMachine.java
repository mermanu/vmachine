/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine;

/**
 *
 * @author manuelmerida
 */
public interface VendingMachine {
    String insertCoin(String coinCode) throws Exception;
    String selectProduct(String productCode) throws Exception;
    String requestProduct() throws Exception;
    String cancelSelection() throws Exception;
    String refundCoins() throws Exception;
}
