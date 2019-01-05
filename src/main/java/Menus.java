import java.util.Scanner;

public class Menus {

	protected void toUserMenu(User user) {
		if(user.isAdmin()) {
			superMenu(user);
		}else {
			userMenu(user);
		}
	}
	
	protected static void superMenu(User user) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to JDBC Bank, "+user.getFirstName()+" "+user.getLastName());
		System.out.println("How can we help you today?");
		System.out.println("Type out one of the following commands:");
		System.out.println("'VIEW USERS'\t'VIEW ACCOUNTS'\t'CREATE USER'\t'DELETE USER'\t'VIEW PERSONAL ACCOUNTS'");
		
		String inputMethod = input.nextLine();
		
		if(inputMethod.toLowerCase().trim().equals("view users")) {
			
		}else if(inputMethod.toLowerCase().trim().equals("view accounts")) {
			
		}else if(inputMethod.toLowerCase().trim().equals("create user")) {
			
		}else if(inputMethod.toLowerCase().trim().equals("delete user")) {
			
		}else if(inputMethod.toLowerCase().trim().equals("view personal accounts")) {
			
		}else {
			System.out.println("The input did not match any of the listed commands.");
		}
	}
	
	protected static void userMenu(User user) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to JDBC Bank, "+user.getFirstName()+" "+user.getLastName());
		System.out.println("How can we help you today?");
		System.out.println("Type out one of the following commands:");
		System.out.println("'VIEW ACCOUNTS'\t'VIEW TRANSACTIONS'\t'DELETE ACCOUNTS'");
		
		String inputMethod = input.nextLine();
		
		if(inputMethod.toLowerCase().trim().equals("view accounts")) {
			
		}else if(inputMethod.toLowerCase().trim().equals("view transactions")) {
			
		}else if(inputMethod.toLowerCase().trim().equals("delete accounts")) {
			
		}else {
			System.out.println("The input did not match any of the listed commands.");
		}
	}
	
}
