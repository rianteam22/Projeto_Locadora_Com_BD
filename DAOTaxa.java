package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOTaxa {
	public void inserir(int associacao, Taxa t) throws SQLException, ValorInvalido {
		if(t.getNome() == "" || t.getNome() == null || t.getVigencia() < 0 || t.getParcelas() <= 0 || t.getValorAno() <= 0) {
			throw new ValorInvalido();
		} else {
		Connection con = Conexao.getConexao();
		if(t.isAdministrativa2())
			t.setAdministrativa(1);
		else
			t.setAdministrativa(0);
		String cmd = "insert into taxa (nome,vigencia,valor,parcelas,administrativa,associacao) values (" + 
		"\'"+ t.getNome() + "\'," + t.getVigencia() + "," + t.getValorAno() + "," + t.getParcelas() 
		+ "," + t.getAdministrativa() + "," + associacao + ")";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
		}
	}
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from taxa";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	public Taxa recuperar(int associacao, int V, String nomeT) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from taxa where vigencia = " + V + " and associacao = " + associacao + 
				" and nome = " + "\'" + nomeT +"\'";
		Taxa t = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    DAOAssociacao daoA = new DAOAssociacao();
	    if (rs.next()) {
	    	String nome = rs.getString("nome");
	    	int vigencia = rs.getInt("vigencia");
	    	double valor = rs.getDouble("valor");
	    	int parcelas = rs.getInt("parcelas");
	    	int administrativa = rs.getInt("administrativa");
	    	Associacao auxA = daoA.recuperar(associacao);
	    	t = new Taxa(auxA,nome,vigencia,valor,parcelas,administrativa);
	    }
	    st.close();
	    return t;
	}
	
	public ArrayList<Taxa> recuperarTodos(Associacao a) throws SQLException{
		Connection con = Conexao.getConexao();
		String cmd = "select * from taxa where associacao = " + a.getNum();
		ArrayList<Taxa> t = new ArrayList<Taxa>();
		Taxa taxa = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    while (rs.next()) {
	    	String nome = rs.getString("nome");
	    	int vigencia = rs.getInt("vigencia");
	    	double valor = rs.getDouble("valor");
	    	int parcelas = rs.getInt("parcelas");
	    	int administrativa = rs.getInt("administrativa");
	    	taxa = new Taxa(a,nome,vigencia,valor,parcelas,administrativa);
	    	t.add(taxa);
	    }
	    st.close();
	    return t;
	}
}
