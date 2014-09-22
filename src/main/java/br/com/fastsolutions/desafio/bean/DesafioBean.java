package br.com.fastsolutions.desafio.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.fastsolutions.desafio.console.PainelIndicadores;
import br.com.fastsolutions.desafio.modelo.Movimentacao;
import br.com.fastsolutions.desafio.reader.LeitorTXT;

@ManagedBean
public class DesafioBean {

	public void uploadArquivo(FileUploadEvent arquivoUploadEvent) { 
		UploadedFile arquivoUpload = arquivoUploadEvent.getFile(); 
		FacesMessage resposta = new FacesMessage();

		PainelIndicadores painel = new PainelIndicadores();
		LeitorTXT leitor = new LeitorTXT();
		List<Movimentacao> movimentacoes;
		try {
			movimentacoes = leitor.getList(arquivoUpload.getInputstream());
			painel.calcular(movimentacoes);
			
			resposta.setSummary("RESULTADO:<br>");
			resposta.setDetail(painel.getStringHtml());
			
		} catch (Exception e) {
			resposta.setSummary("ERRO:");
			resposta.setDetail(e.getMessage());
			e.printStackTrace();
		}
			
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		facesContext.addMessage(null, resposta); 
	}
}
