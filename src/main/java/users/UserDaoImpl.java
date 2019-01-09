package users;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.ConnectionUtil;

public class UserDaoImpl implements UserDao {
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>(); 
		Connection con = ConnectionUtil.getConnection();
		String sql = "select user_id, first_name, last_name, username, is_super, is_open from users";
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				boolean isAdmin, isOpen;
				if(rs.getInt(5) == 1) {isAdmin = true;}else {isAdmin = false;}
				if(rs.getInt(6) == 1) {isOpen = true;}else {isOpen = false;}
				int userId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String username = rs.getString(4);
				User newUser = new User(userId,firstName,lastName,username,isAdmin,isOpen);
				users.add(newUser);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try{
				if(con!=null) {
					con.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		return users;
	}

	@Override
	public boolean isUser(String username, String password) {
		Connection con = ConnectionUtil.getConnection();

		boolean logged_in = false;
		String sql = "select * from users where username = ? and user_password = ?";
			
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				logged_in = username.equals(rs.getString("username")) && password.equals(rs.getString("user_password"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return logged_in;
	}

	@Override
	public void updateUser(User user) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "{call promote_super(?)}";
		CallableStatement cstmt;
		
		try {
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, user.getUserId());
			cstmt.execute();
			System.out.println("User Promoted");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(con!=null) {
					con.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}

	}

	@Override
	public void deleteUser(User user) {
		Connection con = ConnectionUtil.getConnection();
		String sql = "{call delete_user(?)}";
		CallableStatement cstmt;
		
		try {
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, user.getUserId());
			cstmt.execute();
			System.out.println("User Profile Closed");
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public User createUser(){
		Connection con = ConnectionUtil.getConnection();
		User newRegister = new User();
		String password = "";
		
		Scanner input = new Scanner(System.in);
		boolean isLegalPassword = false;
		System.out.println("Enter a valid password:\n"+
				"*A valid password can contain letters, numbers, and the following symbols: [!,@,#,$,*,&]*");
		while(!isLegalPassword) {
			String inputPassword = input.next();
			if(!inputPassword.matches("[a-zA-Z0-9|!|@|#|$|*|&]*$")) {
				System.out.println("INVALID PASSWORD\nEnter a valid password:"
						+"\n*A valid password can contain letters, numbers, and the following symbols: [!,@,#,$,*,&]*");
			}else {
				isLegalPassword = true;
				password = inputPassword;
			}
		}
		
		String sql = "call create_user(?, ?, ?, ?)";
		
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,newRegister.getFirstName());
			ps.setString(2, newRegister.getLastName());
			ps.setString(3, newRegister.getUsername());
			ps.setString(4, password);
			ps.executeQuery();
			System.out.println("User Registered");
		}catch(SQLException e) {
			System.out.println("REGISTRATION FAILED");
		}finally {
			
		}
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			sql = "select user_id_sequence.currval from dual";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				newRegister.setUserId(rs.getInt(1));
			}
		}catch(SQLException e) {
			System.out.println("ID ASSIGNMENT FAILED");
		}
		
		return newRegister;
	}

	@Override
	public User getUser(String username, String password) {
		User loginAcct = null;
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from users where username = ? and user_password = ?";
		
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				boolean isSuper, isOpen;
				if(rs.getInt("is_super") == 1) {isSuper = true;}else {isSuper = false;}
				if(rs.getInt("is_open") == 1) {isOpen = true;}else {isOpen = false;}
				loginAcct = new User(userId, firstName, lastName,username, isSuper, isOpen);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			try {
				if(con != null) {
					con.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		return loginAcct;
	}

	@Override
	public User getUser(int userId, List<User> users) {
		for(User user : users) {
			if(user.getUserId() == userId) {
				return user;
			 }
		}
		System.out.println("USER NOT FOUND");
		return null;
	}

}
