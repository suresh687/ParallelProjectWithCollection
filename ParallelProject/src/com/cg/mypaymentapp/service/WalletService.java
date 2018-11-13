package com.cg.mypaymentapp.service;
import java.math.BigDecimal;

import com.cg.mypaymentapp.beans.Customer;


public interface WalletService {
public Customer createAccount(String name ,String mobileno, BigDecimal amount);
public Customer showBalance (String mobileno);
public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount);
public Customer depositAmount (String mobileNo,BigDecimal amount );
public Customer withdrawAmount(String mobileNo, BigDecimal amount);
public boolean validateName(String name);
public boolean validateNumber(String number);
public boolean validateBalance(BigDecimal number);
public boolean isNewMobile(String mob);
public boolean isAvailableMobile(String mob);
}
