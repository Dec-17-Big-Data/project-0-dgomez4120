import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AcctMgmt {
	private static Connection con = ConnectionUtil.getConnection();
	
	private static Scanner input = new Scanner(System.in);
	
	protected static void viewAcct(User user) throws SQLException{
		Console.clearScreen();
		AcctDaoImpl acctdi = new AcctDaoImpl();
		TranDaoImpl trandi = new TranDaoImpl();
		
		List<Account> accounts = acctdi.getUserAccounts(user);
		List<Transaction> transactions = trandi.getUserTransactions(user);
		
		
		if(accounts.size() == 0) {
			Console.clearScreen();
			System.out.println("Type in one of the following commands: \n"
					+ "'CREATE NEW ACCOUNT'\t'MAIN MENU'\t'LOGOUT'");
			String inputMethod = input.nextLine();
			
			if(inputMethod.toLowerCase().trim().equals("create new account")) {
				acctdi.createAccount(user);
				accounts = acctdi.getUserAccounts(user);
			}else if(inputMethod.toLowerCase().trim().equals("main menu")) {
				Menus.toUserMenu(user);
			}else if(inputMethod.toLowerCase().trim().equals("logout")) {
				Console.main(null);
			}else {
				System.out.println("The input was not one of the listed commands.");
			}
		}
		
		System.out.println("Account Number \tAccount Creation \tBalance");
		for(Account account : accounts) {
			System.out.println(account.getAccoutNumber()+"\t\t"+account.getCreationDay()+"\t\t"+account.getAccountBalance());	
		}
			
		boolean isAcctMenu = true;
			while(isAcctMenu) {
				System.out.println("Type in one of the following commands:\n"
						+ "'CREATE NEW ACCOUNT'\t'TRANSACTION HISTORY'\t'WITHDRAW'\t'DEPOSIT'\t'DELETE ACCOUNT'\t'MAIN MENU'\t'LOGOUT'");
				
				String inputMethod = input.nextLine();
				
				if(inputMethod.toLowerCase().trim().equals("create new account")) {
					acctdi.createAccount(user);
				}else if(inputMethod.toLowerCase().trim().equals("transaction history")) {
					transHist(user, transactions);
				}else if(inputMethod.toLowerCase().trim().equals("withdraw")){
					trandi.createTransaction(user, true, accounts);
				}else if(inputMethod.toLowerCase().trim().equals("deposit")) {
					trandi.createTransaction(user, false, accounts);
				}else if(inputMethod.toLowerCase().trim().equals("delete account")) {
					acctdi.deleteAccount(user, accounts);
				}else if(inputMethod.toLowerCase().trim().equals("main menu")) {
					isAcctMenu = false;
					Menus.toUserMenu(user);
				}else if(inputMethod.toLowerCase().trim().equals("logout")) {
					isAcctMenu = false;
					Console.main(null);
				}else {
					System.out.println("The input was not one of the listed commands.");
				}
			}
		
	}
 static void transHist(User user, List<Transaction> transactions) {
			System.out.println("Account Number \tTransaction Type \tTransaction Time \tAmount");
			for(Transaction transaction : transactions) {
				String transType;
				if(transaction.isWithdraw()) {transType = "Withdraw";}else {transType = "Deposit\t";}
				System.out.println(transaction.getAccountNumber()+"\t\t"+transType+"\t\t"+transaction.getTransactionDate()
					+" "+transaction.getTransactionTime()+"\t"+transaction.getTransactionAmount());	
			}
	}

}
