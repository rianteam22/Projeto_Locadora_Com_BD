package rianGalatasMacedoBrandao.bd;

import java.util.ArrayList;

public class Associado {
	//----------------------------------------------------------
	
	public ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
	
	//----------------------------------------------------------
	
	protected int numero;
	protected String nome;
	protected String telefone;
	protected long dataAssociacao;
	protected long nascimento;
	protected Associacao associacao;
	protected int discriminador, associacao2;
	
	//----------------------------------------------------------

	public Associado (int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.dataAssociacao = dataAssociacao;
		this.nascimento = nascimento;
	}
	
	public Associado (int numero, String nome, String telefone, long dataAssociacao, long nascimento, Associacao associacao, int discriminador) {
		this.numero = numero;
		this.nome = nome;
		this.telefone = telefone;
		this.dataAssociacao = dataAssociacao;
		this.nascimento = nascimento;
		this.associacao = associacao;
	}
	
//	public Associado (int numero, String nome, String telefone, long dataAssociacao, long nascimento, int associacao, int discriminador, long dataRemissao) {
//		this.numero = numero;
//		this.nome = nome;
//		this.telefone = telefone;
//		this.dataAssociacao = dataAssociacao;
//		this.nascimento = nascimento;
//		this.associacao2 = associacao;
//	}
	
	//----------------------------------------------------------

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getDataAssociacao() {
		return dataAssociacao;
	}

	public void setDataAssociacao(long dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}

	public long getNascimento() {
		return nascimento;
	}

	public void setNascimento(long nascimento) {
		this.nascimento = nascimento;
	}

	public Pagamento getPagamento(long dataPagamento) {
		for (Pagamento pagamento : pagamentos) {
			if(pagamento.getDataPagamento() == dataPagamento) {
				return pagamento;
			}
		}
		return null;
	}


	public Associacao getAssociacao() {
		return associacao;
	}

	public void setAssociacao(Associacao associacao) {
		this.associacao = associacao;
	}

	public int getDiscriminador() {
		return discriminador;
	}

	public void setDiscriminador(int discriminador) {
		this.discriminador = discriminador;
	}

	public int getAssociacao2() {
		return associacao2;
	}

	public void setAssociacao2(int associacao2) {
		this.associacao2 = associacao2;
	}

	//----------------------------------------------------------
}
