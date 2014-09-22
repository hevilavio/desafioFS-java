package br.com.fastsolutions.desafio.console;

import java.util.List;

import br.com.fastsolutions.desafio.indicadores.Indicador;
import br.com.fastsolutions.desafio.indicadores.IndicadorMovimentacaoFactory;
import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class PainelIndicadores {
	private Indicador<Movimentacao, ?> indVendaPositivoFilial;
	private Indicador<Movimentacao, ?> indCrescimentoFilialPositivo;
	private Indicador<Movimentacao, ?> indCrescimentoFilialNegativo;
	private Indicador<Movimentacao, ?> indCrescimentoMesPositivo;

	
	
	public PainelIndicadores() { 
		IndicadorMovimentacaoFactory indicadorFactory = new IndicadorMovimentacaoFactory();

		indVendaPositivoFilial = indicadorFactory.indicadorVendaFilialPositivo();		
		indCrescimentoFilialPositivo = indicadorFactory.indicadorCrescimentoFilialPositivo();
		indCrescimentoFilialNegativo = indicadorFactory.indicadorCrescimentoFilialNegativo();
		indCrescimentoMesPositivo = indicadorFactory.indicadorCrescimentoMesPositivo();
	}

	public void calcularEExibir(List<Movimentacao> movimentacoes) {

		System.out.println("\n ==RESULTADOS== \n");

		System.out.println("Filial que mais vendeu: " + indVendaPositivoFilial.calcula(movimentacoes));
		System.out.println("Filial com maior crescimento: " + indCrescimentoFilialPositivo.calcula(movimentacoes));
		System.out.println("Filial com maior queda: " + indCrescimentoFilialNegativo.calcula(movimentacoes));
		System.out.println("Mês que a empresa mais vendeu: " + indCrescimentoMesPositivo.calcula(movimentacoes));
	}
}
