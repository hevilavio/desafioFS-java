package br.com.fastsolutions.desafio.indicadores;

import java.util.List;
import java.util.Map;

/**
 * Interface a ser implementada por todas
 * as classes que desejam realizar algum tipo de agrupamento.<br/>
 * 
 * <b>T:</b> Tipo de dado da Lista.<br/>
 * <b>R:</b> Tipo de retorno (obrigatoriamente algo derivado de Map)
 * 
 * @author Hevilávio
 * 
 * */
public interface Agrupador<T, R extends Map<?, ?>>{
	public R agrupar(List<T> list);
}
