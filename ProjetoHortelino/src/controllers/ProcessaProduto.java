package controllers;

import java.util.ArrayList;

import models.Produto;
import models.dao.ProdutoDAO;

public class ProcessaProduto {

	private static ProdutoDAO pd = new ProdutoDAO();
	private static ArrayList<Produto> produtos = pd.open();
		
	//GETs && SETs
	public static ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public static void setProdutos(ArrayList<Produto> produtos) {
		ProcessaProduto.produtos = produtos;
		pd.save(produtos);
	}
	
	//Retorna o código do produto adicionando 1 ao ultimo codigo da lista
	public static int getAutoCodigo() {
		if(ProcessaProduto.produtos.isEmpty())
			return 1;
		else
			return ProcessaProduto.produtos.get(ProcessaProduto.produtos.size()-1).getCodigo()+1;
	}
	
}
