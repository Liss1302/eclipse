<%@page import="controllers.FuncionarioServlet"%>
<%@page import="domains.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Funcionários</title>
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

form select {
	width: 160px;
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
	width: 1050px;
	padding: 10px;
	margin: 10px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	font-size: 14px;
	text-align: center;
}
</style>
</head>
<body>

	<form method="POST" action="funcionarios">
		<input type="text" name="nome" placeholder="Nome" /> <input
			type="text" onfocus="(this.type='date')" name="nascimento" placeholder="Nascimento" />
		<input type="text" name="endereco" placeholder="Endereço" /> <input
			type="text" name="especialidade" placeholder="Especialidade" /> <input
			type="text" name="competencias"
			placeholder="Competências separadas por vírcula," /> <select
			name="periodo"><option>Escolha o período</option>
			<option>Manhã</option>
			<option>Tarde</option>
			<option>Noite</option></select> <input type="number" name="valorHora"
			placeholder="Valor Hora" /> <input type="submit" value="Enviar">
	</form>
	<div class="msg">
		<%
			//Mensagens do sistema
		if (request.getParameter("mensagem") != null) {
			out.print(request.getParameter("mensagem"));
			out.print("<script>setTimeout(() => {window.location.href='funcionario.jsp'}, 3000);</script>");
		} else {
			out.print("Mensagens do sistema.");
		}
		%>
	</div>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Nascimento</th>
				<th>Endereco</th>
				<th>Especialidade</th>
				<th>Competências</th>
				<th>Periodo</th>
				<th>ValorHora</th>
				<th>Ações</th>
			</tr>
		</thead>
		<tbody>
			<%
				//Corpo da tabela
			for (Funcionario f : FuncionarioServlet.getFuncionarios()) {
				out.print("<tr>");
				out.print(f.toHTML());
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
										window.location.href = "funcionario.jsp?mensagem=Excluído com sucesso";
										//window.location.reload();
										//alert(this.responseText); 
									}
								});
				let url = "funcionarios?id=" + id; //Monta a URL concatenando o ID
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
			e.parentNode.parentNode.cells[6].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[7].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[8].innerHTML = "<button onclick='send(this)'>Concluir</button>";
		}

		function send(e) {
			let id = e.parentNode.parentNode.cells[0].innerText;
			let nome = e.parentNode.parentNode.cells[1].innerText;
			let nascimento = e.parentNode.parentNode.cells[2].innerText;
			let endereco = e.parentNode.parentNode.cells[3].innerText;
			let especialidade = e.parentNode.parentNode.cells[4].innerText;
			let competencias = e.parentNode.parentNode.cells[5].innerText;
			let periodo = e.parentNode.parentNode.cells[6].innerText;
			let valorHora = e.parentNode.parentNode.cells[7].innerText;
			let url = "funcionarios?id=" + id + "&nome=" + nome
					+ "&nascimento=" + nascimento + "&endereco=" + endereco
					+ "&especialidade=" + especialidade + "&competencias="
					+ competencias + "&periodo=" + periodo + "&valorHora="
					+ valorHora;
			let xm = new XMLHttpRequest();
			xm
					.addEventListener(
							"readystatechange",
							function() {
								if (this.readyState === this.DONE) {
									window.location.href = "funcionario.jsp?mensagem=Alterado com sucesso";
									//window.location.reload();
								}
							});
			xm.open("PUT", url);
			xm.send();
		}
	</script>
</body>
</html>