package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.Mensagem;
import model.Carteira;

public class CarteiraDAO {

	private ArrayList<Carteira> carteiras;
	private Connection con;
	private PreparedStatement ps;
	private Carteira carteira;
	
	public ArrayList<Carteira> listarTodas(){
		carteiras = new ArrayList<>(); //Cria uma lisa vazia
		String query = "Select * from carteira";
		con = ConnectionDB.getConnection(); // Obtem conexão
		try {
			ps = con.prepareStatement(query); //Prepara a Query
			ResultSet rs = ps.executeQuery(); //Executa a Query
			while(rs.next()) {
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
			Mensagem.addMensagem("Erro, conexão com o BD: "+e);
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
		} catch (SQLException e) {
			Mensagem.addMensagem("Erro, conexão com o BD: "+e);
		}
		return sucesso;
	}

}
