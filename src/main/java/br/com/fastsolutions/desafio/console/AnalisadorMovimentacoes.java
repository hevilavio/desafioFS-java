package br.com.fastsolutions.desafio.console;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import br.com.fastsolutions.desafio.modelo.Movimentacao;
import br.com.fastsolutions.desafio.reader.Leitor;
import br.com.fastsolutions.desafio.reader.LeitorTXT;

public class AnalisadorMovimentacoes {
	private Scanner teclado;
	private Leitor<Movimentacao> leitor;

	public AnalisadorMovimentacoes() {
		this.teclado = new Scanner(System.in);
		this.leitor = new LeitorTXT();
	}

	public void iniciar() {
		boolean loop = true;
		System.out.println("Informe o caminho do arquivo:\n");

		try {
			while(loop){
				
				String caminho = teclado.nextLine();
				caminho = "file:///".concat(caminho).replace("\\", "/"); // ajuste do java.nio para Windows
				Path arquivo = Paths.get(new URI(caminho));
				
				 
				List<Movimentacao> movimentacoes = leitor.getList(arquivo);

				new PainelIndicadores().calcularEExibir(movimentacoes);

				System.out.println("\nExecutar novamente?[S/N]");
				loop = teclado.nextLine().toLowerCase() == "s";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			teclado.close();
		}
	}
}
