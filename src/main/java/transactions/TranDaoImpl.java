package transactions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import accounts.AcctDaoImpl;
import accounts.Account;
import application.ConnectionUtil;
import application.NegativeNumericException;
import users.User;

public class TranDaoImpl implements TransactionDao{

	@Override
	public List<Transaction> getUserTransactions(User user) {
		List<Transaction> transactions = new ArrayList<Transaction>(); 
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from transactions where user_id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Transaction transaction = new Transaction();
				transaction.setTransactionId(rs.getInt(1));
				transaction.setUserID(rs.getInt(2));
				transaction.setAccountNumber(rs.getInt(3));
				if(rs.getInt(4) == 1) {
					transaction.setWithdraw(true);
				}else {
					transaction.setWithdraw(false);
				}
				transaction.setTransactionAmount(rs.getFloat(5));
				transaction.setTransactionDate(rs.getDate(6).toLocalDate());
				transaction.setTransactionTime(rs.getTime(6).toLocalTime());
				
				transactions.add(transaction);
			}
		}catch(SQLException e) {
			System.out.println("Something happened");
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return transactions;
	}

	@Override
	public void createTransaction(User user, boolean isWithdraw, List<Account> accounts) {
		Connection con = ConnectionUtil.getConnection();
		Scanner input = new Scanner(System.in);
		
		int acctNum = AcctDaoImpl.selectAccountNum(accounts);
		
		float transAmt = 0;
		try {
			System.out.println("Amount:");
			transAmt= input.nextFloat();
			if(transAmt < 0) {
				throw new NegativeNumericException();
			}
		}catch(InputMismatchException ime) {
			System.out.println("INPUT MUST BE NUMERIC");
			createTransaction(user,isWithdraw,accounts);
			return;
		}catch(NegativeNumericException nne) {
			createTransaction(user,isWithdraw,accounts);
			return;
		}
		
	
		String sql = "{call perform_transaction(?, ?, ?, ?, ?)}";
		CallableStatement cstmt;
		
		int booleanSQL;
		if(isWithdraw) {
			booleanSQL = 1;
		}else {
			booleanSQL = 0;
		}
		
		try {
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, acctNum);
			cstmt.setInt(2, booleanSQL);
			cstmt.setFloat(3, transAmt);
			cstmt.setInt(4, user.getUserId());
			cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
			cstmt.execute();
			int Successful = cstmt.getInt(5);
			if(Successful == 1) {
				System.out.println("Success");
			}else if(Successful == 2){
				System.out.println("OVERDRAW: TRANSACTION CANCELED");
			}else {
				System.out.println("ERROR");
			}
		}catch(SQLException e) {
			System.out.println("Something happened");
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

}
