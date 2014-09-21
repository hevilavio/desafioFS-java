package br.com.fastsolutions.desafio.console;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.com.fastsolutions.desafio.modelo.Movimentacao;
import br.com.fastsolutions.desafio.reader.Leitor;
import br.com.fastsolutions.desafio.reader.LeitorTXT;

public class AnalisadorMovimentacoes {
	private DataInputStream teclado;
	private Leitor<Movimentacao> leitor;

	public AnalisadorMovimentacoes() {
		this.teclado = new DataInputStream(System.in);
		this.leitor = new LeitorTXT();
	}

	public void iniciar() {
		boolean loop = true;
		System.out.println("Informe o caminho do arquivo:\n");

		try {
			while(loop){
				
				String caminho = teclado.readUTF();
				caminho = "file:///".concat(caminho); // ajuste do java.nio para Windows
				Path arquivo = Paths.get(new URI(caminho));
				
				if (Files.exists(arquivo, LinkOption.NOFOLLOW_LINKS)) {
					throw new IllegalArgumentException(
							"Caminho inválido.Caminho: " + caminho);
				}
				List<Movimentacao> movimentacoes = leitor.getList(arquivo);

				new PainelIndicadores().exibir(movimentacoes);

				System.out.println("Executar novamente?[S/N]");
				loop = teclado.readUTF().toLowerCase() == "s";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				teclado.close();
			} catch (IOException e) {
				System.err.println("Erro ao liberar teclado como entrada");
				e.printStackTrace();
			}
		}
	}
}
