/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.context;

import com.vending.vmachine.memory.MachineData;
import com.vending.vmachine.memory.MachineMemory;
import com.vending.vmachine.memory.MemoryEnum;

/**
 *
 * @author manuelmerida
 */
public class MachineContext {

    private MachineState state;
    private MachineData data;

    public MachineContext(MachineState state) {
        this.state = state;
        MachineMemory memory = MemoryEnum.DEFAULT_DATA;
        data = memory.getMachineData();
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public MachineData getData() {
        return data;
    }

}
