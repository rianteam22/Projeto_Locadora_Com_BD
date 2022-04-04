package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOReuniao {
	public void inserir(int associacao, Reuniao r) throws SQLException, ValorInvalido {
		if(r.getAta() == "" || r.getData() < 0 || r.getAta() == null ) {
			throw new ValorInvalido();
		} else {
		Connection con = Conexao.getConexao();
		String cmd = "insert into reuniao (pauta, data, associacao) values (" + 
		"\'"+ r.getAta() + "\',"+ r.getData() + "," + associacao +")";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
		}
	}
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from reuniao";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	public Reuniao recuperar(int associacao, long dataR) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from reuniao where data = " + dataR + " and associacao = " + associacao;
		Reuniao r = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    DAOAssociacao daoA = new DAOAssociacao();
	    if (rs.next()) {
	    	long data = rs.getLong("data"); 
	    	String pauta = rs.getString("pauta");
	    	Associacao auxA = daoA.recuperar(associacao);
	    	r = new Reuniao(data, pauta, auxA);
	    }
	    st.close();
	    return r;
	}
	
	public ArrayList<Reuniao> recuperarTodos(Associacao a) throws SQLException{
		Connection con = Conexao.getConexao();
		String cmd = "select * from reuniao where associacao = " + a.getNum();
		ArrayList<Reuniao> r = new ArrayList<Reuniao>();
		Reuniao reuniao = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    while (rs.next()) {
	    	long data = rs.getLong("data"); 
	    	String pauta = rs.getString("pauta");
	    	reuniao = new Reuniao(data, pauta, a);
	    	r.add(reuniao);
	    }
	    st.close();
	    return r;
	}
}
