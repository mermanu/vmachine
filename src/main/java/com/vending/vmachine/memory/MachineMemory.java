/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.memory;

/**
 *
 * @author manuelmerida
 */
public interface MachineMemory {

    MachineData getMachineData();
    
    void resetMemory();
}
