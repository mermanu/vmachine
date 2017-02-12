/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.commands;

import com.vending.vmachine.context.MachineContext;

/**
 *
 * @author manuelmerida
 */
public interface MachineCommand {

    CResponse execute(MachineContext context) throws Exception;
    
    
}
