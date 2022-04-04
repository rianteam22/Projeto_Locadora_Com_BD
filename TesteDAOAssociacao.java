package rianGalatasMacedoBrandao.bd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

public class TesteDAOAssociacao {

	@Test
	public void testarIncluirRecuperarAssociacao() throws SQLException {
		Associacao a = new Associacao(10, "Casa PASN");
		DAOAssociacao dao = new DAOAssociacao();
		dao.removerTodos();
		dao.inserir(a);
		Associacao outra = dao.recuperar(10);
		assertEquals(10, outra.getNum());
		assertEquals("Casa PASN", outra.getNome());
	}
}
