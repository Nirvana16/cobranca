package com.algaworks.cobranca.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.cobranca.model.Titulo;

/**
 * Essa interface é o Repository, ela que será responsvel por guardar os dados no 
 * banco de dados. Essa interface extend uma classe ja existente do Spring data JPA
 * a JpaRepository. Nessa classe encontra-se todos métodos necessarios para gravar,
 * buscar, listar os dados referente a nossa persistencia em Banco de dados. 
 * Ao crirar essa interface e extendermos a classe supracitada precisaremos informar 
 * como paramentro qual é a Entidade (Titulo) a qual ela trata e qual o tipo do Id(long)
 * que a Pk ira assumir. 
 */

public interface Titulos extends JpaRepository<Titulo, Long>{

}
