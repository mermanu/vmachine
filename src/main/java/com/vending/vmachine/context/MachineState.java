/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.context;

/**
 *
 * @author manuelmerida
 */
public enum MachineState {
    WAITING_ORDER,
    PRODUCT_SELECTED,
    COINS_IN_QUEUE,
    PRODUCT_DELIVERED,
    RESTARTING,
    SELECTION_CANCELED;
}
