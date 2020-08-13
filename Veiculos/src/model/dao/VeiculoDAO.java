package model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Veiculo;

public class VeiculoDAO {

	private Veiculo veiculo;
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "E:\\eclipse\\git\\eclipse\\Veiculos\\bd\\veiculos.csv";
	private String[] campos;
	
	public boolean save(ArrayList<Veiculo> veiculos) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo,false));
			for(Veiculo v: veiculos) {
				bw.write(v.toCSV());
			}
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar "+e);
		}
		return retorno;
	}
	
	public ArrayList<Veiculo> open(){
		ArrayList<Veiculo> veiculos = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while(linha != null) {
				campos = linha.split(";");
				veiculo = new Veiculo();
				veiculo.setPlaca(campos[0]);
				veiculo.setMarca(campos[1]);
				veiculo.setModelo(campos[2]);
				veiculo.setAno(Integer.parseInt(campos[3]));
				veiculo.setKm(Float.parseFloat(campos[4]));
				veiculos.add(veiculo);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo não foi encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo: "+e);
		}
		return veiculos;
	}
}
