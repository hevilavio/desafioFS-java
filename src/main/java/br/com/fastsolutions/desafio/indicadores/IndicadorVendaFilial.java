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
public class IndicadorVendaFilial implements Indicador<Movimentacao, String> {

	private final Comparador<Double, Double> comparador;
	private final Agrupador<Movimentacao, Map<String, List<Movimentacao>>> agrupadorFilial;
	private final Agrupador<Movimentacao, Map<String,List<Movimentacao>>> agrupadorPorValor;

	/**
	 * @param comparador
	 *            Estratégia de comparação para a relevância do indicador <br/>
	 * O agrupamento é feito por <b>Filial</b>
	 *            
	 */
	public IndicadorVendaFilial(Comparador<Double, Double> comparador) {
		
		ChaveAgrupamento<Movimentacao, String> chave = new ChaveAgrupamento<Movimentacao, String>() {
			
			@Override
			public String getChave(Movimentacao item) {
				return item.getFilial();
			}
		};
		
		this.comparador = comparador;
		this.agrupadorFilial = new AgrupadorPorChave(chave);
		this.agrupadorPorValor = new AgrupadorPorValorTotal();
	}

	@Override
	public String calcula(List<Movimentacao> list) {
		String nomeEmpresa = null;
		double atual = comparador.valorInicial();
		TreeMap<String, List<Movimentacao>> agrupamentoValor;
		
		// Agrupamos por nome de Filial
		Map<String,List<Movimentacao>> agrupamentoFilial = agrupadorFilial.agrupa(list);
		for(Map.Entry<String,List<Movimentacao>> entry : agrupamentoFilial.entrySet())
		{
			// agrupamos todos os valores de uma Filial
			agrupamentoValor = (TreeMap<String, List<Movimentacao>>)
					agrupadorPorValor.agrupa(entry.getValue());

			Movimentacao nova = agrupamentoValor.firstEntry().getValue().get(0);
			if(comparador.relevante(atual, nova.getValor())){
				atual = nova.getValor();
				nomeEmpresa = nova.getFilial();
			}
			
		}
		return nomeEmpresa;
	}
}
