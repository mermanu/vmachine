/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine.commands;

/**
 * It could be any kind of format, as a json format string
 * Currently will be only a String with no format.
 * @author manuelmerida
 */
public class CResponse {
    
    private String response;

    public CResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
    
}
