package com.cg.mypaymentapp.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService{
	WalletRepo repo;
	public WalletServiceImpl()
	{
		// TODO Auto-generated constructor stub
		repo=new WalletRepoImpl();
	}
	

	@Override
	public Customer createAccount(String name, String mobileno,
			BigDecimal amount) {
		// TODO Auto-generated method stub
		Customer c1=repo.findOne(mobileno);
		if(c1==null)
		{
		Wallet w=new Wallet(amount);
		Customer c=new Customer(name,mobileno,w);
		if(repo.save(c))
		return c;
		else
			throw new InvalidInputException("Invalid");
		}
		else 
			throw new InvalidInputException("Number already Exists");
	}

	@Override
	public Customer showBalance(String mobileno) {
		// TODO Auto-generated method stub
		Customer c=repo.findOne(mobileno);
		if(c!=null)
		return c;
		else
			throw new InvalidInputException("The number does not exist Please enter registered mobile number");
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount) {
		Customer c=repo.findOne(sourceMobileNo);
		Customer c1=repo.findOne(targetMobileNo);
		if(amount.intValue()<=0)
			throw new InsufficientBalanceException("Please enter amount more than 0 amount cannot be less than or equal to zero");
		if(c==null)
			throw new InvalidInputException("Sender Number not found Please enter registered mobile number");
		if(c1==null)
			throw new InvalidInputException("Reciever Number not registered");
		if(c!=null&&c1!=null)
		{
		withdrawAmount(sourceMobileNo,amount);
		depositAmount(targetMobileNo,amount);
		return c;
		}
		else
		throw new InvalidInputException("Transfer failed");
		// TODO Auto-generated method stub
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		//return dao.depositAmount(mobileNo, amount);
		Customer cu=repo.findOne(mobileNo);
		//Wallet w=cu.getWallet();
		if(amount.intValue()<=0)
			throw new InsufficientBalanceException("Please enter amount more than 0 amount cannot be less than or equal to zero");
		if(cu!=null)
		{
		cu.getWallet().setBalance(cu.getWallet().getBalance().add(amount));
		repo.save(cu);
		return cu;
		}
		throw new InvalidInputException("Number not found Please enter registered mobile number");
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount){
		// TODO Auto-generated method stub
		//return dao.withdrawAmount(mobileNo, amount);
		Customer cu=repo.findOne(mobileNo);
		if(cu==null)
			throw new InvalidInputException("Number not found Please enter registered mobile number");
		//Wallet w=cu.getWallet();
		if(cu.getWallet().getBalance().subtract(amount).intValue()>=500)
		{
		cu.getWallet().setBalance(cu.getWallet().getBalance().subtract(amount));
		repo.save(cu);
		return cu;
		}
		else
			throw new InsufficientBalanceException("Insufficient Balance in  your account cannot deduct money because we need to maintain minimum of 500 balance");
	}


	@Override
	public boolean validateName(String name) throws InvalidInputException{
		// TODO Auto-generated method stub
		Pattern p=Pattern.compile("[A-Z][a-z]{0,19}");
		Matcher m=p.matcher(name);
		if(m.matches())
			return true;
		else
		throw new InvalidInputException("Name is invalid first letter capital and other as small letter and maximum of 20 characters long ");
	}


	@Override
	public boolean validateNumber(String number) {
		// TODO Auto-generated method stub
		Pattern p=Pattern.compile("[6-9][0-9]{9}");
		Matcher m=p.matcher(number);
		if(m.matches())
			return true;
		else
			throw new InvalidInputException("Number is not correct please enter 10 digit number");
	}


	@Override
	public boolean validateBalance(BigDecimal number) {
		// TODO Auto-generated method stub
		if(number.intValue()>=500)
		return true;
		else
			throw new InsufficientBalanceException("Amount is less than 500...Deposit minimum 500 to create account");
	}


	@Override
	public boolean isNewMobile(String mob) {
		// TODO Auto-generated method stub
		Customer c1=repo.findOne(mob);
		if(c1==null)
		return false;
		else
			throw new InvalidInputException("Number already exists");
	}


	@Override
	public boolean isAvailableMobile(String mob) {
		// TODO Auto-generated method stub
		Customer c1=repo.findOne(mob);
		if(c1!=null)
		return false;
		else
			throw new InvalidInputException("Number not found");
	}


	

}
