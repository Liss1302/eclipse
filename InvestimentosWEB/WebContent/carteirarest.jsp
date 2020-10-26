<%@page import="controllers.Mensagem"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>Lista de Carteiras</title>
</head>
<body id="corpoPagina">
	<div class="tableResult">
		<table>
			<!-- (READ) Tabela para listar os dados lidos no Banco de Dados -->
			<thead>
				<tr>
					<th>id Cliente</th>
					<th>Nome</th>
					<th>lucro Esperado</th>
					<th>Prejuizo Maximo</th>
					<th>Perfil de Investimento</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody id="corpoTabela">
			</tbody>
		</table>
	</div>
	<div class="form1">
		<!-- (CREATE) Formulário para cadastro de novas Carteiras -->
		<form method="POST" action="carteirarest">
			<input type="text" name="nome" placeholder="Nome" /> <input
				type="number" name="lucro_esperado" placeholder="Lucro Esperado" />
			<input type="number" name="prejuiso_maximo"
				placeholder="Prejuizo Maximo" /><select name="perfil_investimento">
				<option>Perfil de Investimento</option>
				<option>Conservador</option>
				<option>Moderado</option>
				<option>Agressivo</option>
			</select> <input type="submit" value="Adicionar" />
		</form>
	</div>
	<div class="mensagem" id="msg">
		<!-- Espaço reservado para menságens do sistema que desaparecem após 3 segundos -->
		<%
			if (!Mensagem.getMensagens().isEmpty())
			while (!Mensagem.getMensagens().isEmpty())
				out.print("<p>" + Mensagem.getMensagem() + "</p>");
		else
			out.print("<p>Mensagens do sistema:</p>");
		%>
		<script>setTimeout(() => {document.getElementById("msg").style.display="none"}, 3000);</script>
	</div>
	<script src="carteirarest.js"></script>
</body>
</html>