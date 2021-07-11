package reboucas.nathalia.vacinajaapi.models.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import reboucas.nathalia.vacinajaapi.models.entities.Usuario;

public class UsuarioDTOTest {
	
	@Test
	public void deveConverterDTOEmEntidade() {		
		final UsuarioDTO usuarioDTO = UsuarioDTO.builder()
				.id(1L)
				.nome("Usuário Teste")
				.email("teste@email.com")
				.cpf("123.456.789-12")
				.dataNascimento(LocalDate.of(1990, 3, 21))
				.municipioCodExterno(1L)
				.municipio("Fortaleza")
				.dataCadastro(LocalDate.now())
				.build();
		
		final Usuario usuario = usuarioDTO.toEntity();
		
		assertAll("usuario",
			() -> assertNotNull(usuario),
			() -> assertNotNull(usuario.getId()),
			() -> assertEquals(1, usuario.getId().intValue()),
			() -> assertEquals("Usuário Teste", usuario.getNome()),
			() -> assertEquals("teste@email.com", usuario.getEmail()),
			() -> assertEquals("123.456.789-12", usuario.getCpf()),
			() -> assertNotNull(usuario.getDataNascimento()),
			() -> assertNotNull(usuario.getMunicipioCodExterno()),
			() -> assertEquals(1, usuario.getMunicipioCodExterno().intValue()),
			() -> assertEquals("Fortaleza", usuario.getMunicipio()),
			() -> assertNotNull(usuario.getDataCadastro()));
	}

}
