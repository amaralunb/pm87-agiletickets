package br.com.caelum.agiletickets.acceptance.estacionamento;

import org.openqa.selenium.WebDriver;

import br.com.caelum.agiletickets.acceptance.SharedDriver;
import br.com.caelum.agiletickets.acceptance.page.EstabelecimentosPage;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class EstacionamentoSteps {
	
	private EstabelecimentosPage estabelecimentos;
	
	public EstacionamentoSteps(EstabelecimentosPage estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}
	
	@Quando("o usuário marca que (há|não há) estacionamento")
	public void marcaEstacionamento(String temEstacionamento){
		estabelecimentos.adicioneEstabelecimentoComEstacionamento(temOuNao(temEstacionamento));
	}
	

	@Então("a última linha da lista mostra que (há|não há) estacionamento")
	public void ultimaLinhaDeveTerEstacionamento(String temEstacionamento) {
		estabelecimentos.ultimaLinhaDeveTerEstacionamento(temOuNao(temEstacionamento));
	}


	public boolean temOuNao(String temEstacionamento) {
		return "há".equals(temEstacionamento)?true:false;
	}
	
}
