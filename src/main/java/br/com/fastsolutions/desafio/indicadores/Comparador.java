package br.com.fastsolutions.desafio.indicadores;


/**
 * Interface a ser implementada por todas as
 * classes que desejam analisar se uma taxa é  
 * relevante em relação a outra taxa. <br/>
 * 
 * <b>A:</b> O valor da taxa atual<br/>
 * <b>N:</b>O valor da possível nova taxa
 * 
 * */
public interface Comparador<A, N> {
	public boolean relevante(A atual, N novo);
	public A valorInicial();
} 