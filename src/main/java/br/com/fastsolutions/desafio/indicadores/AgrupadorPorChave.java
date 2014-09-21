package br.com.fastsolutions.desafio.indicadores;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.fastsolutions.desafio.modelo.Movimentacao;


/**
 * Classe que realiza o agrupamento por chave de 
 * agrupamento de uma lista de movimentações.
 * 
 * @author Hevilávio
 * 
 * */
public class AgrupadorPorChave implements Agrupador<Movimentacao, Map<String, List<Movimentacao>>>{
	ChaveAgrupamento<Movimentacao, String> chaveAgrupamento;
	
	/**
	 * @param chaveAgrupamento Chave a ser usada no critério de agrupamento
	 */
	public AgrupadorPorChave(ChaveAgrupamento<Movimentacao, String> chaveAgrupamento) {
		this.chaveAgrupamento = chaveAgrupamento;
	}
	
	@Override
	public Map<String, List<Movimentacao>> agrupa(List<Movimentacao> list) {
		Map<String, List<Movimentacao>> agrupamento = new HashMap<>();
		String chave;
		
		Iterator<Movimentacao> iterator = list.iterator();
		while(iterator.hasNext()){
			Movimentacao movimentacao = iterator.next(); 
			chave = chaveAgrupamento.getChave(movimentacao);
			
			if(agrupamento.containsKey(chave)){
				agrupamento.get(chave).add(movimentacao);
			}
			else{
				agrupamento.put(chave, movimentacao.toList());
			}
			
		}
		return agrupamento;
	}

	@Override
	public ChaveAgrupamento<Movimentacao, String> getChave() {
		return chaveAgrupamento;
	}


}
