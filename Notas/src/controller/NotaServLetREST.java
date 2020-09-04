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
//API - Canal de Comunicação entre Aplicações
//Utiliza as boas práticas REST (Representational State Transfer)
/* REST utiliza os meios de transmissão de dados naturias do protocolo HTTP
	POST: criar dados no servidor;
	GET: leitura de dados no host;
	DELETE: excluir as informações;
	PUT: atualizações de registros.*?
*/

@WebServlet("/rest")
public class NotaServLetREST extends HttpServlet {

	private static final long serialVersionUID = 2L;
	private PrintWriter out;
	private NotaDAO nd = new NotaDAO();
	private Nota nota;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		if (req.getParameter("aluno") != null) {
			if (req.getParameter("aluno").isEmpty()) {
				// Se existir o parâmetro aluno e ele estiver vazio mostra todos
				for (Nota n : nd.open()) {
					out.print(n.getAluno() + "," + n.getNota()[0] + "," + n.getNota()[1] + "," + n.getNota()[2] + "\n");
				}
			} else {
				// Se não estiver vazio
				boolean encontrado = false;
				for (Nota n : nd.open()) {
					if (req.getParameter("aluno").equals(n.getAluno())) {
						out.print(n.getAluno() + "," + n.getNota()[0] + "," + n.getNota()[1] + "," + n.getNota()[2]
								+ "\n");
						encontrado = true;
					}
				}
				if(!encontrado) resp.setStatus(HttpServletResponse.SC_FOUND);
			}
		} else {
			// Senão mostra todos
			for (Nota n : nd.open()) {
				out.print(n.getAluno() + "," + n.getNota()[0] + "," + n.getNota()[1] + "," + n.getNota()[2] + "\n");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			ArrayList<Nota> notas = nd.open();
			notas.add(nota);
			if (nd.save(notas)) {
				out = resp.getWriter();
				resp.setStatus(HttpServletResponse.SC_CREATED);
				String resposta = "Dados salvos no Banco de dados CSV.";
				out.print(resposta);
			}
		}
		// String url = "/teste.jsp?retorno=" + dados;
		// resp.setStatus(HttpServletResponse.SC_CREATED);
		// resp.setContentType("text/html");
		// resp.setHeader("Location", url);
		// resp.getWriter().print(url);
		// resp.sendRedirect(url);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		out.print("Estou esperando requisição PUT - Alterar - UPDATE");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		if(req.getParameter("aluno")!=null) {
			ArrayList<Nota> notas = nd.open();
			nota = new Nota();
			nota.setAluno(req.getParameter("aluno"));
			if(notas.contains(nota)) {
				notas.remove(notas.indexOf(nota));
				if(nd.save(notas)) {
					resp.setStatus(HttpServletResponse.SC_OK);
					out.print("Aluno excluido com sucesso");					
				}
			}else {
				resp.setStatus(HttpServletResponse.SC_FOUND);
				out.print("Aluno não encontrado");									
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			out.print("Favor enviar o nome do aluno");
		}
	}
}
