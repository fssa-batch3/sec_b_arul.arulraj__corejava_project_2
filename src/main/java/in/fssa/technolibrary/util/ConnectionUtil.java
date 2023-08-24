package in.fssa.technolibrary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionUtil {
	/**
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		String url;
		String userName;
		String passWord;

		url = System.getenv("DATABASE_HOSTNAME");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

//          Local

//			DATABASE_HOSTNAME = jdbc:mysql://localhost:3306/techno_library
//			DATABASE_USERNAME = root
//			DATABASE_PASSWORD = 123456
		
//			cloud
		
//		    DATABASE_HOSTNAME = jdbc:mysql://164.52.216.41:3306/arul_ajun__corejava_project
//			DATABASE_USERNAME = 768wRMPz6lKf
//			DATABASE_PASSWORD = f559a84e-0199-4996-8b60-ed82e3abdb47

//            Dotenv env = Dotenv.load();
//            url = env.get("DATABASE_HOSTNAME");
//            userName = env.get("DATABASE_USERNAME");
//            passWord = env.get("DATABASE_PASSWORD");

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, passWord);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		}
		return connection;
	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 */
	public static void close(Connection connection, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @param connection
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
