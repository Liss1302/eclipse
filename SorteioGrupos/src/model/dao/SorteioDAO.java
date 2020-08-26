package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Sorteio;

public class SorteioDAO {
	private BufferedReader br;
	private BufferedWriter bw;
	private String path = "E:\\eclipse\\git\\eclipse\\SorteioGrupos\\";
	private String alunos = "bd\\alunos.csv";
	private String temas = "bd\\temas.csv";
	private String grupos = "bd\\grupos.csv";

	public ArrayList<String> openAlunos() {
		ArrayList<String> lista = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(path + alunos));
			String linha = br.readLine();
			while (linha != null) {
				lista.add(linha);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return lista;
	}

	public ArrayList<String> openTemas() {
		ArrayList<String> lista = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(path + temas));
			String linha = br.readLine();
			while (linha != null) {
				lista.add(linha);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return lista;
	}

	public boolean saveGrupos(ArrayList<Sorteio> sorteios) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(path + grupos));
			for (Sorteio s : sorteios) {
				bw.write(s.toCSV());
			}
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println(e);
		}
		return retorno;
	}

}
