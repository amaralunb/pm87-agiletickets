package br.com.caelum.agiletickets.domain.precos;

public class AcrescimoParaEspetaculo {

	protected double porcentagemDeIngressosFaltantes(double totalDeIngressos, double ingressosRestantes) {
		return ingressosRestantes / totalDeIngressos;
	}

}