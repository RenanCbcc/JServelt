package manager.connections;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import manager.dao.ProdutoDAO;
import manager.models.Produto;

public class ConnectionManager {

	public static void main(String[] args) throws SQLException, PropertyVetoException {

		// testarExibicao();
		// TestarInclusao();
		// testarExclusao();
		// testarInclusaoSemInjecao();
		// testarInclusaoSemautoCommit();
		// testarPool();
		testarDao();
	}

	private static void testarDao() throws SQLException, PropertyVetoException {

		try (Connection connection = new Database().getConnection()) {

			ProdutoDAO dao = new ProdutoDAO(connection);
			dao.salvar(new Produto("Cama bordeau", "cama king sized"));
			dao.salvar(new Produto("Fogao steam", "fogao innox 5 bocas"));
			for (Produto p : dao.listar()) {
				System.out.println(p);
			}

		} catch (Exception e) {
		}

	}

	private static void TestarPool() throws SQLException, PropertyVetoException {
		Database database = new Database();
		for (int i = 0; i < 100; i++) {
			try (Connection connection = database.getConnection()) {
				connection.setAutoCommit(false);
				String sql = "Insert into Produto(nome,descricao) values(?,?)";
				try (PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					adicionar("Microondas 'fire", "microondas innox 600 wats", statment);

					connection.commit();
				} catch (Exception e) {
					System.out.println(e);
					connection.rollback();
					System.out.println("Rollback efetuado");
				}

			}

		}

	}

	public static void testarExibicao() throws SQLException, PropertyVetoException {
		Database database = new Database();
		Connection connection = database.getConnection();
		Statement statment = connection.createStatement();
		// boolean result = statement.execute("Select * from Produto");// true if
		// various lines were returned false if
		// various lines were just altered.
		ResultSet resultSet = statment.executeQuery("Select * from Produto");

		while (resultSet.next()) {
			System.out.println(
					resultSet.getString("id") + resultSet.getString("nome") + resultSet.getString("descricao"));
		}

		statment.close();
		resultSet.close();
		connection.close();

	}

	public static void testarInclusao() throws SQLException, PropertyVetoException {

		Connection connection = new Database().getConnection();

		Statement statment = connection.createStatement();
		boolean result = statment.execute(
				"Insert into Produto(nome,descricao) values('Notebook','Notebook I5 quadcore')",
				Statement.RETURN_GENERATED_KEYS);

		if (!result) {
			System.out.println("Produto adcionado com sucesso!");
			ResultSet resultSet = statment.getGeneratedKeys();
			String nomeColuna = resultSet.getMetaData().getColumnName(1);
			System.out.println("nomeColuna = " + nomeColuna);

			while (resultSet.next()) {
				System.out.println(resultSet.getString("GENERATED_KEY"));
			}
			resultSet.close();
		}

		statment.close();

		connection.close();

	}

	public static void testarExclusao() throws SQLException, PropertyVetoException {
		Database database = new Database();
		Connection connection = database.getConnection();
		Statement statment = connection.createStatement();
		statment.execute("Delete from Produto where id>3");
		int count = statment.getUpdateCount();
		System.out.println(count + " linhas atualizadas");
		connection.close();
	}

	public static void testarInclusaoSemInjecao() throws SQLException, PropertyVetoException {
		String nome = "Microondas 'fire";
		String desc = "microondas innox 600 wats";

		Connection connection = new Database().getConnection();

		String sql = "Insert into Produto(nome,descricao) values(?,?)";
		PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statment.setString(1, nome);
		statment.setString(2, desc);

		statment.execute();
		ResultSet resultSet = statment.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}
		resultSet.close();

		statment.close();

		connection.close();

	}

	public static void testarInclusaoSemautoCommit() throws SQLException, PropertyVetoException {

		try (Connection connection = new Database().getConnection()) {
			connection.setAutoCommit(false);
			String sql = "Insert into Produto(nome,descricao) values(?,?)";
			try (PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				adicionar("Microondas 'fire", "microondas innox 600 wats", statment);
				adicionar("Fogao 'steam", "fogao aluminium 5 bocas", statment);
				connection.commit();
			} catch (Exception e) {
				System.out.println(e);
				connection.rollback();
				System.out.println("Rollback efetuado");
			}

		}
	}

	private static void adicionar(String nome, String desc, PreparedStatement statment) throws SQLException {

		if (nome.equals("Fogao 'steam")) {
			throw new IllegalArgumentException("Erro private static void adiciona");
		}
		statment.setString(1, nome);
		statment.setString(2, desc);

		statment.execute();
		ResultSet resultSet = statment.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}
		resultSet.close();

		statment.close();

	}

}
