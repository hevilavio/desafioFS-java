package br.com.fastsolutions.desafio.indicadores;

import java.util.List;
import java.util.Map;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Apenas uma classe para auxiliar nos testes
 * */
public class GeradorDeAgrupamento {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, List<Movimentacao>> gerarAgrupamentoMovimentacao(
			Agrupador agrupador,
			String[] filiais, double[] valores){
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		return agrupador.agrupar(movimentacoes);
	}
}
