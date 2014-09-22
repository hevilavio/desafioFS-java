package br.com.fastsolutions.desafio.indicadores;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class IndicadorMovimentacaoFactory {

	public IndicadorCrescimento indicadorCrescimentoFilialPositivo() {
		return new IndicadorCrescimento(
				new AgrupadorPorChave(new ChaveAgrupamentoFilial()), 
				new ComparadorDoublePositivo());
	}

	public IndicadorCrescimento indicadorCrescimentoFilialNegativo() {
		return new IndicadorCrescimento(
				new AgrupadorPorChave(new ChaveAgrupamentoFilial()), 
				new ComparadorDoubleNegativo());
	}

	public IndicadorCrescimento indicadorCrescimentoMesPositivo() {

		return new IndicadorCrescimento(
				new AgrupadorPorChave(new ChaveAgrupamentoMes()), 
				new ComparadorDoublePositivo());
	}

	public Indicador<Movimentacao, String> indicadorVendaMesPositivo() {
		return new IndicadorVenda(
				new ChaveAgrupamentoMes(),
				new ComparadorDoublePositivo());
	}
	
	public Indicador<Movimentacao, String> indicadorVendaFilialPositivo() {
		return new IndicadorVenda(
				new ChaveAgrupamentoFilial(),
				new ComparadorDoublePositivo());
	}
}
