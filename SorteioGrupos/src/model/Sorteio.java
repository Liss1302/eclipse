package model;

public class Sorteio {
	
	private String aluno, tema;

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String toCSV() {
		return aluno+";"+tema+"\r\n";
	}
}
