package dynamic_beat_19;

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
	
	public boolean isUserLogin(String userID, String userPW) {
		try {
			String SQL = "SELECT * FROM user_info WHERE userID = '" + userID + "' and userPW = '" + userPW + "'";
			rs = st.executeQuery(SQL);
			
			return rs.next();
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
		return false;
	}
	
	public void setUserScore(String userID, int score) {
		try {
			String SQL = "UPDATE user_info SET score='" + String.valueOf(score) + "' WHERE userID='" + userID + "'";
			int result = st.executeUpdate(SQL);
			if(result == 1) {
				System.out.println(score + "점");
			}
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
	}
	
	public int getUserScore(String userID) {
		int score = 0;
		try {
			String SQL = "SELECT score FROM user_info WHERE userID = '" + userID + "'";
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				score = rs.getInt("score");
			}
			return score;
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
		return score;
	}
}
