package br.com.example.spring.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 6931752339818498534L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
