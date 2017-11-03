package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

/**
 * Toda classe Controler no Spring precisa ter a notação @Controller, assim como as demais 
 * nos outros pacotes. 
 * @author boris
 *
 */

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	/**
	 * O @Autowired serve para indicar de onde esta vindo
	 * o objeto que será passado para dentro dos metodos do controle 
	 * referente ao private Titulos titulos; sem a notação receberiamos
	 * um erro pois o método não teria como identificar o objeto em questao
	 */	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		/**
		 * mv.addObject("TodosStatusTitulo", StatusTitulo.values());
		 * Retorna um array com todos os Enun's presentes na interface
		 * isso é utilizado para iterarmos sobre a coleção com o 
		 * Thymeleaf dentro da Viewer, implementando a combobox de modo
		 * dinamico, sem a necessidade de colocarmos item a item dentro
		 * da view
		 */
		/*
		 * Este metodo deveria ter:
		 * mv.addObject("todosStatusTitulo", StatusTitulo.values());
		 * completar o retorno para o th, mas como também precisariamos colocar
		 * ele novamente no outro método Post, que retorna para /titulos 
		 * estariamos repetindo código, para isso criamos o método
		 * public List<StatusTitulo> todosStatusTitulo(){ 
		 */
		
		/**
		 * Lá na view existe no form action="/titulos" th:object="${titulo}
		 * só que, de inicio, não há nenhum objeto titulo disponibilizado
		 * para view de inicio quando ele executa um verbo POST
		 * entao vamos, no nomento em que a pagina é criada disponbilizar esse
		 * objeto com o mv.addObject(new Titulo());
		 */
		mv.addObject(new Titulo());		
		return mv;
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
	/*
	 * @Validated
	 * Continuando a notação do bean validation precisamos, aqui, falar
	 * para o spring validar o metodo.
	 * 
	 * O spring ja possui o objeto Errors, para já pegar o erro que 
	 * possa ocorrer e permitir que o dev consiga tratar ele.
	 * dentro deste objeto existe o metodo "hasErrors" usamos ele 
	 * para verificar se o objeto errors tem erro. 
	 */
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		if (errors.hasErrors()) {
			return mv;
		}		
		
		titulos.save(titulo);	
		
		mv.addObject("mensagem", "Titulo Salvo com Sucesso");
		
		/*
		 * mv.addObject("todosStatusTitulo", StatusTitulo.values());
		 * caso não houvesse o método public List<StatusTitulo> todosStatusTitulo(){
		 */	
		
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	/*
	 * esse método retorna uma lista de arrays
	 * ele tem a mesma função caso usassemos 
	 * mv.addObject("todosStatusTitulo", StatusTitulo.values());
	 * dentro dos metodos anteriores.
	 */
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());		
	}

}
