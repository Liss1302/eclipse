<%@page import="controllers.Mensagem"%>
<%@page import="controllers.CarteirasController"%>
<%@page import="model.Carteira"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
					out.print("<form method='post' action='carteira' id='form-del'>");
					out.print("<input type='hidden' name='acao' value='delete' />");
					out.print("<input type='hidden' name='id' value='" + c.getId() + "' />");
					out.print("<input type='button' onclick='confimaExclusao(this)' value='Excluir'/>");
					out.print("</form>");
					out.print("<input type='button' value='Alterar' onclick='alterar(this)'/>");
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
						<td><center>
								<input type="submit" value="Cadastrar" />
							</center></td>
					</form>
				</tr>
			</tbody>
		</table>
	</div>
	<form method="POST" action="carteira" id="form-update">
		<input type="hidden" name="acao" value="update">
		<input type="hidden" id="id" name="id">
		<input type="hidden" id="nome" name="nome">
		<input type="hidden" id="lucro_esperado" name="lucro_esperado">
		<input type="hidden" id="prejuiso_maximo" name="prejuiso_maximo">
		<input type="hidden" id="perfil_investimento" name="perfil_investimento">
	</form>
	<script>
		function confimaExclusao(elemento) {
			if (window.confirm("Confirma Exclusão?")) {
				elemento.parentNode.submit();
			}
		}
		function alterar(elemento) {
			elemento.parentNode.parentNode.parentNode.cells[1].setAttribute("contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[2].setAttribute("contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[3].setAttribute("contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[4].setAttribute("contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[5].innerHTML = 
			    "<center>"+
				"<input type='button' value=' Concluir ' onclick='concluirEdicao(this)'/>"+
				"</center>";
		}
		function concluirEdicao(elemento){
			document.getElementById("id").value = elemento.parentNode.parentNode.parentNode.cells[0].innerText;
			elemento.parentNode.parentNode.parentNode.cells[1].setAttribute("contenteditable", "false");
			document.getElementById("nome").value = elemento.parentNode.parentNode.parentNode.cells[1].innerText;
			elemento.parentNode.parentNode.parentNode.cells[2].setAttribute("contenteditable", "false");
			document.getElementById("lucro_esperado").value = elemento.parentNode.parentNode.parentNode.cells[2].innerText;
			elemento.parentNode.parentNode.parentNode.cells[3].setAttribute("contenteditable", "false");
			document.getElementById("prejuiso_maximo").value = elemento.parentNode.parentNode.parentNode.cells[3].innerText;
			elemento.parentNode.parentNode.parentNode.cells[4].setAttribute("contenteditable", "false");
			document.getElementById("perfil_investimento").value = elemento.parentNode.parentNode.parentNode.cells[4].innerText;
			elemento.parentNode.parentNode.parentNode.cells[5].innerHTML =
				"<center>"+
			"<form method='post' action='carteira' id='form-del'>"+
			"<input type='hidden' name='acao' value='delete' />"+
			"<input type='hidden' name='id' value='" + elemento.parentNode.parentNode.parentNode.cells[0] +"' />"+
			"<input type='button' onclick='confimaExclusao(this)' value='Excluir'/>"+
			"</form>"+
			"<input type='button' value='Alterar' onclick='alterar(this)'/>"+
			"</center>";
			document.getElementById("form-update").submit();
		}
	</script>
</body>
</html>