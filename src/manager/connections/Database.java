package manager.connections;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;



public class Database {
	private String url = "jdbc:mysql://localhost/loja_virtual"; // pattern:kind://ip_addres/database;
	private String usuario = "root";
	private String senha = "admin";
	private BasicDataSource  dataSource;

	public Database() throws PropertyVetoException, SQLException {
		dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(usuario);
        dataSource.setPassword(senha);
        dataSource.setInitialSize(1);
        Connection con = dataSource.getConnection();
        System.out.println("Connection Object information : " + con);	}

	public Connection getConnection() throws SQLException {
		Connection con = dataSource.getConnection();
		return con;
	}
}
