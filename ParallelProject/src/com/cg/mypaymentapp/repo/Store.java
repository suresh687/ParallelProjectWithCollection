package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.util.HashMap;
import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;

public class Store {

	public static HashMap<String,Customer > map;
	public static HashMap<String, Customer> createColletion()
	{
		if(map==null)
		{
			map=new HashMap<String,Customer>();
			map.put("9010121131", new Customer("Suresh","9010121131",new Wallet(new BigDecimal(5000))));
			map.put("9010121132", new Customer("Ramesh","9010121132",new Wallet(new BigDecimal(5000))));
		}
		return map;
	}
}



