package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class IndicadorMaiorVendaFilialTest {

	private Indicador<Movimentacao, String> indicador;
	
	@Before
	public void init() {
		Comparador<Double, Double> c = new Comparador<Double, Double>() {
			
			@Override
			public boolean relevante(Double atual, Double novo) {
				return atual < novo;
			}

			@Override
			public Double valorInicial() {
				return Double.MIN_VALUE;
			}
		};
		
		this.indicador = new IndicadorVendaFilial(c);
	}
	
//	@Test(expected=Exception.class)
//	public void naoPossoCalcularMaiorVendaComListaVazia() {
//		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista();
//		indicador.calcula(movimentacoes);
//	}
	
	@Test
	public void possoCalcularMaiorVendaComListaUnitaria() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(100.0);
		String nomeEmpresa = indicador.calcula(movimentacoes);
		
		assertEquals("e0", nomeEmpresa);
	}
	
	@Test
	public void possoCalcularMaiorVendaComListaCrescente() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(10.0, 20.0, 30.0);
		String nomeEmpresa = indicador.calcula(movimentacoes);
		
		assertEquals("e2", nomeEmpresa);
	}
	
	@Test
	public void possoCalcularMaiorVendaComListaDecrescente() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(30.0, 20.0, 10.0);
		String nomeEmpresa = indicador.calcula(movimentacoes);
		
		assertEquals("e0", nomeEmpresa);
	}

}
