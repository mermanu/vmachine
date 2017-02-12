/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.memory;

import com.vending.vmachine.products.MachineProduct;
import java.util.HashMap;

/**
 *
 * @author manuelmerida
 */
public enum MemoryEnum implements MachineMemory {

    DEFAULT_DATA(new MachineData(new HashMap<String, Integer>(), new HashMap<String, Integer>(), new HashMap<String, MachineProduct>()));

    private MachineData machineData;

    private MemoryEnum(MachineData machineData) {
        this.machineData = machineData;
    }

    @Override
    public MachineData getMachineData() {
        return machineData;
    }

    @Override
    public void resetMemory() {
        machineData = new MachineData(new HashMap<String, Integer>(), new HashMap<String, Integer>(), new HashMap<String, MachineProduct>());
    }

}
