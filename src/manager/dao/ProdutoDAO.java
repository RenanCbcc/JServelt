package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import manager.models.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) throws SQLException {
		this.connection = connection;
		this.connection.setAutoCommit(false);

	}

	public void salvar(Produto produto) throws SQLException {

		String sql = "Insert into Produto(nome,descricao) values(?,?)";
		try (PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			adicionar(produto, statment);

			connection.commit();
		} catch (Exception e) {
			System.out.println(e);
			connection.rollback();
			System.out.println("Rollback efetuado");
		}

	}

	public List<Produto> listar() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		try (Statement statment = connection.createStatement()) {
			statment.executeQuery("Select * from Produto");
			try (ResultSet resultSet = statment.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					String desc = resultSet.getString("descricao");
					produtos.add(new Produto(id, nome, desc));
				}
				statment.close();
				resultSet.close();
				connection.close();
			}
		}
		return produtos;

	}

	private void adicionar(Produto produto, PreparedStatement statment) throws SQLException {

		statment.setString(1, produto.getNome());
		statment.setString(2, produto.getDescricao());

		statment.execute();
		ResultSet resultSet = statment.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}
		resultSet.close();

		statment.close();

	}

}
