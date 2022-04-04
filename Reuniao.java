package rianGalatasMacedoBrandao.bd;

import java.util.ArrayList;

public class Reuniao {
	//----------------------------------------------------------
	
	private long data;
	private String ata;
	private int associacao;
	private Associacao associacao2;
	
	//----------------------------------------------------------

	public Reuniao(long data, String ata) {
		this.data = data;
		this.ata = ata;
	}
	
	public Reuniao(long data, String ata, int associacao) {
		this.data = data;
		this.ata = ata;
		this.associacao = associacao;
	}
	
	public Reuniao(long data, String ata, Associacao associacao) {
		this.data = data;
		this.ata = ata;
		this.associacao2 = associacao;
	}
	
	//----------------------------------------------------------


	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}
	
	//----------------------------------------------------------

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}

	public Associacao getAssociacao2() {
		return associacao2;
	}

	public void setAssociacao2(Associacao associacao2) {
		this.associacao2 = associacao2;
	}

}
