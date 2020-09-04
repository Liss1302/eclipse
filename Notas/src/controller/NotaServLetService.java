package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//O que Servlet = Mini Servidor WEB - Serve como API (Application Programming Interface)
//API - Canal de Comunicação entre Aplicações
//Utiliza as boas práticas REST (Representational State Transfer)
/* REST utiliza os meios de transmissão de dados naturias do protocolo HTTP
	POST: criar dados no servidor;
	GET: leitura de dados no host;
	DELETE: excluir as informações;
	PUT: atualizações de registros.*?
*/

@WebServlet("/service")
public class NotaServLetService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		out.print("Serviço Ativo, aceito qualquer VERBO");
	}

}
