
import java.sql.SQLException;
import java.util.Scanner;

public class Menus {
	
	private static Scanner input = new Scanner(System.in);
	
	protected static void toUserMenu(User user) {
		if(user.isAdmin()) {
			superMenu(user);
		}else {
			userMenu(user);
		}
	}
	
	protected static void superMenu(User user) {
		
		boolean isValidCommand = false;
		Console.clearScreen();
		System.out.println("Welcome to JDBC Bank, "+user.getFirstName()+" "+user.getLastName());
		System.out.println("How can we help you today?");
		
		while(!isValidCommand) {
			System.out.println("Type out one of the following commands:");
			System.out.println("'ADMINISTRATIVE'\t'PERSONAL ACCOUNTS'\t'LOGOUT'");
			
			String inputMethod = input.nextLine();
			
			if(inputMethod.toLowerCase().trim().equals("administrative")) {
				isValidCommand = true;
				UserMgmt.administrative(user);
			}else if(inputMethod.toLowerCase().trim().equals("personal accounts")) {
				isValidCommand = true;
				try{AcctMgmt.viewAcct(user);}catch(SQLException e) {}
			}else if(inputMethod.toLowerCase().trim().equals("logout")) {
				isValidCommand = true;
				Console.main(null);
			}else {
				System.out.println("The input did not match any of the listed commands.");
			}
		}
	
	}
	
	protected static void userMenu(User user) {
		Console.clearScreen();
		boolean isValidCommand = false;
		
		System.out.println("Welcome to JDBC Bank, "+user.getFirstName()+" "+user.getLastName());
		System.out.println("How can we help you today?");
		
		while(!isValidCommand) {
			System.out.println("Type out one of the following commands:");
			System.out.println("'VIEW ACCOUNTS'\t'LOGOUT'");
			
			String inputMethod = input.nextLine();
			
			if(inputMethod.toLowerCase().trim().equals("view accounts")) {
				isValidCommand = true;
				try{
					AcctMgmt.viewAcct(user);
				}catch(SQLException e) {}
			}else if(inputMethod.toLowerCase().trim().equals("logout")) {
				isValidCommand = true;
				Console.main(null);
			}else {
				System.out.println("The input did not match any of the listed commands.");
			}
		}
	
	}
	
	
}
