package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class SemAcrescimo extends AcrescimoParaEspetaculo {

	@Override
	public BigDecimal calculaAcrescimo(Sessao sessao) {
		return sessao.getPreco();
	}

}
