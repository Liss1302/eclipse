<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Testeando ServLet</title>
</head>
<body>
	<form method="GET" action="rest">
		Testando GET e DELETE <br/>
		<input type="text" name="aluno" id="aluno" placeholder="Digite um nome"/>
		<input type="submit" value="GET"/>
		<input type="button" value="DELETE" onclick="del()"/>
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
	<script>
	//JavaScript utilizando AJAX
	function del(){
		var data = null;
		var aluno = document.getElementById("aluno").value;
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = true;
		xhr.addEventListener("readystatechange", function () {
		  if (this.readyState === this.DONE) {
		    alert(this.responseText);
			//console.log(this.responseText);
		    //window.location.replace("rest");
		  }
		});
		xhr.open("DELETE", "http://localhost:8080/Notas/rest?aluno="+aluno);		
		xhr.send(data);
	}
	</script>
</body>
</html>