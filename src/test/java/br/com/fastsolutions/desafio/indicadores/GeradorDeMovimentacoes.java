package br.com.fastsolutions.desafio.indicadores;

import java.util.LinkedList;
import java.util.List;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Apenas um auxiliar para os testes
 * */
public class GeradorDeMovimentacoes {

	/**
	 * Gera uma lista com valores:</br>
	 * <b>nomeEmpresa:</b> "e" + contador <br/>
	 * <b>nomeMes:</b> "m" + contador <br/>
	 * */
	public static List<Movimentacao> gerarLista(double... valores){
		List<Movimentacao> movimentacoes = new LinkedList<>();
		for (int i = 0; i < valores.length; i++) {
			movimentacoes.add(new Movimentacao("e" + i, "m" + i, valores[i]));
		}
		return movimentacoes;
	}
	
	/**
	 * Gera lista com a respectiva ordem de filial->valor.
	 * 
	 * */
	public static List<Movimentacao> gerarListaComNomesDeFilial(String[] filiais, double[] valores){
		if(filiais.length != valores.length){
			throw new IllegalArgumentException("Os tamanhos das listas devem ser iguais!");
		}
		
		List<Movimentacao> movimentacoes = new LinkedList<>();
		for (int i = 0; i < valores.length; i++) {
			movimentacoes.add(new Movimentacao(filiais[i], "m" + i, valores[i]));
		}
		return movimentacoes;
	}

	/**
	 * Apenas lista com valores default<br/>
	 * <b>Maior venda:</b> RS
	 * <b>Menor venda:</b>RJ
	 * 
	 * */
	public static List<Movimentacao> gerarListaComNomesDeFilial() {
		String[] filiais = new String[]{ "SP", "RJ", "RS", "SP", "RJ", "RS" };
		double[] valores = new double[]{ 10.0, 20.0, 30.0, 50.0, 20.0, 40.0 };
		return gerarListaComNomesDeFilial(filiais, valores);
	}
}
