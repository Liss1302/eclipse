<%@page import="controllers.FuncionarioServlet"%>
<%@page import="controllers.OrdemServicoServlet"%>
<%@page import="domains.OrdemServico"%>
<%@page import="domains.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Ordens de Serviço</title>
<style>
form {
	display: flex;
	flex-direction: row;
}

form input {
	padding: 10px;
	border-radius: 4px;
}

form input[type=text] {
	width: 130px;
}

form input[type=number] {
	width: 80px;
}

table {
	display: block;
}

table th {
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 10px;
	margin: 10px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	font-size: 14px;
	text-align: center;
	background-color: #eee;
}

table td {
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 10px;
	margin: 10px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	font-size: 14px;
	text-align: center;
}

.msg {
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 760px;
	padding: 10px;
	margin: 10px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	font-size: 14px;
	text-align: center;
}
</style>
</head>
<body>
	<form method="POST" action="os">
		<input type="text" onfocus="(this.type='date')" name="dataAgendamento"
			placeholder="Data de Agendamento" /> <input type="text"
			onfocus="(this.type='date')" name="dataExecucao"
			placeholder="Data de Execução" /> <input type="number"
			name="totalHoras" placeholder="Total de Horas" /> <input type="text"
			name="endereco" placeholder="Endereço do Serviço" /> <select
			name="idFuncionario"><option value="0">Escolha o
				Funcionário</option>
			<%
				for (Funcionario f : FuncionarioServlet.getFuncionarios()) {
				out.print("<option value='" + f.getId() + "'>");
				out.print(f.getId() + "- " + f.getNome());
				out.print("</option>");
			}
			%>
		</select><input type="submit" value="Enviar">
	</form>
	<div class="msg">
		<%
			//Mensagens do sistema
		if (request.getParameter("mensagem") != null) {
			out.print(request.getParameter("mensagem"));
			out.print("<script>setTimeout(() => {window.location.href='os.jsp'}, 3000);</script>");
		} else {
			out.print("Mensagens do sistema.");
		}
		%>
	</div>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Agendado para:</th>
				<th>Executado em:</th>
				<th>N. de horas</th>
				<th>Endereço do Serviço</th>
				<th>Funcionário</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<%
				//Corpo da tabela
			for (OrdemServico o : OrdemServicoServlet.getOrdensServico()) {
				out.print("<tr>");
				out.print(o.toHTML());
				out.print("<td>");
				out.print("<button onclick='del(this)'>Excluir</button>");
				out.print("<button onclick='put(this)'>Alterar</button>");
				out.print("</td>");
				out.print("</tr>");
			}
			%>
		</tbody>
	</table>
	<script>
		//Funções JavaScript para Alterar e Excluir
		function del(e) {
			let id = e.parentNode.parentNode.cells[0].innerText;
			//Abre uma janela confirmando a edição
			if (window.confirm("Confirma Exclusão do id " + id + "?")) {
				//Envia a requisição de DELETE via XML utilizando o verbo correto "DELETE"
				let xhr = new XMLHttpRequest();
				xhr
						.addEventListener(
								"readystatechange",
								function() {
									if (this.readyState === this.DONE) {
										window.location.href = "os.jsp?mensagem=Excluído com sucesso";
										//window.location.reload();
										//alert(this.responseText); 
									}
								});
				let url = "os?id=" + id; //Monta a URL concatenando o ID
				xhr.open("DELETE", url);
				xhr.send(); //Envia a requisição ao Servlet REST
			}
		}

		function put(e) {
			e.parentNode.parentNode.cells[1].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[2].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[3].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[4].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[5].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[6].innerHTML = "<button onclick='send(this)'>Concluir</button>";
		}

		function send(e) {
			let id = e.parentNode.parentNode.cells[0].innerText;
			let dataAgendamento = e.parentNode.parentNode.cells[1].innerText;
			let dataExecucao = e.parentNode.parentNode.cells[2].innerText;
			let totalHoras = e.parentNode.parentNode.cells[3].innerText;
			let endereco = e.parentNode.parentNode.cells[4].innerText;
			let funcionario = e.parentNode.parentNode.cells[5].innerText;
			let idFuncionario = funcionario.split(",")[0];
			let url = "os?id=" + id + "&dataAgendamento=" + dataAgendamento
					+ "&dataExecucao=" + dataExecucao + "&totalHoras="
					+ totalHoras + "&endereco=" + endereco + "&idFuncionario="
					+ idFuncionario;
			let xm = new XMLHttpRequest();
			xm
					.addEventListener(
							"readystatechange",
							function() {
								if (this.readyState === this.DONE) {
									window.location.href = "os.jsp?mensagem=Alterado com sucesso";
									//window.location.reload();
								}
							});
			xm.open("PUT", url);
			xm.send();
		}
	</script>
</body>
</html>