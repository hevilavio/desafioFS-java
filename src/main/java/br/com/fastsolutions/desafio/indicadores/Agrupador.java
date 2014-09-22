package br.com.fastsolutions.desafio.indicadores;

import java.util.List;
import java.util.Map;

import br.com.fastsolutions.desafio.modelo.Movimentacao;

/**
 * Interface a ser implementada por todas
 * as classes que desejam realizar algum tipo de agrupamento.<br/>
 * 
 * <b>T:</b> Tipo de dado da Lista.<br/>
 * <b>R:</b> Tipo de retorno (obrigatoriamente algo derivado de Map)
 * 
 * @author Hevil�vio
 * 
 * */
public interface Agrupador<T, R extends Map<?, ?>>{
	public R agrupa(List<T> list);
	public ChaveAgrupamento<T, String> getChave();
}


/**
 * Interface utilizada para reaproveitamento do 
 * algoritmo de agrupamento cuja �nica diferen�a �
 * o atributo utilizado.
 * */
interface ChaveAgrupamento <T, R>{
	public R getChave(T item);
}

/**
 * Realiza o agrupamento de movimenta��es baseado no m�s.
 * 
 * @author Hevil�vio
 * */
class ChaveAgrupamentoMes implements ChaveAgrupamento<Movimentacao, String>{
	@Override
	public String getChave(Movimentacao item) {
		return item.getMes();
	}
}

/**
 * Realiza o agrupamento de movimenta��es baseado na filial.
 * 
 * @author Hevil�vio
 * */
class ChaveAgrupamentoFilial implements ChaveAgrupamento<Movimentacao, String>{
	@Override
	public String getChave(Movimentacao item) {
		return item.getFilial();
	}
}

