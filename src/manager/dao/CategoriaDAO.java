package manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import manager.models.Categoria;
import manager.models.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) throws SQLException {
		this.connection = connection;
		this.connection.setAutoCommit(false);

	}

	public void salvar(Categoria categoria) throws SQLException {

		String sql = "Insert into Categoria(descricao) values(?)";
		try (PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			adicionar(categoria, statment);

			connection.commit();
		} catch (Exception e) {
			System.out.println(e);
			connection.rollback();
			System.out.println("Rollback efetuado");
		}

	}

	public List<Categoria> listar() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		try (Statement statment = connection.createStatement()) {
			String sql = "select c.id as c_id, c.descricao as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao from Categoria as c join Produto as p on p.categoria_id = c.id";
			statment.executeQuery(sql);
			try (ResultSet resultSet = statment.getResultSet()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("c_id");
					String desc = resultSet.getString("c_nome");
					if (ultima == null || !ultima.getDescricao().equals(desc)) {
						Categoria c = new Categoria(id, desc);
						categorias.add(c);
						ultima = c;
					}
					int idProduto = resultSet.getInt("p_id");
					String nomeProduto = resultSet.getString("p_nome");
					String descProduto = resultSet.getString("p_descricao");
					Produto p = new Produto(idProduto,nomeProduto,descProduto); 
					ultima.adiciona(p);
				}
				 
				statment.close();
				resultSet.close();
				connection.close();
			}
		}
		return categorias;

	}


	private void tranformaResultadosEmProdutos(List<Produto> produtos, PreparedStatement statment) throws SQLException {
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

	public List<Produto> buscar(Categoria categoria) throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "Select * from Produto where categoria_id = ?";
		try (PreparedStatement statment = connection.prepareStatement(sql)) {
			statment.setInt(1, categoria.getId());
			statment.executeQuery();
			tranformaResultadosEmProdutos(produtos, statment);
		}
		return produtos;

	}

	private void adicionar(Categoria categoria, PreparedStatement statment) throws SQLException {

		statment.setString(1, categoria.getDescricao());

		statment.execute();
		ResultSet resultSet = statment.getGeneratedKeys();

		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}
		resultSet.close();

		statment.close();

	}

}
