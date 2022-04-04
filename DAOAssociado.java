package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAssociado {
	
	public void inserir(int associacao, Associado a) throws SQLException, ValorInvalido {
		if(a.getNumero() < 0 ||  a.getNome() == "" || a.getNome() == null || a.getTelefone() == null || a.getTelefone() == "" || a.getDataAssociacao() < 0 || a.getNascimento() < 0) {
			throw new ValorInvalido();
		} else {
		Connection con = Conexao.getConexao();
		String cmd = "insert into associado (numero, nome, telefone, data_associacao, nascimento, associacao, discriminador, data_remissao) values (" 
		     + a.getNumero() + ", \'" + a.getNome() + "\', \'" + a.getTelefone() + "\',"
		     + a.getDataAssociacao() + "," +  a.getNascimento() + "," + associacao + ",";
		
		if(a instanceof AssociadoRemido) {
			a.setDiscriminador(1);
			cmd = cmd + a.getDiscriminador() + "," + ((AssociadoRemido) a).getDataRemissao() + ")";
		} else {
			a.setDiscriminador(0);
			cmd = cmd + a.getDiscriminador() + ",0)";
		}
		
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	}
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associado";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	
	public Associado recuperar(int associacao, int n) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from associado where numero = " + n + " and associacao = " + associacao;
		Associado a = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    DAOAssociacao daoA = new DAOAssociacao();
	    if (rs.next()) {
	    	int num  = rs.getInt("numero");
	    	String nome = rs.getString("nome");
	    	String telefone = rs.getString("telefone");
	    	long dataAssociacao = rs.getLong("data_associacao");
	    	long nascimento = rs.getLong("nascimento");
	    	Associacao auxA = daoA.recuperar(associacao);
	    	int discriminador = rs.getInt("discriminador");
	    	long dataRemissao = rs.getLong("data_remissao");
	    	if(discriminador == 1) {
	    		a = new AssociadoRemido(num, nome, telefone, dataAssociacao, nascimento, auxA, discriminador, dataRemissao);
	    	} else {
	    		a = new Associado(num, nome, telefone, dataAssociacao, nascimento, auxA, discriminador);
	    	}
	    
	    }
	    st.close();
	    return a;
	}
	
	public ArrayList<Associado> recuperarTodos(Associacao assoc) throws SQLException{
		Connection con = Conexao.getConexao();
		String cmd = "select * from associado where associacao = " + assoc.getNum();
		ArrayList<Associado> a = new ArrayList<Associado>();
		Associado associado = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    while (rs.next()) {
	    	int num  = rs.getInt("numero");
	    	String nome = rs.getString("nome");
	    	String telefone = rs.getString("telefone");
	    	long nascimento = rs.getLong("nascimento");
	    	long dataAssociacao = rs.getLong("data_associacao");
	    	int discriminador = rs.getInt("discriminador");
	    	long dataRemissao = rs.getLong("data_remissao");
	    	associado = new Associado(num, nome, telefone, dataAssociacao, nascimento, assoc, discriminador, dataRemissao);
	    	a.add(associado); 
	    }
	    st.close();
	    return a;
	}
}
