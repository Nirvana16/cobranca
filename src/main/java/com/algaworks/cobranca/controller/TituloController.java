package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	 * como estamos repetindo o tempo todo CadastroTitulo, 
	 * criamos uma varaivel final com o valor da string, assim
	 * facilita a vida.
	 */
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	
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
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
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
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView();
		if (errors.hasErrors()) {
			/*
			 * Retorna o nome da pagina HTML
			 */
			return CADASTRO_VIEW;
		}		
		
		titulos.save(titulo);			
		mv.addObject("mensagem", "Titulo Salvo com Sucesso");
		
		/*
		 * mv.addObject("todosStatusTitulo", StatusTitulo.values());
		 * caso não houvesse o método public List<StatusTitulo> todosStatusTitulo(){
		 */	
		
		/**
		 * return new ModelAndView("redirect:/titulos/novo")
		 * Com o o uso do th:field="{*algumaCoisa}" toda vez que o titulo 
		 * fosse salvo com sucesso os campos ainda iriam permacer com os dados já
		 * inseridos, para limpar podemos usar um redirecionamento onde a
		 * view eh redirecionada para pagina /titulos/novo.
		 * 
		 * Só que deixando apenas assim o redirect joga o usuario de volta no metodo salvar, este metodo
		 * e a mensagem de "titulo salvo com sucesso" nao apareceria, pois ele volta voce 
		 * la no começo e ele não saberia o que fazer com as informaçẽos existentes
		 * para corrigir isso a Spring tem o RedirectAttributes attributes
		 * que é justamente os atributos redirecionaveis com isso nós passamos 
		 * attributes.addFlashAttribute("mensagem", "Titulo Salvo com Sucesso");
		 */
		
		/*
		 * O Método foi alterado para STRING para simplificarmos seu funcionamento
		 * toda a parte em azul no ultimo bloco de comentários nao é mais necessaria, mas
		 * mantive a mesma de forma informativa
		 */
		
		attributes.addFlashAttribute("mensagem", "Titulo Salvo com Sucesso");
		/*
		 * Retorna o redirecionamento para uma URL
		 */
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	/*
	 * @RequestMapping("{codigo}")
	 * Faz com que a requisição chegue com o código do item a ser editado
	 * como o codigo é variavel usamos o {codigo}.
	 * 
	 * dessa forma podemos dizer para o metodo que ele ira receber
	 * um codigo do tipo LONG sendo que é referente a um PathVariable
	 * ou seja uma variavel que indica um caminho, quase um redirecionamento
	 * @PathVariable Long codigo 
	 * 
	 * ja vou explicar o pq botei Titulo titulo
	 */
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		/*
		 * Esse método findOne recupera o titulo de um 
		 * determinado ID (codigo) que foi passado para ele
		 * 
		 * 	Titulo titulo = titulos.findOne(codigo);	
		 * Comentei esse metodo pois o spring é tao sinistro
		 * que se voce fizer Titulo titulo. ou seja 
		 * se voce passar o objeto ele ja sabe que o codigo 
		 * q ta vindo na PathVariabel é para ele buscar 
		 * o objeto referenciado a ele...
		 * voce so precisa dizer pra ele qual o nome
		 * do parametro a buscar ("codigo")
		 * 
		 */		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		/**
		 * Com o mv.addObject(titulo); nós vamos retornar 
		 * o titulo que foi recuperado no metodo findOne
		 * assim ao contrario do que ocorre no metodo novo()
		 * onde é retornado um novo Model and view
		 * aqui retornamos o objeto que referenciado a ID 
		 * a qual foi recebida
		 */
		mv.addObject(titulo);
		return mv;
	}
	
	/*
	 * a Lógica por trás do excluir é praticamente a mesma 
	 * do editar, voce faz o RequestMapping dizendo que o tipo 
	 * do Method é o DELETE e ele ja cai no metodo excluir
	 * só que voce precisa passar o código de quem será removido
	 * entao fazemos basicamente a mesma coisa do editar.
	 */
	
	/**
	 * nao precisamos converter diretamente o codigo para o objeto
	 * Titulo. Já da para excluirmos diretamente pelo código
	 * nao tem o pq de recupera um objeto se nao vamos usa-lo
	 */
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		titulos.delete(codigo);
		
		//para exibir a mensagem igual fizemos com o Salvar.
		attributes.addFlashAttribute("mensagem", "Titulo Excluido com Sucesso");
		return "redirect:/titulos";		
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
