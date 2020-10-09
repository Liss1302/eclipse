package controllers; // Nome do pacote referente ao código abaixo ex: controllers

import java.io.IOException; //
import java.util.ArrayList; // importação do ArrayList

import javax.servlet.ServletException; //
import javax.servlet.annotation.WebServlet; //
import javax.servlet.http.HttpServlet; //
import javax.servlet.http.HttpServletRequest; //
import javax.servlet.http.HttpServletResponse; //

import domains.Funcionario;
import domains.OrdemServico;
import domains.dao.OrdemServicoDAO;

@WebServlet("/os")
public class OrdemServicoServlet extends HttpServlet { //

	private static final long serialVersionUID = 1L;
	private OrdemServico os;
	private static OrdemServicoDAO od = new OrdemServicoDAO(); 
	private static ArrayList<OrdemServico> oss = od.open();

	public static ArrayList<OrdemServico> getOrdensServico() {
		for(OrdemServico o: oss) {
			Funcionario f = FuncionarioServlet.getFuncionario(""+o.getFuncionario().getId());
			oss.get(oss.indexOf(o)).setFuncionario(f);
		}
		return OrdemServicoServlet.oss;
	}

	public static int getAutoId() {
		if (OrdemServicoServlet.oss.isEmpty())
			return 1;
		else
			return OrdemServicoServlet.oss.get(OrdemServicoServlet.oss.size() - 1).getId() + 1;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String msg = "";
		if (req.getParameter("dataAgendamento") != "" && req.getParameter("dataExecucao") != ""
				&& req.getParameter("totalHoras") != "" && req.getParameter("endereco") != ""
				&& !req.getParameter("idFuncionario").equals("0")) {
			os = new OrdemServico();
			String dataAgendamento = req.getParameter("dataAgendamento");
			String dataExecucao = req.getParameter("dataExecucao");
			String totalHoras = req.getParameter("totalHoras");
			String endereco = req.getParameter("endereco");
			String idFuncionario = req.getParameter("idFuncionario");
			os.setId(getAutoId());
			os.setDataAgendamento(dataAgendamento);
			os.setDataExecucao(dataExecucao);
			os.setTotalHoras(Integer.parseInt(totalHoras));
			os.setEndereco(endereco);
			os.setFuncionario(new Funcionario(Integer.parseInt(idFuncionario)));
			oss.add(os);
			if (od.save(oss)) {
				msg = "Dados enviados para arquivo CSV";
			} else {
				msg = "Erro ao enviar dados para o arquivo";
			}
		} else {
			msg = "Favor preencher todos os campos corretamente.";
		}
		resp.sendRedirect("os.jsp?mensagem=" + msg);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		os = new OrdemServico(Integer.parseInt(req.getParameter("id")));
		oss.remove(oss.indexOf(os));
		if (!od.save(oss)) {
			resp.sendRedirect("os.jsp?mensagem=Erro ao excluir");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		os = new OrdemServico();
		String id = req.getParameter("id");
		String dataAgendamento = req.getParameter("dataAgendamento");
		String dataExecucao = req.getParameter("dataExecucao");
		String totalHoras = req.getParameter("totalHoras");
		String endereco = req.getParameter("endereco");
		String idFuncionario = req.getParameter("idFuncionario");
		os.setId(Integer.parseInt(id));
		os.setDataAgendamento(dataAgendamento);
		os.setDataExecucao(dataExecucao);
		os.setTotalHoras(Integer.parseInt(totalHoras));
		os.setEndereco(endereco);
		os.setFuncionario(new Funcionario(Integer.parseInt(idFuncionario)));
		oss.set(oss.indexOf(os), os);
		if (!od.save(oss)) {
			resp.sendRedirect("os.jsp?mensagem=Erro ao atualizar");
		}
	}
}
