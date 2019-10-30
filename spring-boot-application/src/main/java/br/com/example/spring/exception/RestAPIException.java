package br.com.example.spring.exception;

public class RestAPIException extends RuntimeException {

	private static final long serialVersionUID = -5147835170268452551L;

	public RestAPIException(String mensagem) {
		super(mensagem);
	}
}
