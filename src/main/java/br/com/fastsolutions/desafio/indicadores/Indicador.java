package br.com.fastsolutions.desafio.indicadores;

import java.util.List;
 

/**
 * Interface a ser implementada por todas
 * as classes que calculam alguma forma de
 * Indicador.<br/>
 * 
 * <b>T:</b> Tipo de dado da lista<br/>
 * <b>R:</b> Tipo de retorno
 * */
public interface Indicador<T, R> {
	public R calcula(List<T> list);
}
