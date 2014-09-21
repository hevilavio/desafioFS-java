package br.com.fastsolutions.desafio.indicadores;

import java.util.Iterator;
import java.util.List;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Indica uma rela��o de vendas para uma lista de movimenta��es,
 * obedecendo � regra de compara��o.
 * 
 * */
public class IndicadorVendaFilial implements Indicador<Movimentacao, String>{
	
	private final Comparador<Double, Double> comparador;
	
	/**
	 * @param comparador Estrat�gia de compara��o para a relev�ncia do indicador
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
