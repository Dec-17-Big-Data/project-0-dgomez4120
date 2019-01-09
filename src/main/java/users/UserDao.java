package users;

import java.sql.SQLException;
import java.util.List;


public interface UserDao {
	
	public List<User> getAllUsers();
	public void updateUser(User user);
	public void deleteUser(User user);
	public User createUser() throws SQLException;
	public boolean isUser(String username, String password);
	public User getUser(String username, String password);
	public User getUser(int userId, List<User> users);
	
}
