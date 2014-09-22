package br.com.fastsolutions.desafio.reader;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * Interface a ser implementada por todos
 * as classes que realizam algum tipo de leitura
 * e retornam uma lista de objetos.
 * 
 * @author Hevilávio
 * 
 * */
public interface Leitor<T> {
	public List<T> getList(Path path) throws Exception;
	public List<T> getList(InputStream stream) throws Exception;
}
