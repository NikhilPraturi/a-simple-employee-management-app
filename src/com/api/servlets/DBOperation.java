package com.api.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperation {

	private final String url = "jdbc:postgresql://localhost:5432/postgres";
	private final String user = "postgres";
	private final String password = "6765";

	private Connection connection;

	public DBOperation() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(url, user, password);
	}

	public int addEmployee(String name, String phone, String supervisors) throws SQLException {
		if (name == null || name.isEmpty()) {
			return 0;
		}

		if (phone == null || phone.isEmpty()) {
			return 0;
		}

		if (supervisors == null || supervisors.isEmpty()) {
			return 0;
		}

		Statement statement = connection.createStatement();

		String cmd = String.format("INSERT INTO EMPLOYEE (name, phone_number, supervisors) VALUES ('%s','%s', '%s');",

		name, phone, supervisors);

		int count = statement.executeUpdate(cmd);

		statement.close();

		return count;
	}

	public int removeEmployee(String id) throws SQLException {
		Statement statement = connection.createStatement();

		String cmd = String.format("Delete from EMPLOYEE where employee_id='%s';", id);

		int count = statement.executeUpdate(cmd);

		statement.close();

		return count;

	}

	public boolean find(String username, String password2) {

		String cmd = String.format("SELECT * from USERINFO where username='%s' AND password='%s';", username,
				password2);

		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(cmd);

			boolean result = rs.next();

			statement.close();

			rs.close();

			return result;

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		DBOperation app = new DBOperation();

		app.addEmployee("liu bang", "0103888888", "Qin Lang");
		app.removeEmployee("9");
		app.removeEmployee("12");
		app.removeEmployee("13");

		System.out.println(app.find("admin", "admin"));

		System.out.println(app.find("admin", "admi"));
	}

}
