package br.com.fastsolutions.desafio.indicadores;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Indica uma relação de vendas para uma lista de movimentações, obedecendo à
 * regra de comparação.
 * 
 * */
public class IndicadorVenda implements Indicador<Movimentacao, String> {

	private final Comparador<Double, Double> comparador;
	private final Agrupador<Movimentacao, Map<String, List<Movimentacao>>> agrupadorPorChave;
	private final Agrupador<Movimentacao, Map<String,List<Movimentacao>>> agrupadorPorValor;
	private final ChaveAgrupamento<Movimentacao, String> chaveAgrupamento;
	
	/**
	 * @param comparador
	 *            Estratégia de comparação para a relevância do indicador <br/>
	 * O agrupamento é feito por <b>Filial</b>
	 *            
	 */
	public IndicadorVenda(ChaveAgrupamento<Movimentacao, String> chave, Comparador<Double, Double> comparador) {
		
		this.comparador = comparador;
		this.agrupadorPorChave = new AgrupadorPorChave(chave);
		this.agrupadorPorValor = new AgrupadorPorValorTotal();
		this.chaveAgrupamento = chave;
	}

	@Override
	public String calcula(List<Movimentacao> list) {
		String chave = null;
		double atual = comparador.valorInicial();
		TreeMap<String, List<Movimentacao>> agrupamentoValor;
		
		// Agrupamos por chave (filial ou mes)
		Map<String,List<Movimentacao>> agrupamentoChave = agrupadorPorChave.agrupa(list);
		for(Map.Entry<String,List<Movimentacao>> entry : agrupamentoChave.entrySet())
		{
			// agrupamos os valores do agrupamento anterior
			agrupamentoValor = (TreeMap<String, List<Movimentacao>>)
					agrupadorPorValor.agrupa(entry.getValue());

			Movimentacao nova = agrupamentoValor.firstEntry().getValue().get(0);
			if(comparador.relevante(atual, nova.getValor())){
				atual = nova.getValor();
				chave = chaveAgrupamento.getChave(nova);
			}
			
		}
		return chave;
	}
}
