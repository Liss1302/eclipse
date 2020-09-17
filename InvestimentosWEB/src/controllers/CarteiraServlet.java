package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarteiraDAO;
import model.Carteira;

@WebServlet("/carteira")
public class CarteiraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Carteira carteira;
	private CarteiraDAO cd = new CarteiraDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Recebe os dados do formulário, verifica se não estão em branco
		if (!req.getParameter("nome").isEmpty() && !req.getParameter("lucro_esperado").isEmpty()
				&& !req.getParameter("prejuiso_maximo").isEmpty()
				&& !req.getParameter("perfil_investimento").isEmpty()) {
			//Preenche o Modelo
			carteira = new Carteira();
			carteira.setNome(req.getParameter("nome"));
			carteira.setLucroEsperado(Double.parseDouble(req.getParameter("lucro_esperado")));
			carteira.setPrejuisoMaximo(Double.parseDouble(req.getParameter("prejuiso_maximo")));
			carteira.setPerfilDeInvestimento(req.getParameter("perfil_investimento"));
			//Envia para o Banco de Dados através da Classe DAO
			if(cd.cadastrar(carteira)) {
				resp.sendRedirect("carteira.jsp");
			} else {
				resp.sendRedirect("carteira.jsp?erro=EnviarParaBD");
			}
		} else {
			resp.sendRedirect("carteira.jsp?erro=HaCamposEmBranco");
		}
	}

}
