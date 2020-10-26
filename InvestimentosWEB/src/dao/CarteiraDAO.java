package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.Mensagem;
import models.Carteira;

/*
 * DAO (Data Access Object) ou Objeto de acesso a dados
 * Antes de manipular dados em um SGBD Banco de Dados nós utilizávamos arquivos CSV
 * A classe DAO servia somente para abrir o arquivo (open) e salvar as modificações (save)
 * Para utilizar um SGBD precisamos fazer um CRUD completo.
 * INSERT, UPDATE e DELETE e para listar (read) utilizamos Queries SELECT
 * */

public class CarteiraDAO {

	private ArrayList<Carteira> carteiras;
	private Connection con;
	private PreparedStatement ps;
	private Carteira carteira;

	public ArrayList<Carteira> listarTodas() {
		carteiras = new ArrayList<>(); // Cria uma lisa vazia
		String query = "Select * from carteira";
		con = ConnectionDB.getConnection(); // Obtem conexão
		try {
			ps = con.prepareStatement(query); // Prepara a Query
			ResultSet rs = ps.executeQuery(); // Executa a Query
			while (rs.next()) {
				carteira = new Carteira();
				carteira.setId(rs.getInt("idCliente"));
				carteira.setNome(rs.getString("nome"));
				carteira.setLucroEsperado(rs.getDouble("lucroEsperado"));
				carteira.setPrejuisoMaximo(rs.getDouble("prejuizoMaximo"));
				carteira.setPerfilDeInvestimento(rs.getString("perfilDeInvestimento"));
				carteiras.add(carteira);
			}
			con.close();
		} catch (SQLException e) {
			Mensagem.addMensagem("Erro ao tentar listar todas: " + e);
		}
		return carteiras;
	}

	public boolean cadastrar(Carteira c) {
		boolean sucesso = false;
		String sql = "insert into carteira values (default,?,?,?,?)";
		con = ConnectionDB.getConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getNome());
			ps.setDouble(2, c.getLucroEsperado());
			ps.setDouble(3, c.getPrejuisoMaximo());
			ps.setString(4, c.getPerfilDeInvestimento());
			if (ps.executeUpdate() > 0) {
				sucesso = true;
			}
			con.close();
		} catch (SQLException e) {
			Mensagem.addMensagem("Erro ao tentar cadastrar: " + e);
		}
		return sucesso;
	}

	public boolean excluir(int id) {
		boolean sucesso = false;
		String sql = "delete from carteira where idCliente = ?";
		con = ConnectionDB.getConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			if (ps.executeUpdate() > 0) {
				sucesso = true;
			}
			con.close();
		} catch (SQLException e) {
			Mensagem.addMensagem("Erro, ao tentar excluir: " + e);
		}
		return sucesso;
	}

	public boolean alterar(Carteira c) {
		boolean sucesso = false;
		String sql = "update carteira set nome = ?, lucroEsperado = ?, prejuizoMaximo = ?, perfilDeInvestimento =? where idCliente = ?";
		con = ConnectionDB.getConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getNome());
			ps.setDouble(2, c.getLucroEsperado());
			ps.setDouble(3, c.getPrejuisoMaximo());
			ps.setString(4, c.getPerfilDeInvestimento());
			ps.setInt(5, c.getId());
			if (ps.executeUpdate() > 0) {
				sucesso = true;
			}
			con.close();
		} catch (SQLException e) {
			Mensagem.addMensagem("Erro, ao tentar alterar: " + e);
		}
		return sucesso;
	}
}
