/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vending.vmachine;

import com.vending.vmachine.common.MachineConstants;
import com.vending.vmachine.coins.CoinsEnum;
import com.vending.vmachine.context.MachineContext;
import com.vending.vmachine.context.MachineState;
import com.vending.vmachine.memory.MemoryEnum;
import com.vending.vmachine.products.ProductsEnum;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manuelmerida
 */
public class VendingMachineTest {

    public VendingMachineTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of insertCoin method, of class VendingMachine.
     */
    @Test
    public void testInsertCoin() throws Exception {
        System.out.println("insertCoin");
        MachineContext context = new MachineContext(MachineState.WAITING_ORDER);
        MemoryEnum.DEFAULT_DATA.resetMemory();
        VendingMachine vendingMachine = new VendingMachineOne(context);

        assertEquals("1", vendingMachine.insertCoin(CoinsEnum.ONE.name()));
        assertEquals(context.getState(), MachineState.COINS_IN_QUEUE);
        assertEquals("2", vendingMachine.insertCoin(CoinsEnum.ONE.name()));
        assertEquals("2.5", vendingMachine.insertCoin(CoinsEnum.HALF.name()));
        assertEquals("2.55", vendingMachine.insertCoin(CoinsEnum.NICKEL.name()));
        assertEquals("2.75", vendingMachine.insertCoin(CoinsEnum.FIFTH.name()));
        assertEquals("2.85", vendingMachine.insertCoin(CoinsEnum.DIME.name()));
        assertEquals("4.85", vendingMachine.insertCoin(CoinsEnum.TWO.name()));

        context.setState(MachineState.PRODUCT_SELECTED);
        assertEquals(MachineConstants.PLEASE_WAIT_UNTIL_FINISH_THE_CURRENT_OPERATION, vendingMachine.insertCoin(CoinsEnum.ONE.name()));

    }

    /**
     * Test of selectProduct method, of class VendingMachine.
     */
    @Test
    public void testSelectProduct() throws Exception {
        System.out.println("selectProduct");
        MachineContext context = new MachineContext(MachineState.WAITING_ORDER);
        MemoryEnum.DEFAULT_DATA.resetMemory();
        fillContextWithProducts(context);
        VendingMachine vendingMachine = new VendingMachineOne(context);

        context.setState(MachineState.WAITING_ORDER);
        assertEquals(MachineConstants.PLEASE_INSERT_COINS_TO_SELECT_THE_PRODUCT, vendingMachine.selectProduct(ProductsEnum.COKE.name()));

        vendingMachine.insertCoin(CoinsEnum.ONE.name());
        assertEquals(MachineConstants.THERE_IS_NO_MORE + ProductsEnum.COKE.name(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));

        context.getData().getProductStock().put(ProductsEnum.COKE.name(), 1);
        assertEquals(MachineConstants.INSERT_MORE_COINS_PRICE_IS + ProductsEnum.COKE.getMachineProduct()
                .getPrice(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));

        vendingMachine.insertCoin(CoinsEnum.NICKEL.name());
        assertEquals(MachineConstants.INSERT_MORE_COINS_PRICE_IS + ProductsEnum.COKE.getMachineProduct()
                .getPrice(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));
        
        vendingMachine.insertCoin(CoinsEnum.DIME.name());
        assertEquals(MachineConstants.INSERT_MORE_COINS_PRICE_IS + ProductsEnum.COKE.getMachineProduct()
                .getPrice(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));
        
        vendingMachine.insertCoin(CoinsEnum.DIME.name());
        assertEquals(MachineConstants.INSERT_MORE_COINS_PRICE_IS + ProductsEnum.COKE.getMachineProduct()
                .getPrice(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));
        
        vendingMachine.insertCoin(CoinsEnum.NICKEL.name());
        assertEquals(MachineConstants.INSERT_MORE_COINS_PRICE_IS + ProductsEnum.COKE.getMachineProduct()
                .getPrice(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));
        
        vendingMachine.insertCoin(CoinsEnum.FIFTH.name());
        assertEquals(MachineConstants.PRODUCT_SELECTED + ProductsEnum.COKE.name(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));

        context.setState(MachineState.COINS_IN_QUEUE);
        vendingMachine.insertCoin(CoinsEnum.NICKEL.name());
        assertEquals(MachineConstants.PRODUCT_SELECTED + ProductsEnum.COKE.name(), vendingMachine.selectProduct(ProductsEnum.COKE.name()));

        assertEquals(context.getData().getSelectedProduct(), ProductsEnum.COKE.getMachineProduct());
        
        context.setState(MachineState.COINS_IN_QUEUE);
        assertEquals(MachineConstants.PRODUCT_CODE_DOES_NOT_EXIST, vendingMachine.selectProduct("NOEXIST"));

    }

    /**
     * Test of requestProduct method, of class VendingMachine.
     */
    @Test
    public void testRequestProduct() throws Exception {
        System.out.println("requestProduct");
        
        MachineContext context = new MachineContext(MachineState.WAITING_ORDER);
        MemoryEnum.DEFAULT_DATA.resetMemory();
        fillContextWithProducts(context);
        VendingMachine vendingMachine = new VendingMachineOne(context);
       
        assertEquals(MachineConstants.SELECT_PRODUCT_FIRST_PLEASE, vendingMachine.requestProduct());
        
        assertEquals(null, context.getData().getCoinsRepository().get(CoinsEnum.TWO.name()));
        
        context.getData().getProductStock().put(ProductsEnum.COKE.name(), 1);
        vendingMachine.insertCoin(CoinsEnum.TWO.name());
        vendingMachine.selectProduct(ProductsEnum.COKE.name());
        assertEquals(MachineConstants.PRODUCT_DELIVERED, vendingMachine.requestProduct());
        assertEquals(Integer.valueOf(1), context.getData().getCoinsRepository().get(CoinsEnum.TWO.name()));
        
        context.setState(MachineState.WAITING_ORDER);
        vendingMachine.insertCoin(CoinsEnum.TWO.name());
        vendingMachine.selectProduct(ProductsEnum.COKE.name());
        assertEquals(MachineConstants.PRODUCT_DELIVERED, vendingMachine.requestProduct());
        assertEquals(Integer.valueOf(2), context.getData().getCoinsRepository().get(CoinsEnum.TWO.name()));
        
    }

    /**
     * Test of cancelSelection method, of class VendingMachine.
     */
    @Test
    public void testCancelSelection() throws Exception {
        System.out.println("cancelSelection");
        MachineContext context = new MachineContext(MachineState.WAITING_ORDER);
        MemoryEnum.DEFAULT_DATA.resetMemory();
        fillContextWithProducts(context);
        VendingMachine vendingMachine = new VendingMachineOne(context);
        assertEquals(MachineConstants.THERE_IS_NO_SELECTION, vendingMachine.cancelSelection());
        
        context.getData().getProductStock().put(ProductsEnum.PEPSI.name(), 1);
        vendingMachine.insertCoin(CoinsEnum.TWO.name());
        vendingMachine.selectProduct(ProductsEnum.PEPSI.name());
        assertEquals(MachineConstants.OPERATION_CANCELED, vendingMachine.cancelSelection());
        
        assertEquals(BigDecimal.valueOf(2), context.getData().getRefund());
        assertEquals(MachineState.SELECTION_CANCELED, context.getState());
    }

    /**
     * Test of refundCoins method, of class VendingMachine.
     */
    @Test
    public void testRefundCoins() throws Exception {
        System.out.println("refundCoins");
        MachineContext context = new MachineContext(MachineState.WAITING_ORDER);
        MemoryEnum.DEFAULT_DATA.resetMemory();
        fillContextWithProducts(context);
        VendingMachine vendingMachine = new VendingMachineOne(context);
        
        
        
        assertEquals(MachineConstants.PLEASE_WAIT_UNTIL_FINISH_THE_CURRENT_OPERATION, vendingMachine.refundCoins());
        
        context.getData().getProductStock().put(ProductsEnum.COKE.name(), 1);
        vendingMachine.insertCoin(CoinsEnum.TWO.name());
        vendingMachine.selectProduct(ProductsEnum.COKE.name());
        assertEquals(MachineConstants.PRODUCT_DELIVERED, vendingMachine.requestProduct());
        assertEquals(Integer.valueOf(1), context.getData().getCoinsRepository().get(CoinsEnum.TWO.name()));
        assertEquals("0.5", vendingMachine.refundCoins());
        
        vendingMachine.insertCoin(CoinsEnum.ONE.name());
        assertEquals("1", vendingMachine.refundCoins());
        
        context.getData().getProductStock().put(ProductsEnum.COKE.name(), 1);
        vendingMachine.insertCoin(CoinsEnum.TWO.name());
        vendingMachine.selectProduct(ProductsEnum.COKE.name());
        vendingMachine.cancelSelection();
        assertEquals("2", vendingMachine.refundCoins());
        
        context.getData().getProductStock().put(ProductsEnum.COKE.name(), 1);
        vendingMachine.insertCoin(CoinsEnum.TWO.name());
        vendingMachine.insertCoin(CoinsEnum.HALF.name());
        vendingMachine.insertCoin(CoinsEnum.DIME.name());
        vendingMachine.selectProduct(ProductsEnum.COKE.name());
        vendingMachine.cancelSelection();
        assertEquals("2.6", vendingMachine.refundCoins());
    }
    
    private void fillContextWithProducts(MachineContext context){
        for (ProductsEnum products : ProductsEnum.values()) {
            context.getData().getProducts().put(products.name(), products.getMachineProduct());
            context.getData().getProductStock().put(products.name(), 0);
        }
    }

}
