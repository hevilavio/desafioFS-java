package br.com.fastsolutions.desafio.indicadores;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class AgrupadorPorChaveTest {
	
	private List<Movimentacao> movimentacoes;
	private Agrupador<Movimentacao, Map<String, List<Movimentacao>>> agrupador;
	private Agrupador<Movimentacao, Map<String, List<Movimentacao>>> agrupadorMes;
	
	private final String[] filiais = new String[]{ "SP", "RJ", "RS", "SP", "RJ", "RS" };
	private final double[] valores = new double[]{ 10.0, 20.0, 30.0, 20.0, 40.0, 60.0 };
	
	@Before
	public void init(){
		ChaveAgrupamento<Movimentacao, String> chaveFilial = new ChaveAgrupamento<Movimentacao, String>() {
			@Override
			public String getChave(Movimentacao item) {
				return item.getFilial();
			}
		};
		ChaveAgrupamento<Movimentacao, String> chaveMes = new ChaveAgrupamento<Movimentacao, String>() {
			@Override
			public String getChave(Movimentacao item) {
				return item.getMes();
			}
		};

		agrupador = new AgrupadorPorChave(chaveFilial);
		agrupadorMes = new AgrupadorPorChave(chaveMes);
		movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
	}
	
	@Test
	public void possoAgruparListaSemFiliaisDeMesmoNome() {
		String[] filiaisMesmoNome = new String[]{ "SP", "RJ", "RS" };
		double[] valores = new double[]{ 10.0, 20.0, 30.0 };
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiaisMesmoNome, valores);
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(movimentacoes);

		assertEquals(filiaisMesmoNome.length, agrupamento.size());
	}
	
	@Test
	public void possoAgruparListaComFiliaisDeMesmoNome() {
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(this.movimentacoes);
		assertEquals(3, agrupamento.size());
	}
	
	@Test
	public void tenhoTamanhoCorrespondenteEmSublista() { 
		
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(this.movimentacoes);

		assertEquals(2, agrupamento.get("SP").size());
		assertEquals(2, agrupamento.get("RJ").size());
		assertEquals(2, agrupamento.get("RS").size());
	}
	
	@Test
	public void tenhoValoresCorrespondentesEmSublista() {

		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(this.movimentacoes);

		assertEquals(10.0, agrupamento.get("SP").get(0).getValor(), 0.00001);
		assertEquals(20.0, agrupamento.get("SP").get(1).getValor(), 0.00001);
		
		assertEquals(20.0, agrupamento.get("RJ").get(0).getValor(), 0.00001);
		assertEquals(40.0, agrupamento.get("RJ").get(1).getValor(), 0.00001);
		
		assertEquals(30.0, agrupamento.get("RS").get(0).getValor(), 0.00001);
		assertEquals(60.0, agrupamento.get("RS").get(1).getValor(), 0.00001);
	}
	
	@Test
	public void possoAgruparListaNaoSimetrica() {
		String[] filiais = new String[]{ "SP", "RJ", "RS", "RJ", "SP" };
		double[] valores = new double[]{ 10.0, 20.0, 30.0, 40.0, 50.0 };
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		Map<String, List<Movimentacao>> agrupamento = agrupador.agrupa(movimentacoes);

		assertEquals(2, agrupamento.get("SP").size());
		assertEquals(10.0, agrupamento.get("SP").get(0).getValor(), 0.00001);
		assertEquals(50.0, agrupamento.get("SP").get(1).getValor(), 0.00001);

		assertEquals(2, agrupamento.get("RJ").size());
		assertEquals(20.0, agrupamento.get("RJ").get(0).getValor(), 0.00001);
		assertEquals(40.0, agrupamento.get("RJ").get(1).getValor(), 0.00001);
		
		assertEquals(1, agrupamento.get("RS").size());
		assertEquals(30.0, agrupamento.get("RS").get(0).getValor(), 0.00001);
	}
	
	@Test
	public void possoAgruparPorMes() {
		String[] filiais = new String[]{ "SP", "RJ", "RS", "RJ", "SP" };
		double[] valores = new double[]{ 10.0, 20.0, 30.0, 40.0, 50.0 };
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		Map<String, List<Movimentacao>> agrupamento = agrupadorMes.agrupa(movimentacoes);

		assertEquals(5, agrupamento.size());  
	}
	@Test
	public void possoCalcularMaiorVendaAgrupamentoMes() {
		String[] filiais = new String[]{ "SP", "RJ", "RS", "RJ", "SP" };
		double[] valores = new double[]{ 10.0, 20.0, 30.0, 40.0, 50.0 };
		
		List<Movimentacao> movimentacoes = GeradorDeMovimentacoes.gerarListaComNomesDeFilial(filiais, valores);
		Map<String, List<Movimentacao>> agrupamento = agrupadorMes.agrupa(movimentacoes);

		assertEquals(5, agrupamento.size());  
	}
	

}
