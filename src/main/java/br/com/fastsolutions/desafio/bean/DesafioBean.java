package br.com.fastsolutions.desafio.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean 
public class DesafioBean {
	
	private UploadedFile arquivo;
	private String filialMaiorVenda;

	
	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}


	public String getFilialMaiorVenda() {
		return filialMaiorVenda;
	}
	
	public void calcular(){
	
		
		System.out.println("chamado: \n"); 
		System.out.println(arquivo.getFileName());
		filialMaiorVenda = "Foo2";
	}

	
	 
}
