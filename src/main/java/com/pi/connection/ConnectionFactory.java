package com.pi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/database";
	private static final String USER = "root";
	private static final String PASS = "123456";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver nao achado", e);
		} catch (SQLException e) {
			throw new RuntimeException("Erro na conexão", e);
		}
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("Erro em fechar a conexão", e);
			}
		}
	}

	public static void closeConnection(Connection connection, PreparedStatement stmt) {
		closeConnection(connection);

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException("Erro em fechar a conexão(stmt)", e);
			}
		}
	}

	public static void closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs) {

		closeConnection(connection, stmt);

		try {

			if (rs != null) {
				rs.close();
			}

		} catch (SQLException ex) {
			throw new RuntimeException("Erro em fechar a conexão(rs)", ex);
		}
	}

}
