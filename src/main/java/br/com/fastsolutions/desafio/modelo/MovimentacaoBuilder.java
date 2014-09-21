package br.com.fastsolutions.desafio.modelo;

/**
 * Classe para constru��o de objetos do tipo Movimentacao.
 * 
 * @author Hevil�vio
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
	 * 		obrigat�rios n�o tenham sido setados.
	 * 
	 * @throws IllegalArgumentException Caso o valor de movimenta��o seja negativo.
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
