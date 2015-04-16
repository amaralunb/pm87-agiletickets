package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.BALLET;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.CINEMA;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.ORQUESTRA;
import static br.com.caelum.agiletickets.models.TipoDeEspetaculo.SHOW;

public class CalculadoraDePrecos {

	private static final double TAXA_ESPETACULO_NORMAL = 0.10;
	private static final double TAXA_ESPETACULO_PREMIUM = 0.20;

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		TipoDeEspetaculo categoriaDoEspetaculo = sessao.getEspetaculo().getTipo();
		
		double totalDeIngressos = sessao.getTotalIngressos().doubleValue();
		double ingressosRestantes = totalDeIngressos - sessao.getIngressosReservados();

		BigDecimal preco;
		
		
		if(categoriaDoEspetaculo.equals(CINEMA) || categoriaDoEspetaculo.equals(SHOW)) {
			AcrescimoParaEspetaculoNormal acrescimoParaEspetaculoNormal = new AcrescimoParaEspetaculoNormal();
			preco = acrescimoParaEspetaculoNormal.acrescimoParaEspetaculoNormal(sessao, totalDeIngressos, ingressosRestantes, TAXA_ESPETACULO_NORMAL);
		} else if(categoriaDoEspetaculo.equals(BALLET) || categoriaDoEspetaculo.equals(ORQUESTRA)) {
			AcrescimoParaEspetaculoPremium acrescimoParaEspetaculoPremium = new AcrescimoParaEspetaculoPremium();
			preco = acrescimoParaEspetaculoPremium.acrescimoParaEspetaculoPremium(sessao, totalDeIngressos, ingressosRestantes, TAXA_ESPETACULO_PREMIUM);
		} else {
			//nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		} 

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	static BigDecimal atualizaPreco(Sessao sessao, double taxa) {
		return sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(taxa)));
	}

}