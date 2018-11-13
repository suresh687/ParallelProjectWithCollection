package com.cg.mypaymentapp.ui;

import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Main {
	private static Scanner sc;

	public static void main(String args[])
	{
	Customer c=new Customer();
	WalletService service=new WalletServiceImpl();
	sc = new Scanner(System.in);
	int ch;
	do
	{
		System.out.println("1.Create Account");
		System.out.println("2.Deposit Amount");
	    System.out.println("3.Withdraw");
		System.out.println("4.Show Balance");
		System.out.println("5.FundTransfer");
		System.out.println("6.Exit");
		System.out.println("Enter ur choice");
		ch=sc.nextInt();
		switch(ch)
		{
		case 1:
			String name;
			String mob;
			BigDecimal bal;
			do{
					System.out.println("Enter name");
					name=sc.next();
					try{
					if(service.validateName(name))
						break;
					}
					catch(InvalidInputException e)
					{
						System.out.println(e.getMessage());
					}
				}while(true);
				
				do
				{
					try{
					System.out.println("Enter mobile number");
					mob=sc.next();
					if(service.validateNumber(mob)&&!service.isNewMobile(mob))
						break;
					}
					catch(InvalidInputException e)
					{
						System.out.println(e.getMessage());
					}
				}while(true);
				
				do
				{
					try{
				System.out.println("Enter intial balance");
					bal=sc.nextBigDecimal();
				if(service.validateBalance(bal))
					break;
					}
					catch(InsufficientBalanceException e)
					{
						System.out.println(e.getMessage());
					}
				}while(true);
				c=service.createAccount(name,mob,bal);
				System.out.println(c.getName()+" "+c.getMobileNo()+" "+c.getWallet().getBalance());
			break;
			
		case 2:
			String mobi;
			BigDecimal amt;
		
			do{
				try
				{
				System.out.println("Enter the mobile no. that you registered for the account");
				mobi=sc.next();
				if(service.validateNumber(mobi)&&!service.isAvailableMobile(mobi))
					break;
				}
				catch(InvalidInputException e)
				{
					System.out.println(e.getMessage());
				}
			}while(true);
			do{
				try
				{
					System.out.println("Enter the amount you want to deposit");
					amt=sc.nextBigDecimal();
					c=service.depositAmount(mobi, amt);
					System.out.println(c.getName()+" "+c.getMobileNo()+" "+c.getWallet().getBalance());
					break;
				}
				catch(InsufficientBalanceException e)
				{
					System.out.println(e.getMessage());
				}
				
			}while(true);
				
					
					
			break;
		
		case 3:
			String mobil;
			BigDecimal amt1;
			
			do{
				try
				{
				System.out.println("Enter the mobile no. that you registered for the account");
				mobil=sc.next();
				if(service.validateNumber(mobil)&&!service.isAvailableMobile(mobil))
					break;
				}
				catch(InvalidInputException e)
				{
					System.out.println(e.getMessage());
				}
			}while(true);
			do{
				try
				{
					System.out.println("Enter the amount you want to withdraw");
					amt1=sc.nextBigDecimal();
					c=service.withdrawAmount(mobil, amt1);
					System.out.println(c.getName()+" "+c.getMobileNo()+" "+c.getWallet().getBalance());
					break;
				}
				catch(InsufficientBalanceException e)
				{
					System.out.println(e.getMessage());
				}
				
			}while(true);
			break;
			
		case 4:
			do
			{
			System.out.println("Enter registered mobile no.");
			String mobile=sc.next();
			try
			{
				if(service.validateNumber(mobile))
				{
					c=service.showBalance(mobile);
					break;
				}
			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			
			}while(true);
			System.out.println("Hello "+c.getName()+" Your Available Balance: "+c.getWallet().getBalance());
			break;
		case 5:
			String mob1;
			String mob2;
				do
				{
					try{
				System.out.println("Enter ur registered mobile no.");
				mob1=sc.next();
				if(service.validateNumber(mob1)&&!service.isAvailableMobile(mob1))
					break;
					}
					catch(InvalidInputException e)
					{
						System.out.println("Sender "+e.getMessage());
					}
				}while(true);
				
				do
				{
					try{
						System.out.println("Enter mobile no. registered on app of the receiver");
						mob2=sc.next();
				if(service.validateNumber(mob2)&&!service.isAvailableMobile(mob2))
					break;
					}
					catch(InvalidInputException e)
					{
						System.out.println("Receiver "+e.getMessage());
					}
				}while(true);
				do
				{
				System.out.println("Enter amount you want to transfer");
				BigDecimal amount=sc.nextBigDecimal();
				try{
						c=service.fundTransfer(mob1, mob2, amount);
						break;
				}
				catch(InvalidInputException e)
				{
					System.out.println(e.getMessage());
				}
				catch(InsufficientBalanceException e)
				{
					System.out.println(e.getMessage());
				}
			}while(true);
			
			if(c!=null)
			{
				System.out.println("Transfered amount successfully");
				System.out.println("Available balance in account: "+c.getName()+" with mobile no. "+c.getMobileNo()+" is "+c.getWallet().getBalance());
			}	
			else
				System.out.println("Transfer failed");
			break;
		
		case 6:
			System.out.println("Thanks for using our application");
			break;
		}
		
		
	}while(ch!=6);
	
	}
}
