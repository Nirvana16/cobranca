package com.algaworks.cobranca.model;

/**
 * Este Enum sera exibido na tela de cadastros de titulos, onde será inserido o 
 * status dele. Para ficar mais bonito foi realizado uma descrição para os enumeradores
 * Criado, então, um método construtor e colocando o ("umStringAqui") na frente de cada
 * enum.
 */

/**
 * Tambem foi criado um metodo GET para buscar essa descrição. Não necessitaremos de nenhum
 * outro metodo por ora.
 * @author boris
 *
 */

public enum StatusTitulo {
	
	
	PENDENTE("Pendente"), RECEBIDO("Recebido");
	
	private String descricao;
	
	
	StatusTitulo (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
