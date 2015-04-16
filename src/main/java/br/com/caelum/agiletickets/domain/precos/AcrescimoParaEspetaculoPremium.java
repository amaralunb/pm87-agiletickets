package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class AcrescimoParaEspetaculoPremium {

	static BigDecimal acrescimoParaEspetaculoPremium(Sessao sessao,
			double totalDeIngressos, double ingressosRestantes,
			double taxaBalletOrquestra) {
		BigDecimal preco;
		if(ingressosRestantes / totalDeIngressos <= 0.50) { 
			preco = CalculadoraDePrecos.atualizaPreco(sessao, taxaBalletOrquestra);
		} else {
			preco = sessao.getPreco();
		}
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

}
