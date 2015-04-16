package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.BALLET;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.CINEMA;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.ORQUESTRA;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.SHOW;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		TipoDeEspetaculo categoriaDoEspetaculo = sessao.getEspetaculo().getTipo();
		
		double totalDeIngressos = sessao.getTotalIngressos().doubleValue();
		double ingressosRestantes = totalDeIngressos - sessao.getIngressosReservados();

		BigDecimal preco;
		
		double taxaCinemaShow = 0.10;
		double taxaBalletOrquestra = 0.20;
		
		if(categoriaDoEspetaculo.equals(CINEMA) || categoriaDoEspetaculo.equals(SHOW)) {
			//quando estiver acabando os ingressos...
			
			if(ingressosRestantes / totalDeIngressos <= 0.05) {
				preco = atualizaPreco(sessao, taxaCinemaShow);
			} else {
				preco = sessao.getPreco();
			}
		} else if(categoriaDoEspetaculo.equals(BALLET) || categoriaDoEspetaculo.equals(ORQUESTRA)) {
			
			if(ingressosRestantes / totalDeIngressos <= 0.50) { 
				preco = atualizaPreco(sessao, taxaBalletOrquestra);
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal atualizaPreco(Sessao sessao, double taxa) {
		return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(taxa)));
	}

}