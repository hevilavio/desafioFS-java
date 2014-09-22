package br.com.fastsolutions.desafio.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	 * No arquivo *.txt: Cada Movimentacao deve vir separada por quebra de linha
	 * ('\n') Cada atributo de Movimentacao deve vir sepadado por tabulação
	 * ('\t')
	 * @throws IOException 
	 * @throws ParseException 
	 * 
	 * */
	@Override
	public List<Movimentacao> getList(Path path) throws Exception{
		List<String> linhas = Files.readAllLines(path, Charset.defaultCharset());
		return criarLista(linhas);
		
	}

	/**
	 * Lê um arquivo InputStream e retorna uma lista de objetos do tipo Movimentacao.<br/>
	 * 
	 * O InputStream será transformado em String.<br/>
	 * Cada Movimentacao deve vir separada por quebra de linha('\n') <br/>
	 * Cada atributo de Movimentacao deve vir sepadado por tabulação('\t')<br/>
	 * @throws IOException 
	 * @throws ParseException 
	 * 
	 * */
	@Override
	public List<Movimentacao> getList(InputStream stream) throws Exception {
		return criarLista(lerLinhasStream(stream));
	}

	private List<Movimentacao> criarLista(List<String> linhas) throws ParseException{
		List<Movimentacao> movimentacoes = new LinkedList<>(); 
		String[] aux;
		double valor;
		
		linhas.remove(0); // removemos o cabeçalho do arquivo.
		for (String line : linhas) {
			aux = line.split("\t");

			valor = nFormat.parse(aux[2]).doubleValue();
			movimentacoes.add(builder.comFilial(aux[0]).comMes(aux[1])
					.comValor(valor).gerarMovimentacao());
		}
		return movimentacoes;
	}
	
	private List<String> lerLinhasStream(InputStream is) throws IOException {
		BufferedReader br = null; 
		List<String> linhas = new LinkedList<>();
		String linha;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((linha = br.readLine()) != null) {
				linhas.add(linha);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
		}

		return linhas;
	}
}
