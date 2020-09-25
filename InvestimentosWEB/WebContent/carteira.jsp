<%@page import="controllers.Mensagem"%>
<%@page import="controllers.CarteirasController"%>
<%@page import="model.Carteira"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>Lista de Carteiras</title>
</head>
<body>
	<div class="mensagem">
		<p>
			Mensagens do sistema:
			<%
			if (!Mensagem.getMensagens().isEmpty()) {
			while (!Mensagem.getMensagens().isEmpty()) {
				out.print(Mensagem.getMensagem());
				out.print("<br/>");
			}
		}
		%>
		</p>
	</div>
	<div class="tableResult">
		<table>
			<thead>
				<th>id Cliente</th>
				<th>Nome</th>
				<th>lucro Esperado</th>
				<th>Prejuizo Maximo</th>
				<th>Perfil de Investimento</th>
				<th>Ações</th>
			</thead>
			<tbody>
				<%
					for (Carteira c : CarteirasController.getCarteiras()) {
					out.print("<tr>");
					out.print(c.toHTML());
					out.print("<td style='display: inline-block; width: 200px; margin: 0 auto;'><center>");
					out.print("<form method='post' action='carteira'>");
					out.print("<input type='hidden' name='acao' value='delete' />");
					out.print("<input type='hidden' name='id' value='"+c.getId()+"' />");
					out.print("<input type='submit' value='Excluir'/>");
					out.print("</form>");
					out.print("<input type='submit' value='Alterar'/>");
					out.print("</center></td>");
					out.print("</tr>");
				}
				%>
				<tr>
					<form method="POST" action="carteira">
						<input type="hidden" name="acao" value="insert" />
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
						<td><center><input type="submit" value="Cadastrar" /></center></td>
					</form>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>