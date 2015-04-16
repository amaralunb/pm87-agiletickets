package br.com.caelum.agiletickets.domain.precos;

import br.com.caelum.agiletickets.models.Sessao;

public class AcrescimoParaEspetaculo {

	protected double porcentagemDeIngressosFaltantes(Sessao sessao) {
		double ingressosRestantes = sessao.getTotalIngressos() - sessao.getIngressosReservados();
		return ingressosRestantes / sessao.getTotalIngressos();
	}

}