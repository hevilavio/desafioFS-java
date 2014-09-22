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
	 

	private final String txtFilialMaiorVenda = "Filial que mais vendeu: %s";
	private final String txtFilialMaiorCrescimento = "Filial com maior crescimento: %s";
	private final String txtFilialMaiorQueda = "Filial com maior queda: %s";
	private final String txtMesMaiorVenda = "Mes que a empresa mais vendeu: %s";
	
	private String mesMaiorVenda;
	private String filialMaiorCrescimento;
	private String filialMaiorQueda;
	private String filialMaiorVenda;
	
	public PainelIndicadores() { 
		IndicadorMovimentacaoFactory indicadorFactory = new IndicadorMovimentacaoFactory();
		iniciarValorIndicadores(indicadorFactory);
	}

	private void iniciarValorIndicadores(IndicadorMovimentacaoFactory indicadorFactory) {
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
	
	public String getStringConsole(){
		return getString("\n");
	}
 
	private String getString(String separador) {
		StringBuilder sb = new StringBuilder()
		.append(separador)
		.append(String.format(txtFilialMaiorVenda, filialMaiorVenda))
		.append(separador)
		.append(String.format(txtFilialMaiorCrescimento, filialMaiorCrescimento))
		.append(separador)
		.append(String.format(txtFilialMaiorQueda, filialMaiorQueda))
		.append(separador)
		.append(String.format(txtMesMaiorVenda, mesMaiorVenda))
		.append(separador);
		
		return sb.toString();
	}

	public String getStringHtml(){
		return getString("<br/>");
	}
}
