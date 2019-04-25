package com.bank.view;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//import com.bank.controller.*;
import com.bank.model.Customer;


 
public class AllProcess {
	static Scanner scan=new Scanner(System.in);
	static ArrayList<Customer> list=new ArrayList<Customer>();
	//static BankLogin blogin=new BankLogin();
	static void register() {
		System.out.println("enter no of customers");
		int n=scan.nextInt();
		for(int i=0;i<n;i++) {
		Customer cust=new Customer();
		System.out.println("enter first name");
		cust.setFirstName(scan.next());
		System.out.println("enter last name");
		cust.setLastName(scan.next());
		System.out.println("enter aadhar number");
		cust.setAadharNo(scan.nextLong());
		System.out.println("enter address");
		cust.setAddress(scan.next());
		System.out.println("enter mobile number");
		cust.setMobileNo(scan.nextLong());
		System.out.println("enter password");
		cust.setPassword(scan.next());
		System.out.println("enter account number");
		long no= 10000 + new Random().nextInt(90000);
		//System.out.println(Math.abs(cust.getAccNo()));
		list.add(cust);
		}
	}
	static void serializeArray(){
		try {
			FileOutputStream output = new FileOutputStream("user1.txt");
			ObjectOutputStream object=new ObjectOutputStream(output);
			object.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void deserializeArray() {
		try {
			FileInputStream out=new FileInputStream("user1.txt");
			ObjectInputStream obj=new ObjectInputStream(out);
			try {
				ArrayList<Customer> array=(ArrayList<Customer>)obj.readObject();
				long aadhar=scan.nextLong();
				for(int i=0;i<array.size();i++) {
					Customer customer = array.get(i);
					if(customer.getAadharNo()==aadhar) {
						login();
					}
					else{
						register();
						//blogin.login();
					}
				}
			}
			 catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void login() {
		int ano=scan.nextInt();
		String pass=scan.next();
		for(Customer cust10:list)
		if((cust10.getAccNo()==ano)&&(cust10.getPassword().equals(pass))) {
			//System.out.println("");
			while(true) {
				int choice=scan.nextInt();
			switch(choice) {
			case 1:System.out.println("Enter the amount u want to withdraw");
					int amount= scan.nextInt();
					withdraw(amount);
					break;
			case 2:System.out.println("Enter the amount u want to withdraw");
					int amount1= scan.nextInt();
					deposit(amount1);
					break;
			case 3: System.exit(0);
			}
		}
		}
	}
	static void withdraw(int amont) {
		for(Customer cust3:list) {
			if(cust3.getBalance()<amont)
				System.out.println("insufficient balance");
			else {
				//double bal;
				double bal=cust3.getBalance();
				bal=bal-amont;
				System.out.println(bal);
			}
		}		
	}
	static void deposit(int amont1) {
		for(Customer cust5:list){
		//double bal;
		double bal=cust5.getBalance();
		bal=bal+amont1;
		System.out.println(bal);
		}
	}
	public static void main(String[] args) {
		int option=scan.nextInt();
			switch(option) {
			case 1: register();
				break;
			case 2: login();
					break;
			default:System.exit(0);
			}
	}
}
