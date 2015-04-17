package br.com.caelum.agiletickets.acceptance.estacionamento;

import br.com.caelum.agiletickets.acceptance.page.EstabelecimentosPage;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class EstabelecimentoSteps {

	private EstabelecimentosPage estabelecimentos;
	
	public EstabelecimentoSteps(EstabelecimentosPage estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}

	@Dado("que o usuário abre a lista de estabelecimentos")
	public void abreListagem(){
		estabelecimentos.abreListagem();
	}
	
	@Quando("clica em \"Adicionar\"")
	public void clicaAdicionar(){
		estabelecimentos.adicionar();
	}

	@Quando("o usuário informa o nome \"(.*?)\"")
	public void informaNome(String nome) {
		estabelecimentos.adicioneEstabelecimento();
		estabelecimentos.comNome(nome);
	}

	@Quando("o usuário não informa o nome")
	public void naoInformaNome() {
		estabelecimentos.adicioneEstabelecimento();
	}

	@Quando("informa o endereço \"(.*?)\"")
	public void informaEndereco(String endereco) {
		estabelecimentos.comEndereco(endereco);
	}

	@Quando("não informa o endereço")
	public void naoInformaEndereco() {
	}

	@Então("deve ser mostrada a mensagem \"(.*?)\"")
	public void deveMostraMensagem(String mensagem) {
	    estabelecimentos.deveMostrarErro(mensagem);
	}

	@Então("a última linha da lista mostra \"(.*?)\" e \"(.*?)\"")
	public void ultimaLinhaMostra(String nome, String endereco) {
		estabelecimentos.ultimaLinhaDeveConter(nome, endereco);
	}

}
