package br.com.caelum.agiletickets.models;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void temVagaDisponivel() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}
	
	@Test
	public void consegueReservarTudo() {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);
		
		boolean podeReservar = sessao.podeReservar(2);
		
		Assert.assertTrue(podeReservar);
	}

	@Test
	public void naoUltrapassaLimiteDeVagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
}
