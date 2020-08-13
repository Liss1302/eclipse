package model;

public class Veiculo {

	private String placa, modelo, marca;
	private int ano;
	private float km;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public float getKm() {
		return km;
	}

	public void setKm(float km) {
		this.km = km;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

	//Banco de Dados Local (Arquivo de Texto)
	public String toCSV() {
		return placa + ";" + marca + ";" + modelo + ";" + ano + ";" + km + "\r\n";
	}
	
	//Front End (Colunas em uma tabela)
	public String toHTML() {
		return "<td>"+placa + "</td><td>" + marca + "</td><td>" + modelo + "</td><td>" + ano + "</td><td>" + String.format("%.1f", km) + "</td>";
	}

}
