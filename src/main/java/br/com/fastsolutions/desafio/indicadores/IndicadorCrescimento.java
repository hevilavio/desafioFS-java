package br.com.fastsolutions.desafio.indicadores;

import java.util.List;
import java.util.Map;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Classe que indica o crescimento de uma Filial.
 * Perceba que, ela tamb�m pode indicar um crescimento negativo (queda),
 * de acordo como ela foi constru�da.
 * 
 * @author Hevil�vio
 * 
 * */
public class IndicadorCrescimento implements Indicador<Movimentacao, String>{
	
	private final Agrupador<Movimentacao, Map<String, List<Movimentacao>>> agrupador;
	private final Comparador<Double, Double> comparador;
	
	/**
	 * 
	 * @param agrupador A estrat�gia de agrupamento da lista Movimentacao
	 * @param comparador A estrat�gia para analisar se uma taxa pe relevante ou n�o
	 * 
	 * */
	public IndicadorCrescimento(Agrupador<Movimentacao, Map<String,List<Movimentacao>>> agrupador,
			Comparador<Double, Double> comparador){
		this.agrupador = agrupador;
		this.comparador = comparador;
	}
	
	
	@Override
	public String calcula(List<Movimentacao> list) {
		Map<String,List<Movimentacao>> agrupamento = agrupador.agrupa(list);

		double taxaRelevante = comparador.valorInicial();
		String chave = agrupador.getChave().getChave(list.get(0));
		
		// C�lculo baseado em valores inicial e final, interpretado como forma de porcentagem
		for(Map.Entry<String,List<Movimentacao>> entry : agrupamento.entrySet())
		{
			double valorInicial = entry.getValue().get(0).getValor();
			double valorFinal = entry.getValue().get(entry.getValue().size() - 1).getValor();
			
			double taxa = (valorFinal - valorInicial) / valorInicial;
			taxa = taxa / 100;// Just for fun!
			
			if(comparador.relevante(taxaRelevante, taxa)){
				taxaRelevante = taxa;
				chave = agrupador.getChave().getChave(entry.getValue().get(0));
			}
		}
		
		return chave;
	}

}
