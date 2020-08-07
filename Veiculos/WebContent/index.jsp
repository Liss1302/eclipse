<%@page import="java.util.ArrayList"%>
<%@page import="model.Veiculo"%>
<%@page import="model.dao.VeiculoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Veiculos</title>
</head>
<body>

	<form method="POST">
		<input type="text" size="10" name="placa" placeholder="PLACA" /> <input
			type="text" size="10" name="marca" placeholder="MARCA" /> <input
			type="text" size="10" name="modelo" placeholder="MODELO" /> <input
			type="text" size="6" name="ano" placeholder="ANO" /> <input
			type="text" size="7" name="km" placeholder="KM" />
			<input type="submit" value="Enviar">
	</form>
	<table>
		<thead>
			<tr>
				<th>Placa</th>
				<th>Marca</th>
				<th>Modelo</th>
				<th>Ano</th>
				<th>Km</th>
			</tr>
		</thead>
		<tbody>

			<%
				//Cabeçalho
			Veiculo veiculo;
			VeiculoDAO vd = new VeiculoDAO();
			ArrayList<Veiculo> veiculos = vd.open();

			//Validando e Recebendo dados por request (Dados do frontEnd)
			String placa = request.getParameter("placa");
			String modelo = request.getParameter("modelo");
			String marca = request.getParameter("marca");
			int ano = 0;
			float km = 0;
			if (request.getParameter("ano") != null) {
				ano = Integer.parseInt(request.getParameter("ano"));
			}
			if (request.getParameter("km") != null) {
				km = Float.parseFloat(request.getParameter("km"));
			}

			//(CREATE) Cadastra um veículo no arquivo de Banco de Dados
			if (placa != null && modelo != null && marca != null && ano != 0 && km != 0) {
				veiculo = new Veiculo(); //Novo objeto vazio
				//Preencher o objeto configurando os dados recebido do front End
				veiculo.setPlaca(placa);
				veiculo.setModelo(modelo);
				veiculo.setMarca(marca);
				veiculo.setAno(ano);
				veiculo.setKm(km);
				if (!veiculos.contains(veiculo)) {//Se a lista não contem o novo veículo
					veiculos.add(veiculo);//Adicionar o veículo a lista
					vd.save(veiculos);//Salvar a nova lista no arquivo
					response.sendRedirect("#"); //Renova a página respondendo vazio #
				} else {
					out.print("Veículo já está cadastrado");
				}

			}

			//(READ) Retorna todos ou só um veículo conforme a placa.
			if (request.getParameter("placa") == null) {
				for (Veiculo v : veiculos) {
					out.print("<tr>" + v.toHTML() + "<td><input type='submit' value=' - '></td></tr>");
				}
			} else {
				veiculo = new Veiculo();
				veiculo.setPlaca(request.getParameter("placa"));
				if (veiculos.contains(veiculo)) {
					out.print("<tr>" + veiculos.get(veiculos.indexOf(veiculo)).toHTML() + "</tr>");
				} else {
					out.print("Placa não encontrada.");
				}
			}
			%>

		</tbody>
	</table>
</body>
</html>