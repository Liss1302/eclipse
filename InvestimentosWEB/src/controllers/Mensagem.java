package controllers;

import java.util.ArrayList;

//Controla as mensagens em formato de FILA "FiFo (first in, first out)". 
public class Mensagem {
	private static ArrayList<String> mensagens = new ArrayList<>();

	public static void addMensagem(String m) {
		mensagens.add(m);
	}

	public static ArrayList<String> getMensagens() {
		return mensagens;
	}

	public static String getMensagem() {
		if (!mensagens.isEmpty()) {
			return mensagens.remove(0);
		}
		return null;
	}
}
