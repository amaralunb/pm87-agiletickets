package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	private static final TipoDeEspetaculo ORQUESTRA = TipoDeEspetaculo.ORQUESTRA;
	private static final TipoDeEspetaculo BALLET = TipoDeEspetaculo.BALLET;
	private static final TipoDeEspetaculo SHOW = TipoDeEspetaculo.SHOW;
	private static final TipoDeEspetaculo CINEMA = TipoDeEspetaculo.CINEMA;
	private static final TipoDeEspetaculo TEATRO = TipoDeEspetaculo.TEATRO;

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		TipoDeEspetaculo categoriaDoEspetaculo = sessao.getEspetaculo().getTipo();
		int ingressosRestantes = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		
		if(categoriaDoEspetaculo.equals(CINEMA) || categoriaDoEspetaculo.equals(SHOW)) {
			//quando estiver acabando os ingressos... 
			if(ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			} else {
				preco = sessao.getPreco();
			}
		} else if(categoriaDoEspetaculo.equals(BALLET)) {
			if(ingressosRestantes / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
				preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
			} else {
				preco = sessao.getPreco();
			}
			
			if(sessao.getDuracaoEmMinutos() > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		} else if(categoriaDoEspetaculo.equals(ORQUESTRA)) {
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