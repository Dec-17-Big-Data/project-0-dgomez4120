package users;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import accounts.Account;
import accounts.AcctDaoImpl;
import application.Menus;

public class UserMgmt {
	
	private static Scanner input = new Scanner(System.in);
	
	public static void administrative(User user) {
		UserDaoImpl userdi = new UserDaoImpl();
		AcctDaoImpl acctdi = new AcctDaoImpl();
		List<User> users = userdi.getAllUsers();
		List<Account> accts = acctdi.getAllAccounts();
		
		boolean isHere = true;
		do {
			System.out.println("Type in one of the following commands: \n"
					+ "'USERS'\t'CREATE USER'\t'DELETE USER'\n'PROMOTE USER'\t'ACCOUNTS'\t'MAIN MENU'");
			String commandInput = input.nextLine();
			
			if(commandInput.toLowerCase().trim().equals("users")) {
				userList(users);
			}else if(commandInput.toLowerCase().trim().equals("create user")) {
				userdi.createUser();
				users = userdi.getAllUsers();
			}else if(commandInput.toLowerCase().trim().equals("delete user")) {
				deleteUser(userdi, users);
				users = userdi.getAllUsers();
			}else if(commandInput.toLowerCase().trim().equals("promote user")) {
				promoteUser(userdi, users);
				users = userdi.getAllUsers();
			}else if(commandInput.toLowerCase().trim().equals("accounts")) {
				acctList(accts);
			}else if(commandInput.toLowerCase().trim().equals("main menu")) {
				isHere = false;
				Menus.toUserMenu(user);
			}else {
				System.out.println("The input did not match any of the listed commands.");
			}
		}while(isHere);
	}
	
	private static void userList(List<User> users) {
		System.out.println("User ID\tFirst Name\tLast Name\tUsername\t\tAdministrator\tActive");
		Collections.sort(users);
		
		for(User user : users) {
			String userid = String.format("%05d", user.getUserId());
			String firstName = String.format("%-15s", user.getFirstName());
			String lastName = String.format("%-15s", user.getLastName());
			String username = String.format("%-20s", user.getUsername());
			System.out.println(userid+"\t"+firstName+"\t"+lastName+"\t"+username+"\t"+user.isAdmin()+"\t\t\t"+user.isOpen());
		}
	}
	
	private static void deleteUser(UserDaoImpl userdi, List<User> users) {
		Scanner in = new Scanner(System.in);
		System.out.println("Which user would you like to delete?\n"
				+ "USER ID: ");
		int userId = 0;
		try {
			userId = in.nextInt();
		}catch(InputMismatchException ime) {
			System.out.println("INPUT MUST BE INTEGER");
			deleteUser(userdi, users);
			return;
		}
		User user = userdi.getUser(userId, users);
		if(user != null) {
			userdi.deleteUser(user);
		}else {
			deleteUser(userdi, users);
			return;
		}
	}
	
	private static void promoteUser(UserDaoImpl userdi, List<User> users) {
		System.out.println("Which user are you promoting?\n"
				+ "USER ID:");
		int userid = 0;
		try {
			userid = input.nextInt();
		}catch(InputMismatchException ime) {
			System.out.println("INPUT MUST BE INTEGER");
			promoteUser(userdi, users);
			return;
		}
		User user = userdi.getUser(userid, users);
		userdi.updateUser(user);
		
	}
	
	private static void acctList(List<Account> accts) {
		System.out.println("Account Number\tUser ID\tAccout Creation\tStatus");
		for(Account account : accts){
			String status;
			if(account.isOpen()) {status = "Open";}else {status = "Closed";}
			String acctNum = String.format("%05d", account.getAccoutNumber());
			String userId = String.format("%05d", account.getUserId());
			System.out.println(acctNum+"\t\t\t"+userId+"\t"+account.getCreationDay()+"\t\t"+status);
		}
	}
	
}
