package controllers;

import java.util.ArrayList;

import models.Compra;
import models.dao.CompraDAO;

public class ProcessaCompra {

	private static ArrayList<Compra> compras = new ArrayList<>();
	private static CompraDAO cd = new CompraDAO();
	
	public static void abrir() {
		compras = cd.getCompras();
	}
	
	public static boolean salvar() {
		return cd.setCompras(compras);
	}
	
	public static ArrayList<Compra> getCompras() {
		return compras;
	}

	public static void setCompras(ArrayList<Compra> compras) {
		ProcessaCompra.compras = compras;
	}
	
	//Retorna o número da compra adicionando 1 ao ultimo número da lista
	public static int getAutoNumero() {
		if(ProcessaCompra.compras.isEmpty())
			return 1;
		else
			return ProcessaCompra.compras.get(ProcessaCompra.compras.size()-1).getNum()+1;
	}
	
}
