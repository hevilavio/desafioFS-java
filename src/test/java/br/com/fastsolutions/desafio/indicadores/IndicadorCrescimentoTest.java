package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class IndicadorCrescimentoTest {

	private Indicador<Movimentacao, String> indicador;
	private final String[] filiais = new String[]{ "SP", "RJ", "RS", "SP", "RJ", "RS" };// MaiorC:SP, MenorC:RJ
	private final double[] valores = new double[]{ 10.0, 20.0, 30.0, 50.0, 20.0, 40.0 };
	IndicadorMovimentacaoFactory factory;
	
	@Before
	public void init() {
		factory = new IndicadorMovimentacaoFactory();
	}
	
	
	@Test
	public void possoCalcularCrescimentoPositivoComUmaMovimentacao(){
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(100.0);
		this.indicador = factory.indicadorPorFilialPositivo();
		String nomeFilial = indicador.calcula(movimentacoes);
		
		assertEquals("e0", nomeFilial);
	}
	@Test
	public void possoCalcularCrescimentoNegativoComUmaMovimentacao(){
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(100.0);
		this.indicador = factory.indicadorPorFilialNegativo();
		String nomeFilial = indicador.calcula(movimentacoes);
		
		assertEquals("e0", nomeFilial);
	}
	
	@Test
	public void possoCalcularCrescimentoPositivoComMaisDeUmaMovimentacao(){
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		this.indicador = factory.indicadorPorFilialPositivo();
		String nomeFilial = indicador.calcula(movimentacoes);
		
		assertEquals("SP", nomeFilial);
	}
	@Test
	public void possoCalcularCrescimentoNegativoComMaisDeUmaMovimentacao(){
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		this.indicador = factory.indicadorPorFilialNegativo();
		String nomeFilial = indicador.calcula(movimentacoes);
		
		assertEquals("RJ", nomeFilial);
	}
//	
//	@Test
//	public void possoCalcularMaiorCrescimentoMes(){
//		
//		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
//		this.indicador = factory.indicadorPorMesVendaPositivo();
//		String mes = indicador.calcula(movimentacoes);
//		
//		assertEquals("e5", mes);
//	}
	
	
}
