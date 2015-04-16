package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class AcrescimoParaEspetaculoNormal extends AcrescimoParaEspetaculo {

	static final double TAXA_ESPETACULO_NORMAL = 0.10;

	public BigDecimal acrescimoParaEspetaculoNormal(Sessao sessao) {
		BigDecimal preco;
		//quando estiver acabando os ingressos...
		
		if(sessao.porcentagemDeIngressosFaltantes() <= 0.05) {
			preco = AcrescimoParaEspetaculo.atualizaPreco(sessao, TAXA_ESPETACULO_NORMAL);
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

}
