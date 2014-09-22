package br.com.fastsolutions.desafio.console;

public class Main {

	/**
	 * Apenas um target para possibilidade de exportar a aplicação
	 * como Console Application.
	 * 
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
