package model;

import java.util.Arrays;

public class Nota {

	private String aluno;
	private float[] nota = new float[3];

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public float[] getNota() {
		return nota;
	}

	public void setNota(float[] nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nota [aluno=" + aluno + ", nota=" + Arrays.toString(nota) + "]";
	}

	public String toCSV() {
		return aluno + ";" + nota[0] + ";" + nota[1] + ";" + nota[2] + "\r\n";
	}

	public float getMedia() {
		return (nota[0] + nota[1] + nota[2]) / 3;
	}
}
