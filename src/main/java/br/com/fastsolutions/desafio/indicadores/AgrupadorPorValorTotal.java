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
		String filial = list.get(0).getFilial();
		String mes = list.get(0).getMes();
		
		
		Map<String, List<Movimentacao>> agrupamento = new TreeMap<>();
		Iterator<Movimentacao> iterator = list.iterator();

		while (iterator.hasNext()) {
			Movimentacao movimentacao = iterator.next();
			total += movimentacao.getValor();
		}

		Movimentacao movimentacaoTotal = new MovimentacaoBuilder()
				.comFilial(filial).comMes(mes).comValor(total)
				.gerarMovimentacao();
 
		agrupamento.put(filial, movimentacaoTotal.toList());
		return agrupamento;
	}

	@Override
	public ChaveAgrupamento<Movimentacao, String> getChave() {
		throw new IllegalStateException("Nao implementado, apenas por obrigação da interface");
	}
}
