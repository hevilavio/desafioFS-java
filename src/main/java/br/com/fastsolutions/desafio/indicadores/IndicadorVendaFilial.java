package br.com.fastsolutions.desafio.indicadores;

import java.util.Iterator;
import java.util.List;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Indica uma relação de vendas para uma lista de movimentações,
 * obedecendo à regra de comparação.
 * 
 * */
public class IndicadorVendaFilial implements Indicador<Movimentacao, String>{
	
	private final Comparador<Double, Double> comparador;
	
	/**
	 * @param comparador Estratégia de comparação para a relevância do indicador
	 */
	public IndicadorVendaFilial(Comparador<Double, Double> comparador) {
		this.comparador = comparador;
	}
	
	@Override
	public String calcula(List<Movimentacao> list) {
		double maior = -1.0;
		String nomeEmpresa = null;
		
		Iterator<Movimentacao> iterator = list.iterator();
		while(iterator.hasNext()){
			Movimentacao movimentacao = iterator.next();
			if(comparador.relevante(maior, movimentacao.getValor())){
				maior = movimentacao.getValor();
				nomeEmpresa = movimentacao.getFilial();
			}
		}
		
		return nomeEmpresa;
	}
}
