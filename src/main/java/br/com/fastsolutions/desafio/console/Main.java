package br.com.fastsolutions.desafio.console;

public class Main {

	/**
	 * @param args Não espera nenhum parâmetro
	 */
	public static void main(String[] args) {
		init();
		new AnalisadorMovimentacoes().iniciar();
	}

	private static void init() {
		System.out.println("Desafio FAST SOLUTIONS");
		System.out.println("@hevilavio\n");
	}
}
