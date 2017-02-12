/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine;

import com.vending.vmachine.context.MachineContext;
import com.vending.vmachine.memory.MemoryEnum;
import java.math.BigDecimal;

/**
 *
 * @author manuelmerida
 */
public class MachineAdminOne implements MachineAdmin{
    
    private MachineContext context;

    public MachineAdminOne(MachineContext context) {
        this.context = context;
    }
    
    

    @Override
    public String restartMachine() throws Exception {
        MemoryEnum.DEFAULT_DATA.resetMemory();
        return "Machine data restarted";
    }

    @Override
    public String addProductStock(String productCode, BigDecimal price, Integer quantity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
