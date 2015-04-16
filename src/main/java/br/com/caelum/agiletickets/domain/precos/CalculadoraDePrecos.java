package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		TipoDeEspetaculo categoriaDoEspetaculo = sessao.getEspetaculo().getTipo();
		int ingressosRestantes = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		
		if(categoriaDoEspetaculo.equals(TipoDeEspetaculo.CINEMA) || categoriaDoEspetaculo.equals(TipoDeEspetaculo.SHOW)) {
			//quando estiver acabando os ingressos... 
			if(ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = sessao.getPreco();
			}
		} else if(categoriaDoEspetaculo.equals(TipoDeEspetaculo.BALLET)) {
			if(ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(categoriaDoEspetaculo.equals(TipoDeEspetaculo.ORQUESTRA)) {
			if(ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}

			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}  else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}