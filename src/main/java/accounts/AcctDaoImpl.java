package accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import application.ConnectionUtil;
import application.NegativeNumericException;
import users.User;


public class AcctDaoImpl implements AcctDao {

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from accounts";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				boolean status;
				if(rs.getInt(5)==0) {status = true;}else {status = false;}
				Account account = new Account();
				account.setAccoutNumber(rs.getInt(1));
				account.setUserId(rs.getInt(2));
				account.setAccountBalance(rs.getFloat(3));
				account.setCreationDay(rs.getDate(4).toLocalDate());
				account.setOpen(status);
				accounts.add(account);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return accounts;
	}

	@Override
	public List<Account> getUserAccounts(User user) {
		List<Account> accounts = new ArrayList<Account>();
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from accounts where user_id = ? and is_closed = 0";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while(rs.next()) {
				boolean status;
				if(rs.getInt(5)==0) {status = true;}else {status = false;}
				Account account = new Account();
				account.setAccoutNumber(rs.getInt(1));
				account.setUserId(rs.getInt(2));
				account.setAccountBalance(rs.getFloat(3));
				account.setCreationDay(rs.getDate(4).toLocalDate());
				account.setOpen(status);
				accounts.add(account);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return accounts;
	}

	@Override
	public void createAccount(User user) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "call create_acct(?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Account created!");
	}

	@Override
	public void deleteAccount(User user, List<Account> accounts) {
		Connection con = ConnectionUtil.getConnection();

		int acctNum = selectAccountNum(accounts);
		
		String sql = "call close_acct(?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.setInt(2, acctNum);
			ps.executeQuery();
			System.out.println("Account Deleted");
		}catch(SQLException e) {
			System.out.println("Something happened");
		}
	}

	public static int selectAccountNum(List<Account> accounts) {
		int acctNum = 0;
		Scanner input = new Scanner(System.in);
		if(accounts.size() > 1) {
			boolean isInvalid = true;
			while(isInvalid) {
				System.out.println("Select Account:");
				try {
				acctNum = input.nextInt();
				if(acctNum < 0) {
					throw new NegativeNumericException();
				}
				}catch(InputMismatchException ime) {
					System.out.println("INPUT MUST BE INTEGER");
					return selectAccountNum(accounts);
				}catch(NegativeNumericException nne) {
					return selectAccountNum(accounts);
				}
				for(Account account : accounts) {
					if(account.getAccoutNumber() == acctNum) {
						return account.getAccoutNumber();
					}
				}
			}
		}else {
			return accounts.get(0).getAccoutNumber();
		}
		System.out.println("INVALID ACCOUNT NUMBER");
		return acctNum;
	}

}
