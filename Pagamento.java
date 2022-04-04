package rianGalatasMacedoBrandao.bd;

public class Pagamento {
	private double valorPago;
	private long dataPagamento;
	private String nomeTaxa;
	private int v, assoc, a;
	
//	public Pagamento(long data, double valor, Associado associado, Taxa taxa, String nomeTaxa, Associacao associacao) {
//		this.valorPago = valor;
//		this.dataPagamento = data;
//		this.nomeTaxa = nomeTaxa;
//		this.associado = associado;
//		this.associacao = associacao;
//		this.taxa = taxa;
//		this.v = taxa.getVigencia();
//		this.a = associado.getNumero();
//		this.assoc = associacao.getNum();	
//	}
	
	public Pagamento(long data, double valor, int associado, int taxa, String nomeTaxa, int associacao) {
		this.valorPago = valor;
		this.dataPagamento = data;
		this.nomeTaxa = nomeTaxa;
		this.v = taxa;
		this.a = associado;
		this.assoc = associacao;
	}
	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public long getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(long dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getNomeTaxa() {
		return nomeTaxa;
	}

	public void setNomeTaxa(String nomeTaxa) {
		this.nomeTaxa = nomeTaxa;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getAssoc() {
		return assoc;
	}

	public void setAssoc(int assoc) {
		this.assoc = assoc;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
}
