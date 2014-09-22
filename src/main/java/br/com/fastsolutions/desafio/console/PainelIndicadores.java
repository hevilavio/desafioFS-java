package br.com.fastsolutions.desafio.console;

import java.util.List;

import br.com.fastsolutions.desafio.indicadores.Indicador;
import br.com.fastsolutions.desafio.indicadores.IndicadorMovimentacaoFactory;
import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class PainelIndicadores {
	private Indicador<Movimentacao, String> indVendaPositivoFilial;
	private Indicador<Movimentacao, String> indCrescimentoFilialPositivo;
	private Indicador<Movimentacao, String> indCrescimentoFilialNegativo;
	private Indicador<Movimentacao, String> indCrescimentoMesPositivo;
	 
	private String mesMaiorVenda;
	private String filialMaiorCrescimento;
	private String filialMaiorQueda;
	private String filialMaiorVenda;
	
	public PainelIndicadores() { 
		IndicadorMovimentacaoFactory indicadorFactory = new IndicadorMovimentacaoFactory();

		indVendaPositivoFilial = indicadorFactory.indicadorVendaFilialPositivo();		
		indCrescimentoFilialPositivo = indicadorFactory.indicadorCrescimentoFilialPositivo();
		indCrescimentoFilialNegativo = indicadorFactory.indicadorCrescimentoFilialNegativo();
		indCrescimentoMesPositivo = indicadorFactory.indicadorCrescimentoMesPositivo();
	}
	
	public void calcular(List<Movimentacao> movimentacoes) {
		filialMaiorVenda = indVendaPositivoFilial.calcula(movimentacoes);
		filialMaiorCrescimento = indCrescimentoFilialPositivo.calcula(movimentacoes);
		filialMaiorQueda = indCrescimentoFilialNegativo.calcula(movimentacoes);
		mesMaiorVenda = indCrescimentoMesPositivo.calcula(movimentacoes);
	}
	
	public void imprimirConsole(){
		System.out.println("\n ==RESULTADOS== \n");
		System.out.println("Filial que mais vendeu: " + filialMaiorVenda);
		System.out.println("Filial com maior crescimento: " + filialMaiorCrescimento);
		System.out.println("Filial com maior queda: " + filialMaiorQueda);
		System.out.println("Mes que a empresa mais vendeu: " + mesMaiorVenda);
	}

	public String getMesMaiorVenda() {
		return mesMaiorVenda;
	}

	public String getFilialMaiorCrescimento() {
		return filialMaiorCrescimento;
	}

	public String getFilialMaiorQueda() {
		return filialMaiorQueda;
	}

	public String getFilialMaiorVenda() {
		return filialMaiorVenda;
	}
	
	public String getResultadoHtml(){
		StringBuilder sb = new StringBuilder();
		sb.append("Filial que mais vendeu: ")
		.append(filialMaiorVenda)
		.append("<br>")
		.append("Filial com maior crescimento: ")
		.append(filialMaiorCrescimento)
		.append("<br>")
		.append("Filial com maior queda: ")
		.append(filialMaiorQueda)
		.append("<br>")
		.append("Mes que a empresa mais vendeu: ")
		.append(mesMaiorVenda)
		.append("<br>");

		return sb.toString();
	}
}
