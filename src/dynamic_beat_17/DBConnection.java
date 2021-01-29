package dynamic_beat_17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://122.202.45.140:3306/login", "lizill", "kc9452");
			st = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
	}
	
	public boolean isAdmin(String userID, String userPW) {
		try {
			String SQL = "SELECT * FROM user_info WHERE userID = '" + userID + "' and userPW = '" + userPW + "'";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
		return false;
	}
}
