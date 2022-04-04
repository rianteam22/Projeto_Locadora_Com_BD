package rianGalatasMacedoBrandao.bd;

public class Frequencia {
	private long data;
	private int associacao;
	private int associado;
	private Reuniao r;
	private Associacao a;
	private Associado a1;
	
	//-------------------------------------------------------------------
	
	public Frequencia(long data, int associacao, int associado) {
		this.data = data;
		this.associacao = associacao;
		this.associado = associado;
	}
	
	public Frequencia(Reuniao r, Associacao a, Associado a1) {
		this.r = r;
		this.a = a;
		this.a1 = a1;
		this.data = r.getData();
		this.associacao = a.getNum();
		this.associado = a1.getNumero();
	}

	//-------------------------------------------------------------------
	
	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}

	public int getAssociado() {
		return associado;
	}

	public void setAssociado(int associado) {
		this.associado = associado;
	}

	public Reuniao getR() {
		return r;
	}

	public void setR(Reuniao r) {
		this.r = r;
	}

	public Associacao getA() {
		return a;
	}

	public void setA(Associacao a) {
		this.a = a;
	}

	public Associado getA1() {
		return a1;
	}

	public void setA1(Associado a1) {
		this.a1 = a1;
	}
}
