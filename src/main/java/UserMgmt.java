
import java.util.List;
import java.util.Scanner;

public class UserMgmt {
	
	private static Scanner input = new Scanner(System.in);
	
	protected static void administrative(User user) {
		UserDaoImpl userdi = new UserDaoImpl();
		AcctDaoImpl acctdi = new AcctDaoImpl();
		List<User> users = userdi.getAllUsers();
		List<Account> accts = acctdi.getAllAccounts();
		
		boolean isHere = true;
		do {
			System.out.println("Type in one of the following commands: \n"
					+ "'USERS'\t'CREATE USER'\t'DELETE USER'\t'PROMOTE USER'\t'ACCOUNTS'\t'MAIN MENU'");
			String commandInput = input.nextLine();
			
			if(commandInput.toLowerCase().trim().equals("users")) {
				userList(users);
			}else if(commandInput.toLowerCase().trim().equals("create user")) {
				userdi.createUser();
			}else if(commandInput.toLowerCase().trim().equals("delete user")) {
				deleteUser(userdi, users);
			}else if(commandInput.toLowerCase().trim().equals("promote user")) {
				promoteUser(userdi, users);
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
		System.out.println("User ID\tFirst Name\tLast Name\tUsername\tAdministrator\tActive");
		
		for(User user : users) {
			System.out.println(user.getUserId()+"\t"+user.getFirstName()+"\t\t"+user.getLastName()+"\t\t"+user.getUsername()+"\t"+user.isAdmin()+"\t"+user.isOpen());
		}
	}
	
	private static void deleteUser(UserDaoImpl userdi, List<User> users) {
		System.out.println("Which user would you like to delete?\n"
				+ "USER ID: ");
		int userId = input.nextInt();
		
		User user = userdi.getUser(userId, users);
		userdi.deleteUser(user);
	}
	
	private static void promoteUser(UserDaoImpl userdi, List<User> users) {
		System.out.println("Which user are you promoting?\n"
				+ "USER ID:");
		int userid = input.nextInt();
		
		User user = userdi.getUser(userid, users);
		userdi.updateUser(user);
		
	}
	
	private static void acctList(List<Account> accts) {
		System.out.println("Account Number\tUser ID\tAccout Creation\tStatus");
		for(Account account : accts){
			String status;
			if(account.isOpen()) {status = "Open";}else {status = "Closed";}
			System.out.println(account.getAccoutNumber()+"\t\t"+account.getUserId()+"\t"+account.getCreationDay()+"\t"+status);
		}
	}
	
}
