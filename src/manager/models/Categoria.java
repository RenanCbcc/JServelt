package manager.models;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String descricao;
	private final List<Produto> listaProdutos;

	public Categoria(Integer id, String descricao) {
		this.listaProdutos = new ArrayList<Produto>();
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descricao=" + descricao + ", listaProdutos=" + listaProdutos + "]";
	}

	public List<Produto> getProdutos() {
		return this.listaProdutos;
	}

	public void adiciona(Produto p) {
		this.listaProdutos.add(p);

	}

}
