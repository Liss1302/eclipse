package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CarteiraDAO;
import models.Carteira;

@WebServlet("/carteirarest")
public class CarteiraSERVLET extends HttpServlet {

	/*
	 * Este SERVLET ele passa a responder requisições REST FULL o SERVLET fica na
	 * camada Controller MVC pois possui o comportamento de intermediário, ou seja
	 * atender as requisições do FrontEnd que está na camada View.
	 */

	private static final long serialVersionUID = 1L;
	private Carteira carteira;
	private static CarteiraDAO cd = new CarteiraDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * O método doGet responde ao vebo GET listando toda a tabela de Carteiras ou
		 * apenas uma. Tem o comportamento de READ em um CRUD
		 */
		String url = "carteirarest"; // É o caminho para onde a resp será enviada
		JSONArray jarray = new JSONArray(); // Um vetor de JSONs
		JSONObject jobjt; // Um objeto JSON

		// Confiruração das respostas da API
		resp.setStatus(HttpServletResponse.SC_OK); // Retorna sucesso com o códifo 200
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json"); //
		resp.setHeader("Location", url);
		if (req.getParameter("id") == null) {
			// Laço que percorre a lista de Carteiras e preenche o Vetor
			for (Carteira c : cd.listarTodas()) {
				jobjt = new JSONObject();
				jobjt.put("id", c.getId());
				jobjt.put("nome", c.getNome());
				jobjt.put("lucroEsperado", c.getLucroEsperado());
				jobjt.put("prejuisoMaximo", c.getPrejuisoMaximo());
				jobjt.put("perfilDeInvestimento", c.getPerfilDeInvestimento());
				jarray.put(jobjt);
			}
			resp.getWriter().print(jarray.toString());
		} else {
			jobjt = new JSONObject();
			boolean encontrado = false;
			// Laço que percorre a lista de Carteiras e preenche o Vetor
			for (Carteira c : cd.listarTodas()) {
				if (req.getParameter("id").equals(String.format("%d", c.getId()))) {
					jobjt.put("id", c.getId());
					jobjt.put("nome", c.getNome());
					jobjt.put("lucroEsperado", c.getLucroEsperado());
					jobjt.put("prejuisoMaximo", c.getPrejuisoMaximo());
					jobjt.put("perfilDeInvestimento", c.getPerfilDeInvestimento());
					resp.getWriter().print(jobjt.toString());
					encontrado = true;
				}
			}
			if (!encontrado) {
				jobjt.put("erro", "Id não encontrado");
				resp.getWriter().print(jobjt.toString());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * O método doPost cria uma nova carteira com os dados recebido via POST, Tem o
		 * comportamento de CREATE em um CRUD
		 */
		req.setCharacterEncoding("UTF-8"); // Configura o CHARSET correto
		// Valida se os campos não estão em branco
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
		/*
		 * O método doPut serve para atualização de dados, Possui o comportamento de
		 * UPDATE em um CRUD
		 */
		// Valida se os campos não estão em branco
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
				Mensagem.addMensagem("Carteira " + carteira.getId() + " atualizada com sucesso.");
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * O método doDelete recebe os dados pelo verbo DELETE e faz a exclusão, possui
		 * o comportamento de DELETE em um CRUD
		 */
		if (req.getParameter("id") != null) {
			int id = Integer.parseInt(req.getParameter("id"));
			if (cd.excluir(id)) {
				Mensagem.addMensagem("A carteira " + id + " foi excluída com sucesso.");
			}
		} else {
			Mensagem.addMensagem("Favor enviar o id do Cliente.");
		}
	}
}
