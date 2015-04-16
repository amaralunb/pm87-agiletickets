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
		BigDecimal preco;
		TipoDeEspetaculo categoriaDoEspetaculo = sessao.getEspetaculo().getTipo();
		int ingressosRestantes = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		double totalDeIngressos = sessao.getTotalIngressos().doubleValue();
		double taxaDeIngresso;
		
		if(categoriaDoEspetaculo.equals(CINEMA) || categoriaDoEspetaculo.equals(SHOW)) {
			//quando estiver acabando os ingressos...
			taxaDeIngresso = 0.10;
			
			if(ingressosRestantes / totalDeIngressos <= 0.05) {
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(taxaDeIngresso)));
			} else {
				preco = sessao.getPreco();
			}
		} else if(categoriaDoEspetaculo.equals(BALLET) || categoriaDoEspetaculo.equals(ORQUESTRA)) {
			taxaDeIngresso = 0.20;
			
			if(ingressosRestantes / totalDeIngressos <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(taxaDeIngresso)));
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

}