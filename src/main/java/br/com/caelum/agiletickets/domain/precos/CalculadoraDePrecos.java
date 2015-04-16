package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		TipoDeEspetaculo categoriaDoEspetaculo = sessao.getEspetaculo().getTipo();
		
		BigDecimal preco;
		
		AcrescimoParaEspetaculo acrescimo = categoriaDoEspetaculo.getAcrescimo();

		preco = acrescimo.calculaAcrescimo(sessao);
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}