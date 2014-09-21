package br.com.fastsolutions.desafio.indicadores;


public class IndicadorMovimentacaoFactory {
	
	public IndicadorCrescimento indicadorPorFilialPositivo(){
		Comparador<Double, Double> a = new Comparador<Double, Double>() {
			@Override
			public boolean relevante(Double atual, Double novo) {
				return atual < novo;
			}

			@Override
			public Double valorInicial() {
				return Double.MIN_VALUE;
			}
		};
		
		return new IndicadorCrescimento(new AgrupadorPorFilial(), a);
	}
	
	public IndicadorCrescimento indicadorPorFilialNegativo(){
		Comparador<Double, Double> a = new Comparador<Double, Double>() {
			@Override
			public boolean relevante(Double atual, Double novo) {
				return novo < atual;
			}

			@Override
			public Double valorInicial() {
				return Double.MAX_VALUE;
			}
		};
		
		return new IndicadorCrescimento(new AgrupadorPorFilial(), a);
	}
}
