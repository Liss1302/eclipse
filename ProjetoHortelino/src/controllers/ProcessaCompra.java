package controllers;

import java.util.ArrayList;

import models.Compra;
import models.dao.CompraDAO;

public class ProcessaCompra {

	private static CompraDAO cd = new CompraDAO();
	private static ArrayList<Compra> compras = cd.open();

	public static ArrayList<Compra> getCompras() {
		return compras;
	}

	public static void setCompras(ArrayList<Compra> compras) {
		ProcessaCompra.compras = compras;
		cd.save(compras);
	}
	
	//Retorna o n�mero da compra adicionando 1 ao ultimo n�mero da lista
	public static int getAutoNumero() {
		if(ProcessaCompra.compras.isEmpty())
			return 1;
		else
			return ProcessaCompra.compras.get(ProcessaCompra.compras.size()-1).getNum()+1;
	}
	
}
