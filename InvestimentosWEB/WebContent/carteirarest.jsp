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
					out.print("<input type='button' onclick='excluir(this)' value='Excluir'/>");
					out.print("<input type='button' onclick='alterar(this)' value='Alterar'/>");
					out.print("</center></td>");
					out.print("</tr>");
				}
				%>
				<tr>
					<form method="POST" action="carteirarest">
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
						<td>
							<center>
								<input type="submit" value="Cadastrar" />
							</center>
						</td>
					</form>
				</tr>
			</tbody>
		</table>
	</div>
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
	<script>
		function excluir(elemento) {
			if (window.confirm("Confirma Exclusão?")) {
				let xhr = new XMLHttpRequest();
				let id = elemento.parentNode.parentNode.parentNode.cells[0].innerText;
				xhr.addEventListener("readystatechange", function() {
					if (this.readyState === this.DONE) {
						window.location.reload();
					}
				});
				let url = "carteirarest?id=" + id;
				xhr.open("DELETE", url);
				xhr.send();
			}
		}
		function alterar(elemento) {
			elemento.parentNode.parentNode.parentNode.cells[1].setAttribute(
					"contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[2].setAttribute(
					"contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[3].setAttribute(
					"contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[4].setAttribute(
					"contenteditable", "true");
			elemento.parentNode.parentNode.parentNode.cells[5].innerHTML = "<center>"
					+ "<input type='button' value=' Concluir ' onclick='concluirEdicao(this)'/>"
					+ "</center>";
		}
		function concluirEdicao(elemento) {
			let id = "?id="
					+ elemento.parentNode.parentNode.parentNode.cells[0].innerText;
			let nome = "&nome="
					+ elemento.parentNode.parentNode.parentNode.cells[1].innerText;
			let lucro = "&lucro_esperado="
					+ elemento.parentNode.parentNode.parentNode.cells[2].innerText;
			let prejuiso = "&prejuiso_maximo="
					+ elemento.parentNode.parentNode.parentNode.cells[3].innerText;
			let perfil = "&perfil_investimento="
					+ elemento.parentNode.parentNode.parentNode.cells[4].innerText;
			let xhr = new XMLHttpRequest();
			xhr.addEventListener("readystatechange", function() {
				if (this.readyState === this.DONE) {
					window.location.reload();
				}
			});
			let url = "carteirarest" + id + nome + lucro + prejuiso + perfil;
			xhr.open("PUT", url);
			xhr.send();
		}
	</script>
</body>
</html>