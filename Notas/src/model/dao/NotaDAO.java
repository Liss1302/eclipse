package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Nota;

public class NotaDAO {
	//DAO (Data Access Object = Objeto de Acesso a Dados)
	private Nota nota;
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "E:\\eclipse\\git\\eclipse\\Notas\\bd\\notas.csv";
	private String[] campos;
	
	public boolean save(ArrayList<Nota> notas) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo));
			for(Nota n: notas) {
				bw.write(n.toCSV());
			}
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar "+e);
		}
		return retorno;
	}
	
	public ArrayList<Nota> open(){
		ArrayList<Nota> notas = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(arquivo));
				String linha = br.readLine();
				while(linha != null) {
					campos = linha.split(";");
					nota =  new Nota();
					nota.setAluno(campos[0]);
					nota.setNota(new float[] {Float.parseFloat(campos[1]),Float.parseFloat(campos[2]),Float.parseFloat(campos[3])});
					notas.add(nota);
					linha = br.readLine();
				}
				br.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo não foi encontrado.");
		} catch (IOException e) {
			System.out.println("Arquivo corrompido.");
		}
		return notas;
	}
}
