package dynamic_beat_24;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			st.executeUpdate(SQL);
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
	
	public ArrayList<UserInfo> getUsersInfo() {
		ArrayList<UserInfo> usersInfo = new ArrayList<UserInfo>();
		
		try {
			String SQL = "SELECT userID, score FROM user_info ORDER BY score DESC LIMIT 3";
			rs = st.executeQuery(SQL);
			while(rs.next()) {
				usersInfo.add(new UserInfo(rs.getString("userID"), rs.getInt("score")));
			}
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
		
		return usersInfo;
	}
	
	public boolean isSameMemberID(String signUpID) {
		try {
			String SQL = "SELECT * FROM user_info WHERE userID = '" + signUpID + "'";
			rs = st.executeQuery(SQL);
			
			return rs.next();
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
		return false;
	}
	
	public boolean setUserInfo(String userID, String userPW, String userName, String userEmail) {
		try {
			String SQL = "INSERT INTO user_info (userID, userPW, userName, userEmail) VALUES ('" +  userID + "', '" + userPW
						+ "', '" + userName + "', '" + userEmail + "')";
			if(st.executeUpdate(SQL) == 1) return true;
		} catch(Exception e) {
			System.out.println("Database Connect error: " + e.getMessage());
		}
		
		return false;
	}
}
