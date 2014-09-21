package br.com.fastsolutions.desafio.indicadores;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.fastsolutions.desafio.modelo.Movimentacao;
import br.com.fastsolutions.desafio.modelo.MovimentacaoBuilder;

/**
 * Realiza o agrupamento de uma lista de movimentações
 * em um TreeMap com uma única movimentação (soma
 * de todos os elementos da lista).
 * 
 * @author Hevilávio
 * 
 * */
public class AgrupadorPorValorTotal implements Agrupador<Movimentacao, Map<String, List<Movimentacao>>> {

	@Override
	public Map<String, List<Movimentacao>> agrupa(List<Movimentacao> list) {
		double total = 0.0;
		String nomeFilial = list.get(0).getFilial();
		
		Map<String, List<Movimentacao>> agrupamento = new TreeMap<>();
		Iterator<Movimentacao> iterator = list.iterator();

		while (iterator.hasNext()) {
			Movimentacao movimentacao = iterator.next();
			total += movimentacao.getValor();
		}

		Movimentacao movimentacaoTotal = new MovimentacaoBuilder()
				.comFilial(nomeFilial).comMes("AGRUPAMENTO").comValor(total)
				.gerarMovimentacao();
 
		agrupamento.put(nomeFilial, movimentacaoTotal.toList());
		return agrupamento;
	}
}
