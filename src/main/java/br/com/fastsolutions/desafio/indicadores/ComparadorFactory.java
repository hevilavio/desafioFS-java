package br.com.fastsolutions.desafio.indicadores;

public class ComparadorFactory {

	public Comparador<Double, Double> comparadorPositivoDouble(){
		return new Comparador<Double, Double>() {

			@Override
			public boolean relevante(Double atual, Double novo) {
				return atual < novo;
			}

			@Override
			public Double valorInicial() {
				return Double.MIN_VALUE;
			}
		};
	}
}
