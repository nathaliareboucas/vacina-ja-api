package reboucas.nathalia.vacinajaapi.models;

import lombok.Getter;

@Getter
public class Erro {
	
	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
	private Long timestamp;

	public Erro(String mensagemUsuario, String mensagemDesenvolvedor, Long timestamp) {
		super();
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		this.timestamp = timestamp;
	}

}
