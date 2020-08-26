<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.SorteioDAO"%>
<%@page import="model.Sorteio"%>
<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Sorteio Grupos</title>
</head>
<body>

	<%
		//Cabeçalho
	Sorteio sorteio;
	SorteioDAO sd = new SorteioDAO();
	ArrayList<Sorteio> sorteios = new ArrayList<>();
	ArrayList<String> alunos = sd.openAlunos();
	ArrayList<String> temas = sd.openTemas();
	%>

	<table>
		<thead>
			<th>Alunos do aqruivo CSV</th>
			<th>Alunos Em ordem Reversa</th>
			<th>Temas do aqruivo CSV</th>
			<th>Temas Em ordem alfabética</th>
			<th>Alunos Embaralhados</th>
			<th>Temas Embaralhados</th>
		</thead>
		<tbody>
			<tr>
				<td>
					<%
						for (String a : alunos) {
						out.print(a);
						out.print("<br>");
					}
					%>
				</td>
				<td>
					<%
						Collections.reverse(alunos);
					for (String a : alunos) {
						out.print(a);
						out.print("<br>");
					}
					%>
				</td>
				<td>
					<%
						for (String t : temas) {
						out.print(t);
						out.print("<br>");
					}
					%>
				</td>
				<td>
					<%
						Collections.sort(temas);
					for (String t : temas) {
						out.print(t);
						out.print("<br>");
					}
					%>
				</td>
				<td>
					<%
						Collections.shuffle(alunos);
					for (String a : alunos) {
						out.print(a);
						out.print("<br>");
					}
					%>
				</td>
				<td>
					<%
						Collections.shuffle(temas);
					for (String t : temas) {
						out.print(t);
						out.print("<br>");
					}
					%>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<%
						int indiceTema = 0;
					for (int i = 0; i < alunos.size(); i++) {

						out.print(alunos.get(i) + " - " + temas.get(indiceTema));
						out.print("<br>");
						sorteio = new Sorteio();
						sorteio.setAluno(alunos.get(i));
						sorteio.setTema(temas.get(indiceTema));
						sorteios.add(sorteio);

						if (indiceTema < temas.size() - 1) {
							indiceTema++;
						} else {
							indiceTema = 0;
						}
					}
					%>
					<hr>
					<form>
						<input type="submit" value="Sortear">
					</form>
					<form>
						<input type="hidden" name="salvar" value="true"> <input
							type="submit" value="Salvar">
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<%
		if (request.getParameter("salvar") != null) {
		sd.saveGrupos(sorteios);
		out.print("grupos.csv salvo com sucesso.");
	}
	%>
</body>
</html>