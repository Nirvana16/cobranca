package com.algaworks.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.algaworks.cobranca.model.Titulo;

/**
 * Toda classe Controler no Spring precisa ter a notação @Controller, assim como as demais 
 * nos outros pacotes. 
 * @author boris
 *
 */

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	/**
	 * O spring MVC tem uma habilidade de converter os dados do Input criar o objeto e entregar
	 * no Controle o objeto totalmente preparado!
	 * 
	 * para isso é preciso que os nomes dos InputText lá no HTML sejam os mesmos dos atributos
	 * existentes na Entity. Exemplo:
	 * 
	 * Na Template CadastroTitulo.html temos:
	 * <input type="text" class="form-control" id="descricao "name="descricao" /> 
	 * Veja que o nome é descricao, se voce for na classe Titulo, teremos: 
	 * private String descricao;
	 * 
	 * O nome de ambos precisam ser exatamente iguais e haver o endereço do controller
	 * Para fazermos isso  iremos usar o método a seguir
	 * 
	 * Neste método ao invés de enviarmos cada atributo presente na entidade, podemos
	 * passar diretamente o OBJETO, para que isso ocorra é só mapear com o local
	 * para onde devem ser enviados os dados com o metodo e o Verbo HTML
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		//Salvar no bd
		
		System.out.println(">>>" +titulo.getDescricao());
		
		return "CadastroTitulo";
	}

}
