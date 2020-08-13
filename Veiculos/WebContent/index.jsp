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
			type="text" size="7" name="km" placeholder="KM" /> <input
			type="submit" value="Enviar">
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

			//(CREATE UPDATE) Cadastra um veículo no arquivo de Banco de Dados
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
					veiculos.set(veiculos.indexOf(veiculo), veiculo);
					out.print("Veículo já está cadastrado e foi atualizado");
				}
			}

			//(READ) Retorna todos ou só um veículo conforme a placa.
			if (placa == null) {
				//Todos
				for (Veiculo v : veiculos) {
					out.print("<tr>");//Inicio da linha da tabela
					out.print(v.toHTML()); //Percorre toda a lista
					out.print("<form method='POST'>");
					out.print("<td><input type='hidden' name='delete' value='" + v.getPlaca() + "' ></td>");
					out.print("<td><input type='submit' value=	' - '></td>");
					out.print("</form>");
					out.print("</tr>");//Fim da linha da tabela
				}
			} else {
				veiculo = new Veiculo();
				veiculo.setPlaca(request.getParameter("placa"));
				if (veiculos.contains(veiculo)) {
					//Só um
					out.print("<tr>");
					out.print(veiculos.get(veiculos.indexOf(veiculo)).toHTML());
					out.print("<td><input type='submit' value=	' - '></td>");
					out.print("</tr>");
				} else {
					out.print("Placa não encontrada.");
				}
			}

			//(DELETE)
			if (request.getParameter("delete") != null) {
				veiculo = new Veiculo();
				veiculo.setPlaca(request.getParameter("delete"));
				veiculos.remove(veiculos.indexOf(veiculo));
				vd.save(veiculos);
				out.print("Veículo removido com sucesso");
				response.sendRedirect("#"); //Renova a página respondendo vazio #
			}
			%>

		</tbody>
	</table>
</body>
</html>