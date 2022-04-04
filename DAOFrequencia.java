package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOFrequencia {
	
	public void inserir(long data, int associacao, int associado) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "insert into frequencia (data, associado, associacao) values (" + 
		data + "," + associado + "," + associacao +")";
		System.out.println(cmd);
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from frequencia";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	
	public Frequencia recuperar(long data, int associacao, int associado) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from frequencia where data = " +  data + " and associacao = " + associacao + " and associado = " + associado;
		Frequencia f = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    DAOAssociacao daoA = new DAOAssociacao();
	    DAOAssociado daoa = new DAOAssociado();
	    DAOReuniao daoR = new DAOReuniao();
	    if (rs.next()) {
	    	long dataR = rs.getLong("data");
	    	int codAssoc = rs.getInt("associado");
	    	int numAssoc = rs.getInt("associacao");
	    	Reuniao auxR = daoR.recuperar(numAssoc, dataR);
	    	Associacao auxA = daoA.recuperar(numAssoc);
	    	Associado auxa = daoa.recuperar(numAssoc, codAssoc);
	    	f = new Frequencia(auxR, auxA, auxa);
	    }
	    System.out.println(cmd);
	    st.close();
	    return f;
	}
	
	public ArrayList<Frequencia> recuperarTodos(Associacao a) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from frequencia where associacao = " + a.getNum();
		ArrayList<Frequencia> arrayF = new ArrayList<Frequencia>();
		Frequencia f = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    DAOAssociacao daoA = new DAOAssociacao();
	    DAOAssociado daoa = new DAOAssociado();
	    DAOReuniao daoR = new DAOReuniao();
	    while (rs.next()) {
	    	long dataR = rs.getLong("data");
	    	int codAssoc = rs.getInt("associado");
	    	int numAssoc = rs.getInt("associacao");
	    	Reuniao auxR = daoR.recuperar(numAssoc, dataR);
	    	Associacao auxA = daoA.recuperar(numAssoc);
	    	Associado auxa = daoa.recuperar(numAssoc, codAssoc);
	    	f = new Frequencia(auxR, auxA, auxa);
	    	arrayF.add(f);
	    }
	    System.out.println(cmd);
	    st.close();
	    return arrayF;
	}
}
