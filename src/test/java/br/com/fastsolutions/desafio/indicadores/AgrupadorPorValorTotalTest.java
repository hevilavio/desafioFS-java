package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class AgrupadorPorValorTotalTest {
	private final String[] filiaisRepetidas = new String[]{ "SP", "SP", "SP" };
	private final double[] valores = new double[]{ 10.0, 20.0, 30.0 };
	
	private Agrupador<Movimentacao, Map<String, List<Movimentacao>>> agrupador;
	
	@Before
	public void init(){
		this.agrupador = new AgrupadorPorValorTotal();
	}
	
	@Test
	public void possoAgruparListaUnitaria() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarLista(10.0);
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(movimentacoes);
		
		assertEquals(1, agrupamento.size(), 0.00001); 
		assertEquals(10.0, agrupamento.get("e0").get(0).getValor(), 0.00001); 
	}
	
	@Test
	public void possoAgruparListaNaoUnitaria() {
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiaisRepetidas, valores);
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(movimentacoes);
		
		assertEquals(1, agrupamento.size(), 0.00001); 
		assertEquals(60.0, agrupamento.get("SP").get(0).getValor(), 0.00001); 
	}
	
	public void valoresDeAgrupamentoConferem() {
		String[] filiais = new String[]{ "SP", "RJ", "RS", "SP", "RJ", "RS" };
		double[] valores = new double[]{ 10.0, 20.0, 30.0, 50.0, 20.0, 40.0 };
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(movimentacoes);
		
		assertEquals(1, agrupamento.size(), 0.00001); 
		assertEquals(170.0, agrupamento.get("SP").get(0).getValor(), 0.00001); 
	}

}
