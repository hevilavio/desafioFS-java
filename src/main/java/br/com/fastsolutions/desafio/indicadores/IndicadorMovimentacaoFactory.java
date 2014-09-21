package br.com.fastsolutions.desafio.indicadores;

import br.com.fastsolutions.desafio.modelo.Movimentacao;


public class IndicadorMovimentacaoFactory {
	
	private ChaveAgrupamento<Movimentacao, String> chaveFilial;

	public IndicadorMovimentacaoFactory() {
this.chaveFilial = new ChaveAgrupamento<Movimentacao, String>() {
			
			@Override
			public String getChave(Movimentacao item) {
				return item.getFilial();
			}
		};
	}
	
	
	public IndicadorCrescimento indicadorPorFilialPositivo(){
		Comparador<Double, Double> comparador = new Comparador<Double, Double>() {
			@Override
			public boolean relevante(Double atual, Double novo) {
				return atual < novo;
			}

			@Override
			public Double valorInicial() {
				return Double.MIN_VALUE;
			}
		};
		
		return new IndicadorCrescimento(new AgrupadorPorChave(chaveFilial), comparador);
	}
	
	public IndicadorCrescimento indicadorPorFilialNegativo(){
		Comparador<Double, Double> comparador = new Comparador<Double, Double>() {
			@Override
			public boolean relevante(Double atual, Double novo) {
				return novo < atual;
			}

			@Override
			public Double valorInicial() {
				return Double.MAX_VALUE;
			}
		};
		
		return new IndicadorCrescimento(new AgrupadorPorChave(chaveFilial), comparador);
	}


	public IndicadorCrescimento indicadorPorVendaPositivo(){
		Comparador<Double, Double> comparador = new Comparador<Double, Double>() {
			@Override
			public boolean relevante(Double atual, Double novo) {
				return atual < novo;
			}

			@Override
			public Double valorInicial() {
				return Double.MIN_VALUE;
			}
		};
		
		return new IndicadorCrescimento(new AgrupadorPorChave(chaveFilial), comparador);
	}
}
