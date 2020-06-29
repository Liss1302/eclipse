package views;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {

	private static Date agora = new Date();
	private static SimpleDateFormat hora = new SimpleDateFormat("hh:mm");
	private static SimpleDateFormat dia = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		System.out.println(dia.format(agora));
		System.out.println(hora.format(agora));
	}

}
