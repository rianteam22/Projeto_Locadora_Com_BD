package rianGalatasMacedoBrandao.bd;

public class Taxa {
	//----------------------------------------------------------
	
	private String nome;
	private int vigencia;
	private double valorAno;
	private int parcelas;
	private boolean administrativa2;
	private int associacao, administrativa;
	private Associacao associacao2;
	
	//----------------------------------------------------------
	
	private double restante;
	private double proxParcela;
	
	//----------------------------------------------------------

	public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		this.nome = nome;
		this.vigencia = vigencia;
		this.valorAno = valorAno;
		this.parcelas = parcelas;
		this.setAdministrativa2(administrativa);
		this.proxParcela = valorAno/parcelas;
		this.restante = valorAno;
	}
	
	public Taxa(int a, String nome, int vigencia, double valorAno, int parcelas, int administrativa) {
		this.associacao = a;
		this.nome = nome;
		this.vigencia = vigencia;
		this.valorAno = valorAno;
		this.parcelas = parcelas;
		this.administrativa = administrativa;
		this.proxParcela = valorAno/parcelas;
		this.restante = valorAno;
	}
	
	public Taxa(Associacao associacao, String nome, int vigencia, double valorAno, int parcelas, int administrativa) {
		this.associacao2 = associacao;
		this.nome = nome;
		this.vigencia = vigencia;
		this.valorAno = valorAno;
		this.parcelas = parcelas;
		this.administrativa = administrativa;
		this.proxParcela = valorAno/parcelas;
		this.restante = valorAno;
		if(administrativa == 1)
			setAdministrativa2(true);
		else
			setAdministrativa2(false);
	}
	
	//----------------------------------------------------------

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public double getValorAno() {
		return valorAno;
	}

	public void setValorAno(double valorAno) {
		this.valorAno = valorAno;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public int getAdministrativa() {
		return administrativa;
	}

	public void setAdministrativa(int administrativa) {
		this.administrativa = administrativa;
	}
	
	//----------------------------------------------------------

	public double getRestante() {
		return restante;
	}
	
	public double getProxParcela() {
		return proxParcela;
	}
	
	public void pagarParcela(double valor) {
		if(restante != 0)
			this.restante = restante-valor;
		else
			System.out.println("TaxaJaPaga");
	}

	public void calcMinProxParcela(double valor) {
		proxParcela -= (valor - proxParcela);

	}

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}

	public boolean isAdministrativa2() {
		return administrativa2;
	}

	public void setAdministrativa2(boolean administrativa2) {
		this.administrativa2 = administrativa2;
	}
	
}
