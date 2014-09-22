package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class IndicadorVendaTest {

	private Indicador<Movimentacao, String> indVendaPositivoFilial; 
	private Indicador<Movimentacao, String> indVendaPositivoMes;

	@Before
	public void init() {
		 
		IndicadorMovimentacaoFactory factory = new IndicadorMovimentacaoFactory();
		
		this.indVendaPositivoFilial = factory.indicadorVendaFilialPositivo(); 
		this.indVendaPositivoMes = factory.indicadorVendaMesPositivo();
	} 
	
	@Test
	public void possoCalcularMaiorVendaComListaUnitaria() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes
				.gerarLista(100.0);
		String nomeEmpresa = indVendaPositivoFilial.calcula(movimentacoes);

		assertEquals("e0", nomeEmpresa);
	}
 
	@Test
	public void possoCalcularMaiorVendaComListaCrescente() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(
				10.0, 20.0, 30.0);
		String nomeEmpresa = indVendaPositivoFilial.calcula(movimentacoes);

		assertEquals("e2", nomeEmpresa);
	}
 
	@Test
	public void possoCalcularMaiorVendaComListaDecrescente() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(
				30.0, 20.0, 10.0);
		String nomeEmpresa = indVendaPositivoFilial.calcula(movimentacoes);

		assertEquals("e0", nomeEmpresa);
	}

	@Test
	public void possoCalcularMaiorVendaComListaMixta() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes
				.gerarListaComNomesDeFilial();
		String nomeEmpresa = indVendaPositivoFilial.calcula(movimentacoes);

		assertEquals("RS", nomeEmpresa);
	}

	@Test
	public void possoCalcularMenorVendaComListaMixta() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes
				.gerarListaComNomesDeFilial();
		String nomeEmpresa = indVendaPositivoMes.calcula(movimentacoes);

		assertEquals("m3", nomeEmpresa);
	}

}
