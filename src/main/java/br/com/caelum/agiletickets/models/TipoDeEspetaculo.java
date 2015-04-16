package br.com.caelum.agiletickets.models;

import br.com.caelum.agiletickets.domain.precos.AcrescimoParaEspetaculo;
import br.com.caelum.agiletickets.domain.precos.AcrescimoParaEspetaculoNormal;
import br.com.caelum.agiletickets.domain.precos.AcrescimoParaEspetaculoPremium;
import br.com.caelum.agiletickets.domain.precos.SemAcrescimo;

public enum TipoDeEspetaculo {
	
	CINEMA(new AcrescimoParaEspetaculoNormal()), 
	SHOW(new AcrescimoParaEspetaculoNormal()), 
	TEATRO(new SemAcrescimo()), 
	BALLET(new AcrescimoParaEspetaculoPremium()), 
	ORQUESTRA(new AcrescimoParaEspetaculoPremium());
	
	private AcrescimoParaEspetaculo acrescimo;
	
	private TipoDeEspetaculo(AcrescimoParaEspetaculo acrescimo){
		this.acrescimo = acrescimo;
	}
	
	public AcrescimoParaEspetaculo getAcrescimo() {
		return acrescimo;
	}
}
