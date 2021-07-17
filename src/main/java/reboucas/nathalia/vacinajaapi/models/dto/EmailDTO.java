package reboucas.nathalia.vacinajaapi.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {

	@NotNull
	@NotBlank(message = "Email é obrigatório")
	private String destinatario;
	
	@NotNull
	@NotBlank(message = "Assunto é obrigatório")
	private String assunto;
	
	@NotNull
	@NotBlank(message = "Mensagem é obrigatório")
	private String mensagem;
}
