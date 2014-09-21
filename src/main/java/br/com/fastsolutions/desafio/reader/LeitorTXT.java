package br.com.fastsolutions.desafio.reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import br.com.fastsolutions.desafio.modelo.Movimentacao;
import br.com.fastsolutions.desafio.modelo.MovimentacaoBuilder;

/**
 * Classe para leitura de arquivos *.txt referentes à movimentações.
 * 
 * @author Hevilávio
 * 
 * */
public class LeitorTXT implements Leitor<Movimentacao> {
	private final MovimentacaoBuilder builder;
	private final NumberFormat nFormat;
	
	public LeitorTXT() {
		this.builder = new MovimentacaoBuilder();
		this.nFormat = NumberFormat.getInstance();
	}
	
	/**
	 * Lê um arquivo *.txt e retorna uma lista de objetos do tipo Movimentacao.
	 * 
	 * No arquivo *.txt: 
	 * 	Cada Movimentacao deve vir separada por quebra de linha ('\n')
	 * 	Cada atributo de Movimentacao deve vir sepadado por tabulação ('\t')
	 * 
	 * */
	@Override
	public List<Movimentacao> getList(Path path) throws IOException, ParseException {
		List<Movimentacao> movimentacoes = new LinkedList<>();
		List<String> linhas;
		String[] aux;
		double valor;
		
		linhas = Files.readAllLines(path, Charset.defaultCharset());
		linhas = linhas.subList(1, linhas.size());//Descartamos o cabeçalho do arquivo
		for (String line : linhas) {
			aux = line.split("\t");
			
			valor = nFormat.parse(aux[2]).doubleValue();
			movimentacoes.add(builder.comFilial(aux[0]).comMes(aux[1])
					.comValor(valor).gerarMovimentacao());
		}
		return movimentacoes;
	}
}
