package reboucas.nathalia.vacinajaapi.models.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reboucas.nathalia.vacinajaapi.models.dto.UsuarioDTO;

@Entity
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "MUNICIPIO_COD_EXTERNO")
	private Long municipioCodExterno;
	
	@Column(name = "MUNICIPIO")
	private String municipio;
	
	@Column(name = "DATA_CADASTRO")
	private LocalDate dataCadastro;
	
	public UsuarioDTO toDTO() {
		return UsuarioDTO.builder()
			.id(this.id)
			.nome(this.nome)
			.email(this.email)
			.cpf(this.cpf)
			.dataNascimento(this.dataNascimento)
			.municipioCodExterno(this.municipioCodExterno)
			.municipio(this.municipio)
			.dataCadastro(this.dataCadastro)
			.build();
	}

}
