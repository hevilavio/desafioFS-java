package br.com.fastsolutions.desafio.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

public class LeitorTXTTest {
 
	
	private final String caminhoTxt = "file:///D:/dev/res/movimentacoes.txt";
	private final String caminhoTxtInvalido = "file:///D:/dev/res/movimentacoes_inv.txt";
	private LeitorTXT reader;
	
	@Before
	public void init(){
		reader = new LeitorTXT();
	}
	
	//TODO: Remover
	@Test
	public void NumberFormatTest() throws ParseException{
		NumberFormat format = NumberFormat.getInstance();
	    Number number = format.parse("1.234,55");
	    double d = number.doubleValue();
	    System.out.println(d);
	}
	
	@Test
	public void possoLerUmTxt() throws Exception {
		List<Movimentacao> movimentacoes = reader.getList(Paths.get(new URI(caminhoTxt)));
		assertTrue(movimentacoes != null);
		assertEquals(21, movimentacoes.size());
	}

	@Test
	public void possoLetTodosAtributos() throws Exception {
		List<Movimentacao> movimentacoes = reader.getList(Paths.get(new URI(caminhoTxt)));

		assertEquals("São Paulo", movimentacoes.get(0).getFilial());
		assertEquals("Janeiro", movimentacoes.get(0).getMes());
		assertEquals(14558.0 , movimentacoes.get(0).getValor(), 0.00001);
	}
	
	@Test(expected=Exception.class)
	public void naoPodeLerTxtInvalido() throws Exception {
		reader.getList(Paths.get(new URI(caminhoTxtInvalido)));
	}
	
	
	public void possoLerDeStream() throws Exception{
		FileInputStream stream = new FileInputStream(new File(caminhoTxt));
		List<Movimentacao> movimentacoes = reader.getList(stream);
		
		assertEquals("São Paulo", movimentacoes.get(0).getFilial());
		assertEquals("Janeiro", movimentacoes.get(0).getMes());
		assertEquals(14558.0 , movimentacoes.get(0).getValor(), 0.00001);
		
		stream.close();
	}
	
}
