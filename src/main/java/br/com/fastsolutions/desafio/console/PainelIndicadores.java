package br.com.fastsolutions.desafio.console;

import java.util.List;

import br.com.fastsolutions.desafio.indicadores.Indicador;
import br.com.fastsolutions.desafio.indicadores.IndicadorMovimentacaoFactory;
import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class PainelIndicadores {
	private Indicador<Movimentacao, ?> indicadorPositivoVenda;
	private Indicador<Movimentacao, ?> indicadorCrescimento;
	private Indicador<Movimentacao, ?> indicadorQueda;
	private Indicador<Movimentacao, ?> indicadorMesMaiorVenda;

	public PainelIndicadores() { 
		IndicadorMovimentacaoFactory indicadorFactory = new IndicadorMovimentacaoFactory();

		indicadorPositivoVenda = indicadorFactory.indicadorMesMaiorVenda();		
		indicadorCrescimento = indicadorFactory.indicadorPorFilialPositivo();
		indicadorQueda = indicadorFactory.indicadorPorFilialNegativo();
		indicadorMesMaiorVenda = indicadorFactory.indicadorPorMesVendaPositivo();
	}

	public void calcularEExibir(List<Movimentacao> movimentacoes) {

		System.out.println("\n ==RESULTADOS== \n\n");

		System.out.println("Filial que mais vendeu: " + indicadorPositivoVenda.calcula(movimentacoes));
		System.out.println("Filial com maior crescimento: " + indicadorCrescimento.calcula(movimentacoes));
		System.out.println("Filial com maior queda: " + indicadorQueda.calcula(movimentacoes));
		System.out.println("Mês que a empresa mais vendeu: " + indicadorMesMaiorVenda.calcula(movimentacoes));
	}
}
