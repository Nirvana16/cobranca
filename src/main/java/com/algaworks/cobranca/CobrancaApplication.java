package com.algaworks.cobranca;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class CobrancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobrancaApplication.class, args);
	}
	
	/*
	 * O Bean a seguir serve para garantir que Data vá no formato correto para 
	 * o banco de dados, com ele nos garantimos que o formato sempre será respeitado
	 * Com este metodo nos defimos que queremos usar as definições do Brasil
	 * isso resolve acentos, data, a virgula pra casa decimal etc...
	 * No nosso caso, do problema de data, também é necessario ir na Entidade 
	 * e definir o formato da data.
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));		
	}
	
}
