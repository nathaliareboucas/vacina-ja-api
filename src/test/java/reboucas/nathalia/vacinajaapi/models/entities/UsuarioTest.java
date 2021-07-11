package reboucas.nathalia.vacinajaapi.models.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import reboucas.nathalia.vacinajaapi.models.dto.UsuarioDTO;

public class UsuarioTest {
	
	@Test
	public void deveConverterEntidadeEmDTO() {		
		final Usuario usuario = Usuario.builder()
				.id(1L)
				.nome("Usuário Teste")
				.email("teste@email.com")
				.cpf("123.456.789-12")
				.dataNascimento(LocalDate.of(1990, 3, 21))
				.municipioCodExterno(1L)
				.municipio("Fortaleza")
				.dataCadastro(LocalDate.now())
				.build();
		
		final UsuarioDTO usuarioDTO = usuario.toDTO();
		
		assertAll("usuario",
			() -> assertNotNull(usuarioDTO),
			() -> assertNotNull(usuarioDTO.getId()),
			() -> assertEquals(1, usuarioDTO.getId().intValue()),
			() -> assertEquals("Usuário Teste", usuarioDTO.getNome()),
			() -> assertEquals("123.456.789-12", usuarioDTO.getCpf()),
			() -> assertNotNull(usuarioDTO.getDataNascimento()),
			() -> assertNotNull(usuarioDTO.getMunicipioCodExterno()),
			() -> assertEquals(1, usuarioDTO.getMunicipioCodExterno().intValue()),
			() -> assertEquals("Fortaleza", usuarioDTO.getMunicipio()),
			() -> assertNotNull(usuarioDTO.getDataCadastro()));
	}

}
