
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectDatabase {

	public static void main(String[] args) throws SQLException{
		Connection con = ConnectionUtil.getConnection();
		
		String sql = "SELECT * FROM USERS";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			System.out.println(rs.getString("username"));
		}
		
	}
}
