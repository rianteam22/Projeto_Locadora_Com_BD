package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOPagamento {
	public void inserir(Pagamento p) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "insert into pagamento (data, valor, associado, vigencia, taxa, associacao)" + " values (" + p.getDataPagamento() + "," + p.getValorPago() + "," + p.getA() + "," + p.getV() + ",\'" + p.getNomeTaxa() +"\'," + p.getAssoc() + ")";
		System.out.println(cmd);
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	
	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from pagamento";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}
	
//	public Pagamento recuperar(int n) throws SQLException {
//		Connection con = Conexao.getConexao();
//		String cmd = "select * from pagamento where taxa = " + n;
//		Pagamento p = null;
//		Statement st = con.createStatement();
//	    ResultSet rs = st.executeQuery(cmd);
//	    DAOAssociacao daoA = new DAOAssociacao();
//	    DAOAssociado daoa = new DAOAssociado();
//	    DAOTaxa daoT = new DAOTaxa();
//	    if (rs.next()) {
//	    	long data = rs.getLong("data");
//	    	double valor = rs.getDouble("valor");
//	    	int codAssoc = rs.getInt("associado");
//	    	int vigencia = rs.getInt("vigencia");
//	    	String nomeTaxa = rs.getString("taxa");
//	    	int numAssoc = rs.getInt("associacao");
//	    	Associacao auxA = daoA.recuperar(numAssoc);
//	    	Associado auxa = daoa.recuperar(numAssoc, codAssoc);
//	    	Taxa auxT = daoT.recuperar(numAssoc, vigencia, nomeTaxa);
//	    	p = new Pagamento(data, valor, auxa, auxT, nomeTaxa, auxA);
//	    }
//	    st.close();
//	    return p;
//	}
	
	public ArrayList<Pagamento> recuperarTodos(Taxa t) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from pagamento where taxa = "  + "\'" + t.getNome() + "\'" + "and vigencia = " + t.getVigencia();
		System.out.println(cmd);
		ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
		Pagamento p = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    if (rs.next()) {
	    	long data = rs.getLong("data");
	    	double valor = rs.getDouble("valor");
	    	int codAssoc = rs.getInt("associado");
	    	int vigencia = rs.getInt("vigencia");
	    	String nomeTaxa = rs.getString("taxa");
	    	int numAssoc = rs.getInt("associacao");
	    	p = new Pagamento(data, valor, codAssoc, vigencia, nomeTaxa, numAssoc);
	    	pagamentos.add(p);
	    }
	    st.close();
	    return pagamentos;
	}
}
