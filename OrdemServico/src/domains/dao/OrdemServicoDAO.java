package domains.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domains.Funcionario;
import domains.OrdemServico;

public class OrdemServicoDAO {
	private OrdemServico ordenServico;
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "D:\\ARQUIVOS\\SENAI\\Planos_Ensino\\2020_2_Semestre\\PWBE\\workspace\\OrdemServico\\bd\\os.csv";
	private String[] campos;
	
	public boolean save(ArrayList<OrdemServico> ordensServico) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo,false));
			for(OrdemServico o: ordensServico) {
				bw.write(o.toCSV());
			}
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar "+e);
		}
		return retorno;
	}
	
	public ArrayList<OrdemServico> open(){
		ArrayList<OrdemServico> ordensServico = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while(linha != null) {
				campos = linha.split(";");
				ordenServico = new OrdemServico();
				ordenServico.setId(Integer.parseInt(campos[0]));
				ordenServico.setDataAgendamento(campos[1]);
				ordenServico.setDataExecucao(campos[2]);
				ordenServico.setTotalHoras(Integer.parseInt(campos[3]));
				ordenServico.setEndereco(campos[4]);
				ordenServico.setFuncionario(new Funcionario(Integer.parseInt(campos[5])));
				ordensServico.add(ordenServico);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo não foi encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo: "+e);
		}
		return ordensServico;
	}
}
