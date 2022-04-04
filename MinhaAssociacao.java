package rianGalatasMacedoBrandao.bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MinhaAssociacao implements InterfaceAssociacao {
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio, long fim) throws AssociadoNaoExistente, AssociacaoNaoExistente {
		DAOAssociacao daoA = new DAOAssociacao();
		DAOFrequencia daoF = new DAOFrequencia();
		procurar(numAssociacao);
		double freq = 0, quant = 0;
		try {
			Associacao aux = daoA.recuperar(numAssociacao);
			aux.procurarAssociado(numAssociado);
			ArrayList<Frequencia> arrayF = daoF.recuperarTodos(aux);

			for (Frequencia frequencia : arrayF) {
				if(frequencia.getData() >= inicio && frequencia.getData() <= fim && frequencia.getAssociado() == numAssociado) {
					freq++;
				}
			}
			quant = aux.getReuniao().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freq/quant;
	}


	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao) throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada, FrequenciaIncompativel {
		DAOAssociacao daoA = new DAOAssociacao();
		DAOAssociado daoa = new DAOAssociado();
		DAOFrequencia daoF = new DAOFrequencia();
		procurar(numAssociacao);
		try {
			Associacao aux = daoA.recuperar(numAssociacao);
			aux.procurarAssociado(codigoAssociado);
			aux.procurarReuniao(dataReuniao);
			Associado auxA = daoa.recuperar(numAssociacao, codigoAssociado);

			if(dataReuniao >= auxA.getDataAssociacao()) {
				if(daoF.recuperar(dataReuniao, numAssociacao, codigoAssociado) == null) {
					daoF.inserir(dataReuniao, numAssociacao, codigoAssociado);
				} else {
					throw new FrequenciaJaRegistrada();
				}
			} else {
				throw new FrequenciaIncompativel();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Reuniao auxR = aux.procurarReuniao(dataReuniao);
//
//		if(dataReuniao >= auxA.getDataAssociacao()) {
//			if(auxR.pesquisarFrequencia(auxA) == null) {
//				auxR.getFrequencia().add(auxA);
//			} else {
//				throw new FrequenciaJaRegistrada();
//			}
//		} else {
//			throw new FrequenciaIncompativel();
//		}
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data, double valor) throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		DAOAssociacao daoA = new DAOAssociacao();
		DAOAssociado daoa = new DAOAssociado();
		DAOTaxa daoT = new DAOTaxa();
		DAOPagamento daoP = new DAOPagamento();
		try {
			Associacao auxa = daoA.recuperar(numAssociacao);
			auxa.procurarAssociado(numAssociado);
			auxa.procurarTaxa(numAssociacao, taxa, vigencia);
			procurar(numAssociacao);
			Taxa auxT = daoT.recuperar(numAssociacao, vigencia, taxa);
			valorValido(valor, auxT.getProxParcela(), auxT.getValorAno());
			Associado auxA = daoa.recuperar(numAssociacao, numAssociado);
			if(auxA instanceof AssociadoRemido && auxT.isAdministrativa2() && ((AssociadoRemido) auxA).getDataRemissao() < data) {
				throw new AssociadoJaRemido();
			} else {

				auxT.pagarParcela(valor);
				auxT.calcMinProxParcela(valor);
				Pagamento p = new Pagamento(data, valor, auxA.getNumero(), auxT.getVigencia(), taxa, daoA.recuperar(numAssociacao).getNum());
				daoP.inserir(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Associacao aux = procurar(numAssociacao);
//		Associado auxA =  aux.procurarAssociado(numAssociado);
//		Taxa auxT = aux.procurarTaxa(taxa, vigencia);
//		
//		valorValido(valor, auxT.getProxParcela(), auxT.getValorAno());
//		
//		if(auxA instanceof AssociadoRemido && auxT.isAdministrativa2() && ((AssociadoRemido) auxA).getDataRemissao() < data) {
//				throw new AssociadoJaRemido();
//
//		} else {
//			auxT.pagarParcela(valor);
//			auxT.calcMinProxParcela(valor);
//			auxA.setPagamento(valor, data, taxa);
//		}
//		
	}


	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia, long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {

		DAOPagamento daoP = new DAOPagamento();
		DAOTaxa daoT = new DAOTaxa();
		try {
			
			double pagamento = 0;
			ArrayList<Pagamento> arrayP = daoP.recuperarTodos(daoT.recuperar(numAssociacao, vigencia, nomeTaxa));
			for (Pagamento pagamento2 : arrayP) {
				if(pagamento2.getDataPagamento() >= inicio && pagamento2.getDataPagamento() <= fim && pagamento2.getA() == numAssociado) {
					pagamento += pagamento2.getValorPago();
				}
			}
			return pagamento;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
//		Associacao aux = procurar(numAssociacao);
//		Associado auxA = aux.procurarAssociado(numAssociado);
//		double pagamento = 0;
//		for (Pagamento pagamentos : auxA.getPagamentos()) {
//			if(pagamentos.getDataPagamento() >= inicio && pagamentos.getDataPagamento() <= fim && pagamentos.getNomeTaxa() == nomeTaxa) {
//				pagamento += pagamentos.getValorPago();
//			}	
//		}
//		return pagamento;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente {
		DAOAssociacao daoA = new DAOAssociacao();
		DAOTaxa daoT = new DAOTaxa();
		double total = 0;
		try {
			procurar(numAssociacao);
			ArrayList<Taxa> arrayT = daoT.recuperarTodos(daoA.recuperar(numAssociacao));
			for (Taxa taxa : arrayT) {
				if(taxa.getVigencia() == vigencia) {
					total += taxa.getValorAno();
				}
			}
			return total;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
//		Associacao aux = procurar(numAssociacao);
//		double total = 0;
//		for (Taxa taxa : aux.getTaxa()) {
//			if(taxa.getVigencia() == vigencia) {
//				total  += taxa.getValorAno();
//			}
//		}
//		return total;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		DAOAssociacao dao = new DAOAssociacao();
			try {
				if(dao.recuperar(a.getNum())== a) {
					throw new AssociacaoJaExistente();
				} else {
					dao.inserir(a);
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}
	}
	
	public void adicionar(int associacao, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {	
		DAOAssociado dao = new DAOAssociado();
		procurar(associacao);
		try {
			if(dao.recuperar(associacao, a.getNumero()) == a) {
				throw new AssociadoJaExistente();
			} else {
				if(a instanceof AssociadoRemido)
				dao.inserir(associacao, (AssociadoRemido)a);
				else
				dao.inserir(associacao, a);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
}
	
	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		DAOReuniao dao = new DAOReuniao();
		procurar(associacao);
		
		try {
			if(dao.recuperar(associacao, r.getData()) == r) {
				throw new ReuniaoJaExistente();
			} else {
				dao.inserir(associacao, r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		DAOTaxa dao = new DAOTaxa();
		procurar(associacao);
		
		try {
			if(dao.recuperar(associacao,t.getVigencia(), t.getNome()) == t) {
				throw new TaxaJaExistente();
			} else {
				dao.inserir(associacao,t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		public void procurar(int num) throws AssociacaoNaoExistente {
			DAOAssociacao aux = new DAOAssociacao();
			try {
				if(aux.recuperar(num) == null)
					throw new AssociacaoNaoExistente();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		public void valorValido(double valor, double valorTaxa, double valorAno) throws ValorInvalido {
			if(valor < valorTaxa || valor > valorAno) {
				throw new ValorInvalido();
			}
		}

		public void limparBanco() {
		    try {
	            DAOAssociado daoAssociado = new DAOAssociado();
				daoAssociado.removerTodos();
	            
	            DAOAssociacao daoAssociacao = new DAOAssociacao();
				daoAssociacao.removerTodos();
	  	      
	            DAOReuniao daoReuniao = new DAOReuniao();
				daoReuniao.removerTodos();
	       
	            DAOTaxa daoTaxa = new DAOTaxa();
				daoTaxa.removerTodos();
				
				DAOFrequencia daoFrequencia = new DAOFrequencia();
				daoFrequencia.removerTodos();
				
				DAOPagamento daoPagamento = new DAOPagamento();
				daoPagamento.removerTodos();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
}		
