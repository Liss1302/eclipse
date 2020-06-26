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
	
}
