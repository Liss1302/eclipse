package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Nota;
import model.dao.NotaDAO;

//O que Servlet = Mini Servidor WEB - Serve como API (Application Programming Interface)
//API - Canal de Comunica��o entre Aplica��es
//Utiliza as boas pr�ticas REST (Representational State Transfer)
/* REST utiliza os meios de transmiss�o de dados naturias do protocolo HTTP
	POST: criar dados no servidor;
	GET: leitura de dados no host;
	DELETE: excluir as informa��es;
	PUT: atualiza��es de registros.*?
*/

@WebServlet("/rest")
public class NotaServLetREST extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private NotaDAO nd = new NotaDAO();
	private Nota nota;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dados = "Recebida rec. por GET";
		if (req.getParameter("aluno") != null) {
			if (req.getParameter("aluno").isEmpty()) {
				dados += "<br>O parametro aluno esta vazio.";
			} else {
				dados += "<br/>O nome do aluno foi recebido foi: " + req.getParameter("aluno");
			}
		}
		// Listar Todos
		for (Nota n : nd.open()) {
			dados += "<br/>" + n.toCSV();
		}
		resp.sendRedirect("teste.jsp?retorno=" + dados);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dados = "Recebida rec. por POST";
		if (!req.getParameter("aluno").isEmpty()) {
			nota = new Nota();
			float[] n = new float[3];
			if (req.getParameter("aluno") != null)
				nota.setAluno(req.getParameter("aluno"));
			if (req.getParameter("n1") != null)
				n[0] = Float.parseFloat(req.getParameter("n1"));
			if (req.getParameter("n2") != null)
				n[1] = Float.parseFloat(req.getParameter("n2"));
			if (req.getParameter("n3") != null)
				n[2] = Float.parseFloat(req.getParameter("n3"));
			nota.setNota(n);
			dados += "<br>" + nota.toString() + "<br>Media = " + nota.getMedia();
			ArrayList<Nota> notas = nd.open();
			notas.add(nota);
			if (nd.save(notas)) {
				dados += "<br> Dados salvos no Banco de dados CSV.";
			}
		}
		resp.sendRedirect("teste.jsp?retorno=" + dados);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		out.print("Estou esperando requisi��o PUT - Alterar - UPDATE");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		out.print("Estou esperando requisi��o DELETE");
	}

}
