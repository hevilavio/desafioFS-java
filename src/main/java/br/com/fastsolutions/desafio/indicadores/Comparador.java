package br.com.fastsolutions.desafio.indicadores;


/**
 * Interface a ser implementada por todas as
 * classes que desejam analisar se uma taxa �  
 * relevante em rela��o a outra taxa. <br/>
 * 
 * <b>A:</b> O valor da taxa atual<br/>
 * <b>N:</b>O valor da poss�vel nova taxa
 * 
 * */
public interface Comparador<A, N> {
	public boolean relevante(A atual, N novo);
	public A valorInicial();
} 