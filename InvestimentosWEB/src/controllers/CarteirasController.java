package controllers;

import java.util.ArrayList;

import dao.CarteiraDAO;
import model.Carteira;

public class CarteirasController {
	
	private static CarteiraDAO cd = new CarteiraDAO();
	private static ArrayList<Carteira> carteiras = cd.listarTodas();
	
	public static ArrayList<Carteira> getCarteiras() {
		carteiras = cd.listarTodas();
		return carteiras;
	}
	public static void setCarteiras(ArrayList<Carteira> carteiras) {
		CarteirasController.carteiras = carteiras;
	}	
	
}
