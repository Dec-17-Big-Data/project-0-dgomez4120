import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import accounts.Account;
import accounts.AcctDaoImpl;
import users.User;
import users.UserDaoImpl;


public class JDBCTest {

	private static UserDaoImpl userdi = new UserDaoImpl();
	private static AcctDaoImpl acctdi = new AcctDaoImpl();
	
	private static User user1 = new User(1, "Diego", "Gomez", "dgomez4120", true, true);
	private static User user2 = new User(2, "Anakin", "Skywalker", "hatesand66", false, false);
	private static User user3 = new User(3, "Luke", "Skywalker", "imajedi", false, true);
	private static User user4 = new User(4, "Leia", "Organa", "general1", false, true);
	private static User user5 = new User(5, "Diego", "Gomez", "dgomez56", true, true);
	private static User userCopy = new User(1, "Diego", "Gomez", "dgomez4120", true, true);
	private static User userFake = new User(1, "Diego", "Gomez", "dgomez41202", true, true);
	
	private static Account acct1 = new Account(1, 1, 3.5f, true, LocalDate.of(2019, 1, 9));
	private static Account acct2 = new Account(2, 2, 48.0f, true, LocalDate.of(2019, 1, 9));
	private static Account acct3 = new Account(3, 3, 0.0f, true, LocalDate.of(2019, 1, 9));
	private static Account acct4 = new Account(4, 3, 0.0f, true, LocalDate.of(2019, 1, 9));
	
	
	@Test
	public void isUserTest() {
		
		assertEquals(userdi.isUser("dgomez4120", "pssw0rd"), true); 
		assertEquals(userdi.isUser("random", "account"), false);
		assertEquals(userdi.isUser("general1", "p4ssw0rd"), true);
		
	}
	
	@Test
	public void userEqualTest() {
		

		assertTrue(user1.equals(userCopy));
		assertFalse(user1.equals(user2));
		assertFalse(user1.equals(userFake));
	}
	
	@Test
	public void getUsersListTest() {
		List<User> usersDB = userdi.getAllUsers();
		List<User> users = new ArrayList<User>();
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		
		Collections.sort(usersDB);
		Collections.sort(users);
		
		assertTrue(users.get(0).equals(usersDB.get(0)));
		assertTrue(users.get(1).equals(usersDB.get(1)));
		assertTrue(users.get(2).equals(usersDB.get(2)));
		assertTrue(users.get(3).equals(usersDB.get(3)));
		assertTrue(users.get(4).equals(usersDB.get(4)));
	}
	
	@Test
	public void getUserTest() {
		User getUser = userdi.getUser("general1", "p4ssw0rd");
		assertTrue(getUser.equals(user4));
		
		getUser = userdi.getUser("dgomez4120", "pssw0rd");
		assertTrue(getUser.equals(user1));
		
		getUser = userdi.getUser("general1", "p4ssw0rd");
		assertFalse(getUser.equals(user1));
	}
	
	@Test
	public void getUserFromListTest() {
		List<User> usersDB = userdi.getAllUsers();
		User user = userdi.getUser(1, usersDB);
		assertTrue(user1.equals(user));
		assertFalse(user2.equals(user));
		
		user = userdi.getUser(2, usersDB);
		assertTrue(user2.equals(user));
		assertFalse(user3.equals(user));
	}
	
	@Test
	public void updateUserTest() {
		userdi.updateUser(user5);
		user5.setIsAdmin(true);
		List<User> newUsers = userdi.getAllUsers();
		User user = userdi.getUser(5, newUsers);
		assertTrue(user.isAdmin());
	}
	
	@Test
	public void deleteUserTest() {
		userdi.deleteUser(user2);
		user2.setIsOpen(false);
		List<User> newUsers = userdi.getAllUsers();
		User user = userdi.getUser(2, newUsers);
		assertFalse(user.isOpen());
	}
	
	@Test
	public void getAllAccountsTest() {
		List<Account> acctsDB = acctdi.getAllAccounts();
		List<Account> accts = new ArrayList<Account>();
		
		accts.add(acct1);
		accts.add(acct2);
		accts.add(acct3);
		accts.add(acct4);
		
		Collections.sort(accts);
		Collections.sort(acctsDB);
		
		for(int i = 0; i < 4; i++) {
			assertTrue(accts.get(i).equals(acctsDB.get(i)));
		}
	}
	
	@Test
	public void getUserAcctTest() {
		List<Account> accts = new ArrayList<Account>();
		List<Account> acctsDB = acctdi.getUserAccounts(user3);
		accts.add(acct3);
		accts.add(acct4);
		
		Collections.sort(accts);
		Collections.sort(acctsDB);
		
		for(int i = 0; i < 2; i++) {
			assertTrue(accts.get(i).equals(acctsDB.get(i)));
		}
		
		List<Account> acctsNull = new ArrayList<Account>();
		acctsDB = acctdi.getUserAccounts(user4);
		
		assertThat(acctsNull,is(acctsDB)); 
	}
	
}
