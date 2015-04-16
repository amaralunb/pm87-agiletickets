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
		
		BigDecimal preco;
		
		AcrescimoParaEspetaculo acrescimo = null;
		if(categoriaDoEspetaculo.equals(CINEMA) || categoriaDoEspetaculo.equals(SHOW)) {
			acrescimo = new AcrescimoParaEspetaculoNormal();
		} else if(categoriaDoEspetaculo.equals(BALLET) || categoriaDoEspetaculo.equals(ORQUESTRA)) {
			acrescimo = new AcrescimoParaEspetaculoPremium();
		} 

		if(acrescimo != null){
			preco = acrescimo.calculaAcrescimo(sessao);
		} else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}