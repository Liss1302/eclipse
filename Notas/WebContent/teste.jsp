<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Testeando ServLet</title>
</head>
<body>
	<form action="rest">
		Testando GET <br/>
		<input type="text" name="aluno" placeholder="Digite um nome"/>
		<input type="submit" value="GET"/>
	</form>
	<form method="POST" action="rest">
		Testando POST <br/>
		<input type="text" name="aluno" placeholder="Digite um nome"/>
		<input type="text" name="n1" placeholder="Digite uma nota"/>
		<input type="text" name="n2" placeholder="Digite uma nota"/>
		<input type="text" name="n3" placeholder="Digite uma nota"/>
		<input type="submit" value="POST"/>
	</form>
	<di>
	<% if(request.getParameter("retorno")!=null){
		out.print(request.getParameter("retorno"));
	}
	%>
	</di>
</body>
</html>