package rianGalatasMacedoBrandao.bd;

import java.sql.SQLException;
import java.util.ArrayList;

public class Associacao {
	//----------------------------------------------------------
	
	private ArrayList<Associado> associado = null;
	private ArrayList<Taxa> taxa = null;
	private ArrayList<Reuniao> reuniao = null;
	
	//----------------------------------------------------------
	
	private int num;
	private String nome;
	
	//----------------------------------------------------------

	public Associacao(int num, String nome) {
		this.num = num;
		this.nome = nome;
	}
	
	//----------------------------------------------------------

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//----------------------------------------------------------
	
	public void procurarAssociado(int numero) throws AssociadoNaoExistente {
		DAOAssociado aux = new DAOAssociado();
		try {
			if(aux.recuperar(this.getNum(), numero) == null)
				throw new AssociadoNaoExistente();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//----------------------------------------------------------

	public Taxa pesquisarTaxa(Taxa t) {
		for (Taxa taxa2 : taxa) {
			if(taxa2.getNome() == t.getNome() && taxa2.getVigencia() == t.getVigencia()) {
				return taxa2;
			}
		}
		return null;
	}

	public void procurarTaxa(int associacao,String nomeTaxa, int vigencia) throws TaxaNaoExistente {
		DAOTaxa daoT = new DAOTaxa();
		try {
			if(daoT.recuperar(associacao, vigencia, nomeTaxa) == null)
			throw new TaxaNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------

	public Reuniao pesquisarReuniao(long data) {
		for (Reuniao reuniao2 : reuniao) {
			if(reuniao2.getData() == data) {
				return reuniao2;
			}
		}
		return null;
	}

	public void procurarReuniao(long data) throws ReuniaoNaoExistente {
		DAOReuniao aux = new DAOReuniao();
		try {
			if(aux.recuperar(this.getNum(), data) == null)
				throw new ReuniaoNaoExistente();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//----------------------------------------------------------

	public ArrayList<Associado> getAssociado() throws SQLException {
		if(associado == null) {
			DAOAssociado daoA = new DAOAssociado();
			associado = daoA.recuperarTodos(this);
		}
		return associado;
	}

	public ArrayList<Taxa> getTaxa() {
		return taxa;
	}

	public ArrayList<Reuniao> getReuniao() throws SQLException {
		if(reuniao == null) {
			DAOReuniao daoR = new DAOReuniao();
			reuniao = daoR.recuperarTodos(this);
		}
		return reuniao;
	}

}
