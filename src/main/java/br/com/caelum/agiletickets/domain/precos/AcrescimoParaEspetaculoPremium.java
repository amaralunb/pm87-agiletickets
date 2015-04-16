package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class AcrescimoParaEspetaculoPremium extends AcrescimoParaEspetaculo {

	static final double TAXA_ESPETACULO_PREMIUM = 0.20;

	public BigDecimal acrescimoParaEspetaculoPremium(Sessao sessao) {
		BigDecimal preco;
		if(sessao.porcentagemDeIngressosFaltantes() <= 0.50) { 
			preco = atualizaPreco(sessao, TAXA_ESPETACULO_PREMIUM);
		} else {
			preco = sessao.getPreco();
		}
		
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

}
