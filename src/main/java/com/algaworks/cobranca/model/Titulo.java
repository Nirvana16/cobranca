package com.algaworks.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * Sempre que lidarmos com uma entidade no Spring, precisamos mapear a classe como @Entity
 * para essa classe foi criado os atributos, gerado os GETTERS & SETTERS e também o hashcode e
 * equals (caso tenha duvidas sobre, pesquisar sobre  Estrutura de Dados, Collections e comparaçao
 * entre dois objetos em java)
 * 
 * @author boris
 *
 */

@Entity
public class Titulo {
	
	
	/**
	 * Para que o JPA possa mapear o banco de dados é preciso criar o atributo de código, ou 
	 * id, em seguida mapear o @Id em cima do atributo o qual sera utilizado como chave
	 * primaria no banco de dados
	 */
	/**
	 * O @GeneratedValue(strategy = GenerationType.IDENTITY) é a definição de como sera
	 * feita a PK do atributo código, chamaos isso de Estratégia, no spring apens é 
	 * preciso mapear dessa forma e ele se encarregará de todo o resto.
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	private String descricao;
	
	/**
	 * @Temporal(TemporalType.DATE) é mais uma notação que facilita, e muito , a vida do
	 * Dev, pois quem já teve que efetuar uma persistencia de data, em java, sabe o quanto é
	 * trabalhoso. Utilizando esta notação, toda a configuracao é feita de moto automatizado
	 * e o TemporalType significa que só queremos a DATA, ignorando a hora e minutos.
	 */
	
	/*
	 * O @DateTimeFormat (pattern = "dd/MM/yyyy")
	 * Ver o comentario em CobrancaApplication primeiro. 
	 * Ele define o formato da data que queremos passar para o banco.
	 */
	
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	/*
	 * @NumberFormat(pattern = "#,##0.00")
	 * Mesma explicação do DateTime. 
	 */
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	/**
	 * @Enumerated(EnumType.STRING)
	 * Faz com que o campo na tabela do banco de dados o qual irá receber o Status do titulo
	 * seja, obrigatóriamente, uma String (PENDENTE OU RECEBIDO) Normalmente é feito com 
	 * um "ordinal" (0 ou 1) só que isso não deixa o código muito legivel, pois caso seja
	 * necessário olhar diretamente a consulta no banco o analista precisaria assimilar qual
	 * numero ordinal significa o que. 
	 */
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public StatusTitulo getStatus() {
		return status;
	}
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	

}
