package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class IndicadorVendaFilialTest {

	private Indicador<Movimentacao, String> indicadorMaiorVenda;
	private Indicador<Movimentacao, String> indicadorMenorVenda;

	@Before
	public void init() {
		Comparador<Double, Double> comparadorPositivo = new Comparador<Double, Double>() {

			@Override
			public boolean relevante(Double atual, Double novo) {
				return atual < novo;
			}

			@Override
			public Double valorInicial() {
				return Double.MIN_VALUE;
			}
		};
		Comparador<Double, Double> comparadorNegativo = new Comparador<Double, Double>() {

			@Override
			public boolean relevante(Double atual, Double novo) {
				return novo < atual;
			}

			@Override
			public Double valorInicial() {
				return Double.MAX_VALUE;
			}
		};

		this.indicadorMaiorVenda = new IndicadorVendaFilial(comparadorPositivo);
		this.indicadorMenorVenda = new IndicadorVendaFilial(comparadorNegativo);
	}

	// @Test(expected=Exception.class)
	// public void naoPossoCalcularMaiorVendaComListaVazia() {
	// List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista();
	// indicador.calcula(movimentacoes);
	// }

	@Test
	public void possoCalcularMaiorVendaComListaUnitaria() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(100.0);
		String nomeEmpresa = indicadorMaiorVenda.calcula(movimentacoes);

		assertEquals("e0", nomeEmpresa);
	}
	

	@Test
	public void possoCalcularMaiorVendaComListaCrescente() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(
				10.0, 20.0, 30.0);
		String nomeEmpresa = indicadorMaiorVenda.calcula(movimentacoes);

		assertEquals("e2", nomeEmpresa);
	}

	@Test
	public void possoCalcularMaiorVendaComListaDecrescente() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(
				30.0, 20.0, 10.0);
		String nomeEmpresa = indicadorMaiorVenda.calcula(movimentacoes);

		assertEquals("e0", nomeEmpresa);
	}

	@Test
	public void possoCalcularMaiorVendaComListaMixta() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes
				.gerarListaComNomesDeFilial();
		String nomeEmpresa = indicadorMaiorVenda.calcula(movimentacoes);

		assertEquals("RS", nomeEmpresa);
	}

	@Test
	public void possoCalcularMenorVendaComListaMixta() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes
				.gerarListaComNomesDeFilial();
		String nomeEmpresa = indicadorMenorVenda.calcula(movimentacoes);

		assertEquals("RJ", nomeEmpresa);
	}

}
