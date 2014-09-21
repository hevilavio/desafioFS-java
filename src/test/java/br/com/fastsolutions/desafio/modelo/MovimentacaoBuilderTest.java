package br.com.fastsolutions.desafio.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MovimentacaoBuilderTest {

	private MovimentacaoBuilder builder;
	private final String filial = "Foo SA";
	private final String mes = "Janeiro";
	private final double valor = 100.0;
	
	
	@Before
	public void init(){
		builder = new MovimentacaoBuilder();
	}
	
	@Test
	public void possoCriarUmaMovimentacao() {
		
		Movimentacao movimentacao = builder.comFilial(filial)
				.comMes(mes).comValor(valor).gerarMovimentacao();
	
		assertEquals(filial, movimentacao.getFilial());
		assertEquals(mes, movimentacao.getMes());
		assertEquals(valor, movimentacao.getValor(), 0.00001);
	}
	
	@Test(expected=IllegalStateException.class)
	public void naoPodeGerarMovimentacaoSemFilial(){
		builder.comMes(mes).comValor(valor).gerarMovimentacao();
	}
	
	@Test(expected=IllegalStateException.class)
	public void naoPodeGerarMovimentacaoSemMes(){
		builder.comFilial(filial).comValor(valor).gerarMovimentacao();
	}
	
	@Test(expected=IllegalStateException.class)
	public void naoPodeGerarMovimentacaoSemValor(){
		builder.comFilial(filial).comMes(mes).gerarMovimentacao();
	}
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeGerarMovimentacaoComValorNegativo(){
		
		builder.comFilial(filial).comMes(mes).comValor(valor * -1).gerarMovimentacao();
	}

}
