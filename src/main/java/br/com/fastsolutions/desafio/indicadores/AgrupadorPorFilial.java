package br.com.fastsolutions.desafio.indicadores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.fastsolutions.desafio.modelo.Movimentacao;


/**
 * Classe que realiza o agrupamento por Filial de uma lista de movimentações.
 * 
 * @author Hevilávio
 * 
 * */
public class AgrupadorPorFilial implements Agrupador<Movimentacao, Map<String, List<Movimentacao>>>{

	@Override
	public Map<String, List<Movimentacao>> agrupar(List<Movimentacao> list) {
		Map<String, List<Movimentacao>> agrupamento = new HashMap<>();
		String filial;
		
		Iterator<Movimentacao> iterator = list.iterator();
		while(iterator.hasNext()){
			Movimentacao movimentacao = iterator.next();
			filial = movimentacao.getFilial();
			
			if(agrupamento.containsKey(filial)){
				agrupamento.get(filial).add(movimentacao);
			}
			else{
				agrupamento.put(filial, movimentacao.toList());
			}
			
		}
		return agrupamento;
	}
 

}
