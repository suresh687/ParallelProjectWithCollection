package com.cg.mypaymentapp.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestCases {
	@Test
	public void testValidateName()
	{
		WalletService s=new WalletServiceImpl();
		Assert.assertEquals(true,s.validateName("Suresh"));
	}
	@Test(expected=InvalidInputException.class)
	public void testValidateName1()
	{
		WalletService s=new WalletServiceImpl();
		s.validateName("suresh");
	}
	@Test(expected=InvalidInputException.class)
	public void testValidateName2()
	{
		WalletService s=new WalletServiceImpl();
		s.validateName("");
	}
	@Test(expected=InvalidInputException.class)
	public void testValidateName4()
	{
		WalletService s=new WalletServiceImpl();
		s.validateName("Sureshrate");
	}
	@Test
	public void testValidateNumber1()
	{
		WalletService s=new WalletServiceImpl();
		Assert.assertEquals(true,s.validateNumber("9010121131"));
		
	}
	@Test(expected=InvalidInputException.class)
	public void testValidateNumber2()
	{
		WalletService s=new WalletServiceImpl();
		s.validateNumber("9010s");
	}
	@Test(expected=InvalidInputException.class)
	public void testValidateNumber3()
	{
		WalletService s=new WalletServiceImpl();
		s.validateNumber("90101211313");
	}
	@Test(expected=InvalidInputException.class)
	public void testValidateNumber4()
	{
		WalletService s=new WalletServiceImpl();
		s.validateNumber("9010121");
	}
	@Test
	public void testValidateBalance1()
	{
		WalletService s=new WalletServiceImpl();
		Assert.assertEquals(true,s.validateBalance(new BigDecimal(500)));
	}
	@Test
	public void testValidateBalance2()
	{
		WalletService s=new WalletServiceImpl();
		Assert.assertEquals(true,s.validateBalance(new BigDecimal(501)));
	}
	@Test(expected=InsufficientBalanceException.class)
	public void testValidateBalance3()
	{
		WalletService s=new WalletServiceImpl();
		s.validateBalance(new BigDecimal(499));
	}
}
