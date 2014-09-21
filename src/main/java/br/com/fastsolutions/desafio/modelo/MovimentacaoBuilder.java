package br.com.fastsolutions.desafio.modelo;

/**
 * Classe para construção de objetos do tipo Movimentacao.
 * 
 * @author Hevilávio
 * 
 * */
public class MovimentacaoBuilder {

	private String nomeEmpresa;
	private String nomeMes;
	private double valor;
	 
	public MovimentacaoBuilder comFilial(String nomeEmpresa){
		this.nomeEmpresa = nomeEmpresa;
		return this;
	}
	public MovimentacaoBuilder comMes(String mes){
		this.nomeMes = mes;
		return this;
	}
	public MovimentacaoBuilder comValor(double valor){
		this.valor = valor;
		return this;
	}
	
	/**
	 * Gera um objeto do tipo Movimentacao referente aos parametros setados.
	 *  
	 * @throws IllegalStateException Caso todos os parametros 
	 * 		obrigatórios não tenham sido setados.
	 * 
	 * @throws IllegalArgumentException Caso o valor de movimentação seja negativo.
	 * 
	 * */
	public Movimentacao gerarMovimentacao() throws IllegalStateException, IllegalArgumentException{
		if(nomeEmpresa == null || nomeEmpresa.isEmpty()
			|| nomeMes == null || nomeMes.isEmpty()
			|| valor == 0.0){
			
			throw new IllegalStateException("Parametros nao preenchidos.");
		}
		if(valor < 0){
			throw new IllegalArgumentException("Valor negativo para a Movimentacao nao permitido");
		}
		
		return new Movimentacao(nomeEmpresa, nomeMes, valor);
	}
}
