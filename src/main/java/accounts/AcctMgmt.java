package accounts;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import application.Console;
import application.Menus;
import transactions.TranDaoImpl;
import transactions.Transaction;
import users.User;


public class AcctMgmt {
	
	private static Scanner input = new Scanner(System.in);
	
	public static void viewAcct(User user) throws SQLException{
		Console.clearScreen();
		AcctDaoImpl acctdi = new AcctDaoImpl();
		TranDaoImpl trandi = new TranDaoImpl();
		
		List<Account> accounts = acctdi.getUserAccounts(user);
		List<Transaction> transactions = trandi.getUserTransactions(user);
		
		
		while(accounts.size() == 0) {
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
			String acctNum = String.format("%05d", account.getAccoutNumber());
			String acctBal = String.format("%4.2f", account.getAccountBalance());
			System.out.println(acctNum+"\t\t\t"+account.getCreationDay()+"\t\t\t"+acctBal);	
		}
			
		boolean isAcctMenu = true;
			while(isAcctMenu) {
				System.out.println("Type in one of the following commands:\n"
						+ "'CREATE NEW ACCOUNT'\t'TRANSACTION HISTORY'\t'WITHDRAW'\n'DEPOSIT'\t'DELETE ACCOUNT'\t'MAIN MENU'\t'LOGOUT'");
				
				String inputMethod = input.nextLine();
				
				if(inputMethod.toLowerCase().trim().equals("create new account")) {
					acctdi.createAccount(user);
				}else if(inputMethod.toLowerCase().trim().equals("transaction history")) {
					transHist(user, transactions);
				}else if(inputMethod.toLowerCase().trim().equals("withdraw")){
					trandi.createTransaction(user, true, accounts);
					transactions = trandi.getUserTransactions(user);
				}else if(inputMethod.toLowerCase().trim().equals("deposit")) {
					trandi.createTransaction(user, false, accounts);
					transactions = trandi.getUserTransactions(user);
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
			System.out.println("Account Number \tTransaction Type \tTransaction Time\t\tAmount");
			for(Transaction transaction : transactions) {
				String transType;
				if(transaction.isWithdraw()) {transType = "Withdraw";}else {transType = "Deposit\t";}
				String acctNum = String.format("%05d", transaction.getAccountNumber());
				String tranAmt = String.format("%4.2f", transaction.getTransactionAmount());
				System.out.println(acctNum+"\t\t\t"+transType+"\t\t\t"+transaction.getTransactionDate()
					+" "+transaction.getTransactionTime()+"\t\t"+tranAmt);	
			}
	}

}
