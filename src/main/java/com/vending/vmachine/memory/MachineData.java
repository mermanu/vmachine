/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.memory;

import com.vending.vmachine.coins.MachineCoin;
import com.vending.vmachine.products.MachineProduct;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author manuelmerida
 */
public class MachineData {

    private Map<String, MachineProduct> products;
    private Map<String, Integer> productStock;
    private Map<String, Integer> coinsRepository;
    private Queue<MachineCoin> coinsQueue = new LinkedList<>();
    private MachineProduct SelectedProduct;
    private BigDecimal refund;

    public MachineData(Map<String, Integer> productStock, Map<String, Integer> coinsRepository, Map<String, MachineProduct> products) {
        this.productStock = productStock;
        this.coinsRepository = coinsRepository;
        this.products = products;
    }

    public Map<String, Integer> getProductStock() {
        return productStock;
    }

    public void setProductStock(Map<String, Integer> productStock) {
        this.productStock = productStock;
    }

    public Map<String, Integer> getCoinsRepository() {
        return coinsRepository;
    }

    public void setCoinsRepository(Map<String, Integer> coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    public Queue<MachineCoin> getCoinsQueue() {
        return coinsQueue;
    }

    public void setCoinsQueue(Queue<MachineCoin> coinsQueue) {
        this.coinsQueue = coinsQueue;
    }

    public MachineProduct getSelectedProduct() {
        return SelectedProduct;
    }

    public void setSelectedProduct(MachineProduct SelectedProduct) {
        this.SelectedProduct = SelectedProduct;
    }

    public Map<String, MachineProduct> getProducts() {
        return products;
    }

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }
    
}
