package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class IndicadorVendaFilialTest {

	private Indicador<Movimentacao, String> indicadorMaiorVenda; 
	private Indicador<Movimentacao, String> indicadorMesMaiorVenda;

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

		ChaveAgrupamento<Movimentacao, String> chave = new ChaveAgrupamento<Movimentacao, String>() {

			@Override
			public String getChave(Movimentacao item) {
				return item.getFilial();
			}
		};
		ChaveAgrupamento<Movimentacao, String> chaveMes = new ChaveAgrupamento<Movimentacao, String>() {

			@Override
			public String getChave(Movimentacao item) {
				System.out.println(item.getMes());
				return item.getMes();
			}
		};

		this.indicadorMaiorVenda = new IndicadorVendaFilial(comparadorPositivo,chave); 
		this.indicadorMesMaiorVenda = new IndicadorVendaFilial(comparadorPositivo,chaveMes);
	}

	// (expected=Exception.class)
	// public void naoPossoCalcularMaiorVendaComListaVazia() {
	// List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista();
	// indicador.calcula(movimentacoes);
	// }
 
	@Test
	public void possoCalcularMaiorVendaComListaUnitaria() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes
				.gerarLista(100.0);
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
		String nomeEmpresa = indicadorMesMaiorVenda.calcula(movimentacoes);

		assertEquals("m3", nomeEmpresa);
	}

}
