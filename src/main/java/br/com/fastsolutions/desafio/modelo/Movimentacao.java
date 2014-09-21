package br.com.fastsolutions.desafio.modelo;

import java.util.LinkedList;
import java.util.List;

public class Movimentacao {

	private String filial;
	private String mes;
	private double valor;
	
	public Movimentacao(String filial, String mes, double valor) {
		this.filial = filial;
		this.mes = mes;
		this.valor = valor;
	}
	
	public String getFilial() {
		return filial;
	}
	public String getMes() {
		return mes;
	}
	public double getValor() {
		return valor;
	}
	
	public List<Movimentacao> toList(){
		List<Movimentacao> list = new LinkedList<>();
		list.add(this);
		return list;
	}
	
}
