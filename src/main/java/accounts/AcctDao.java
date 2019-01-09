package accounts;

import java.util.List;
import users.User;

public interface AcctDao {
	
	public List<Account> getAllAccounts();
	public List<Account> getUserAccounts(User user);
	public void createAccount(User user);
	public void deleteAccount(User user, List<Account> account);
	
}
