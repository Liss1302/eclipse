package domains;

public class OrdemServico {

	private int id;
	private String dataAgendamento;
	private String dataExecucao;
	private int totalHoras;
	private String endereco;
	private Funcionario funcionario;

	public OrdemServico() {
	}

	public OrdemServico(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public int getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		OrdemServico other = (OrdemServico) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toCSV() {
		return id + ";" + dataAgendamento + ";" + dataExecucao + ";" + totalHoras + ";" + endereco + ";"
				+ funcionario.getId() + "\r\n";
	}

	public String toHTML() {
		return "<td>" + id + "</td><td>" + dataAgendamento + "</td><td>" + dataExecucao + "</td><td>" + totalHoras
				+ "</td><td>" + endereco + "</td><td>" + funcionario.getId() + ", " + funcionario.getNome() + "</td>";
	}

}
