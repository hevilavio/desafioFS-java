package br.com.fastsolutions.desafio.reader;

import java.nio.file.Path;
import java.util.List;

/**
 * Interface a ser implementada por todos
 * as classes que realizam leitura em disco 
 * e retornam uma lista de objetos.
 * 
 * @author Hevilávio
 * 
 * */
public interface Leitor<T> {
	public List<T> getList(Path path) throws Exception;
}
