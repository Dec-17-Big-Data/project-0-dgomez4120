
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectDatabase {

	public static void main(String[] args) throws SQLException{
		Connection con = ConnectionUtil.getConnection();
		
		String sql = "select * from users where username = ? and user_password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, "dgomez4120");
		ps.setString(2,"pssw0rd");
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			System.out.println(rs.getString("is_super"));
		}
		
	}
}
