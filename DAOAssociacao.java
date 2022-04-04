package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAssociacao {

	public void inserir(Associacao a) throws SQLException, ValorInvalido {
		if(a.getNome() == "" || a.getNum() < 0 || a.getNome() == null) {
			throw new ValorInvalido();
		} else {
		Connection con = Conexao.getConexao();
		String cmd = "insert into associacao (numero, nome) values (" 
		     + a.getNum() + ", \'" + a.getNome() + "\')";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	}
	public void alterar(Associacao a) {
		
	}

	public void remover(int n) {
		
	}
	
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}

	public Associacao recuperar(int n) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from associacao where numero = " + n;
		Associacao a = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    if (rs.next()) {
	    	int num  = rs.getInt("numero");
	    	String nome = rs.getString("nome");
	    	a = new Associacao(num, nome);
	    }
	    st.close();
	    return a;
	}
	
	public ArrayList<Associacao> recuperarTodos() {
		return null;
	}	

}
