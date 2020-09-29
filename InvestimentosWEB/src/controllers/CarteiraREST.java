package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarteiraDAO;
import model.Carteira;

@WebServlet("/carteirarest")
public class CarteiraREST extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Carteira carteira;
	private CarteiraDAO cd = new CarteiraDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "carteirarest";
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		resp.setHeader("Location", url);
		resp.getWriter().print("Sevlet pronto para atender requisições REST");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//Valida se os campos não estão em branco
		if (!req.getParameter("nome").isEmpty() && !req.getParameter("lucro_esperado").isEmpty()
				&& !req.getParameter("prejuiso_maximo").isEmpty()
				&& !req.getParameter("perfil_investimento").equals("Perfil de Investimento")) {
			// Preenche o Modelo
			carteira = new Carteira();
			carteira.setNome(req.getParameter("nome"));
			carteira.setLucroEsperado(Double.parseDouble(req.getParameter("lucro_esperado")));
			carteira.setPrejuisoMaximo(Double.parseDouble(req.getParameter("prejuiso_maximo")));
			carteira.setPerfilDeInvestimento(req.getParameter("perfil_investimento"));
			// Envia para o Banco de Dados através da Classe DAO
			if (cd.cadastrar(carteira)) {
				Mensagem.addMensagem("Carteira cadastrada com sucesso.");
			}
		} else {
			Mensagem.addMensagem("Favor preencher todos os campos.");
		}
		resp.sendRedirect("carteirarest.jsp");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Valida se os campos não estão em branco
		if (!req.getParameter("id").isEmpty() && !req.getParameter("nome").isEmpty()
				&& !req.getParameter("lucro_esperado").isEmpty() && !req.getParameter("prejuiso_maximo").isEmpty()
				&& !req.getParameter("perfil_investimento").equals("Perfil de Investimento")) {
			// Preenche o Modelo
			carteira = new Carteira();
			carteira.setId(Integer.parseInt(req.getParameter("id")));
			carteira.setNome(req.getParameter("nome"));
			carteira.setLucroEsperado(Double.parseDouble(req.getParameter("lucro_esperado")));
			carteira.setPrejuisoMaximo(Double.parseDouble(req.getParameter("prejuiso_maximo")));
			carteira.setPerfilDeInvestimento(req.getParameter("perfil_investimento"));
			// Envia para o Banco de Dados através da Classe DAO
			if (cd.alterar(carteira)) {
				Mensagem.addMensagem("Carteira "+carteira.getId()+" atualizada com sucesso.");
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("id") != null) {
			int id = Integer.parseInt(req.getParameter("id"));
			if (cd.excluir(id)) {
				Mensagem.addMensagem("A carteira "+id+" foi excluída com sucesso.");
			}
		} else {
			Mensagem.addMensagem("Favor enviar o id do Cliente.");
		}
	}
}
