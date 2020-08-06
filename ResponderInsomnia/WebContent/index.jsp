<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Insert title here</title>
</head>
<body>
	<%
	
		String nome = request.getParameter("nome");
		String idadeString = request.getParameter("idade");
		String sexo = request.getParameter("sexo");
	
		if (sexo != null) {
			if (sexo.toUpperCase().equals("M")) {
				sexo = "Homem";
			} else if (sexo.toUpperCase().equals("F")) {
				sexo = "Mulher";
			} else {
				sexo = "Trans";
			}	
		}
		
		int idade = 0;
		if(idadeString != null){
			idade = Integer.parseInt(idadeString); //Converte a String para int
			//Faz os testes
			if(idade > 50){
				idadeString = "idoso";
			}else if(idade > 25){
				idadeString = "adulto";
			}else if(idade > 18){
				idadeString = "jovem";
			}else if(idade > 10){
				idadeString = "adolescente";
			}else{
				idadeString = "criança";
			}
		}
	
		out.print("Oi " + nome + ", você tem " + idade + " anos, é " + sexo + " e é "+idadeString);
	%>
</body>
</html>