package reboucas.nathalia.vacinajaapi.models.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reboucas.nathalia.vacinajaapi.models.entities.Usuario;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class UsuarioDTO {

	
	private String id;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@Email(message = "Email inválido")
	@NotBlank(message = "Email é obrigatório")
	private String email;
	
	@NotBlank(message = "CPF é obrigatório")
	@Length(min = 14, max =14, message = "CPF inválido")
	private String cpf;
	
	@NotNull(message = "Data de nascimento é obrigatória")
	@Past(message = "Data de nascimento inválida")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "Município é obrigatório")
	private String municipio;
	
	@JsonIgnore
	private LocalDate dataCadastro;
	
	private boolean processado;
		
	public Usuario toEntity() {		
		return Usuario.builder()
			.id(this.id)
			.nome(this.nome)
			.email(this.email)
			.cpf(this.cpf)
			.dataNascimento(this.dataNascimento)
			.municipio(this.municipio)
			.dataCadastro(LocalDate.now())
			.processado(false)
			.build();

	}
	
}
