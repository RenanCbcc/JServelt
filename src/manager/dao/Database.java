package manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static String url = "jdbc:mysql://localhost/loja_virtual"; // pattern:kind://ip_addres/database;
	private static String usuario = "root";
	private static String senha = "admin";


	static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(url, usuario, senha);
		return connection;
	}
}
