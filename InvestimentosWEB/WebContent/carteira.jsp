<%@page import="controllers.Mensagem"%>
<%@page import="controllers.CarteirasController"%>
<%@page import="model.Carteira"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Lista de Carteiras</title>
</head>
<body>
	<div>
		Mensagens do sistema:
		<%
			if (!Mensagem.getMensagens().isEmpty()) {
				while (!Mensagem.getMensagens().isEmpty()) {
					out.print(Mensagem.getMensagem());
					out.print("<br/>");
				}
			}
		%>
	</div>
	<table>
		<thead>
			<th>id Cliente</th>
			<th>Nome</th>
			<th>lucro Esperado</th>
			<th>Prejuizo Maximo</th>
			<th>Perfil de Investimento</th>
		</thead>
		<tbody>
			<%
				for (Carteira c : CarteirasController.getCarteiras()) {
				out.print("<tr>");
				out.print(c.toHTML());
				out.print("<td><button>Atualizar</button>");
				out.print("<button>Excluir</button></td>");
				out.print("</tr>");
			}
			%>
			<tr>
				<form method="POST" action="carteira">
					<td>Id</td>
					<td><input type="text" name="nome" placeholder="Nome" /></td>
					<td><input type="number" name="lucro_esperado"
						placeholder="Lucro Esperado" /></td>
					<td><input type="number" name="prejuiso_maximo"
						placeholder="Prejuizo Maximo" /></td>
					<td><select name="perfil_investimento">
							<option>Perfil de Investimento</option>
							<option>Conservador</option>
							<option>Moderado</option>
							<option>Agressivo</option>
					</select></td>
					<td><input type="submit" value="Cadastrar" /></td>
				</form>
			</tr>
		</tbody>
	</table>
</body>
</html>