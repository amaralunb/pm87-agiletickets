package br.com.caelum.agiletickets.acceptance.page;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.caelum.agiletickets.acceptance.SharedDriver;

public class EstabelecimentosPage {

	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;
	private WebElement form;

	public EstabelecimentosPage(SharedDriver webDriver) {
		this.driver = webDriver;
	}

	public void abreListagem() {
		driver.get(BASE_URL + "/estabelecimentos");
	}

	public void adicioneEstabelecimento() {
		form = form();
	}

	public void comNome(String nome){
		form.findElement(By.name("estabelecimento.nome")).sendKeys(nome);
	}

	public void comEndereco(String endereco){
		form.findElement(By.name("estabelecimento.endereco")).sendKeys(endereco);
	}

	public void adicionar(){
		form.submit();
		form = null;
	}
	
	public void ultimaLinhaDeveConter(String nome, String endereco) {
		WebElement ultimaLinha = ultimaLinha();
		assertThat(ultimaLinha.findElements(By.tagName("td")).get(1).getText(), is(nome));
		assertThat(ultimaLinha.findElements(By.tagName("td")).get(2).getText(), is(endereco));
	}

	public void deveMostrarErro(String erro) {
		WebElement erros = driver.findElement(By.id("errors"));

		assertThat(erros.getText(), containsString(erro));
	}

	public void adicioneEstabelecimentoComEstacionamento(boolean temEstacionamento) {
		adicioneEstabelecimento();
		comNome("qualquer");
		comEndereco("qualquer");
		form.findElement(By.name("estabelecimento.temEstacionamento"))
			.sendKeys(temEstacionamento ? "Sim" : "Não");
	}

	public void ultimaLinhaDeveTerEstacionamento(boolean estacionamento) {
		WebElement temEstacionamento = ultimaLinha().findElements(By.tagName("td")).get(3);
		assertThat(temEstacionamento.getText(), is(estacionamento ? "Sim" : "Não"));
	}

	private WebElement form() {
		return driver.findElement(By.id("addForm"));
	}

	private WebElement ultimaLinha() {
		List<WebElement> linhas = driver.findElements(By.tagName("tr"));
		WebElement ultimaLinha = linhas.get(linhas.size() - 1);
		return ultimaLinha;
	}

}
