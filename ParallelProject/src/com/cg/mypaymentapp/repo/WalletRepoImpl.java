package com.cg.mypaymentapp.repo;

import java.util.HashMap;

import com.cg.mypaymentapp.beans.Customer;
//import com.cg.mypaymentapp.pl.Datastore;

public class WalletRepoImpl implements WalletRepo {
	HashMap<String,Customer> m;
	
	public WalletRepoImpl() {
		// TODO Auto-generated constructor stub
		m=Store.createColletion();
	}

	@Override
	public boolean save(Customer customer) {
		// TODO Auto-generated method stub
		m.put(customer.getMobileNo(), customer);
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) {
		// TODO Auto-generated method stub
		return m.get(mobileNo);
	}

}
