/**
 * Pegamos o ID confirmacaoExclusaoModal e dizemos: Toda vez que ".on"
 * show.bs.modal aparecer eu quero executar uma função que recebe um evento 
 * como parametro 
 * 
 * A ideia deste script é adicionar o ID n ofinal do action lá do formulario 
 * assim que o usuario clicar no botão ele vai ver qual é o ID capturar e 
 * adicionar em tempo real no campo: [...] <Form action="/titulos/ID" 
 * onde o ID é o numero que ele vai buscar e insterir.
 * 
 */
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	//primeiro vamos pegar o botão que disparou o evento.
	var button = $(event.relatedTarget); 	
	//esse camarada recebe o código la do HTML de onde ele tiver o data-
	var codigoTitulo = button.data('codigo')
	//Para que possamos exibir uma mensagem com o que esta sendo apagado vamos precisar da descricao
	var descricao = button.data('descricao');
	//transformamos a variavel em uma para Jquery.
	var modal = $(this);
	//aqui vamos pegar o modal que esta no form
	var form = modal.find('form');
	//aqui pegamos a String "/titulos" que esta lá no action ... <form action="/titulos" 
	var action = form.attr('action');
	/*
	 * Podemos acabar encontrando bugs por conta da barra no final da String acima
	 * dessa forma podemos usar do if a baixo para driblarmos essa ocorrencia
	 * sendo assim, caso a string não tenha barra ele adiciona, do contrario ele segue a vida
	 */
	//Se essa string acima não terminar com barra.... Ok confesso isso é gambiarra, finja q nao percebeu.
	if (!action.endsWith('/')) {
		action += '/';
	}
	//Aqui nós dizemos pro Js que é para ele ir no campo action do form e adicionar o ID ao final da Sting
	form.attr('action', action + codigoTitulo);
	
	/**
	 * Aqui nós vamos substituir a frase padrão contida la no span do modal
	 * modal.find encontra a classe que queremos no caso estamos buscando
	 * o modal-body e, no caso, o span que esta dentro dessa classe.
	 * 
	 * encontrado, nós podemos substituir o conteudo de dentro do span por um 
	 * html personalizado concatenado com a descricao que pegamos do button
	 */
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricao + '</strong>?');
});