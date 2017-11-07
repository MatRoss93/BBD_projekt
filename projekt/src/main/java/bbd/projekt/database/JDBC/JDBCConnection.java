package bbd.projekt.database.JDBC;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class JDBCConnection {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://serwer1796722.home.pl/25678166_0000001";
	private static final String DB_USER = "25678166_0000001";
	private static final String DB_PASSWORD = "Monocentropus1";
	
	public static void main(String[] args) {
		try {
			selectRecordsFromTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		/*
		try {
			insertRecordIntoDb();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		*/
	}
	/*
	private static void insertRecordIntoDb() throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String insertTableSQL = "INSERT INTO MST(MSTO) VALUES ('CIECHOCINEK')";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(insertTableSQL);
			statement.executeUpdate(insertTableSQL);
			System.out.println("Record is inserted into table!");
		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}
	*/
	private static void selectRecordsFromTable() throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT NMST, MSTO FROM MST";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				String mstid = rs.getString("NMST");
				String mstname = rs.getString("MSTO");
				System.out.println("mstid: " + mstid);
				System.out.println("mstname: " + mstname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
