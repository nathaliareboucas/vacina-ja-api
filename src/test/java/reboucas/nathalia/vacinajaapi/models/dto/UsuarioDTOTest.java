package reboucas.nathalia.vacinajaapi.models.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import reboucas.nathalia.vacinajaapi.models.entities.Usuario;

public class UsuarioDTOTest {
	
	@Test
	public void deveConverterDTOEmEntidade() {		
		final UsuarioDTO usuarioDTO = UsuarioDTO.builder()
				.id("f5456-f54a-bb")
				.nome("Usuário Teste")
				.email("teste@email.com")
				.cpf("123.456.789-12")
				.dataNascimento(LocalDate.of(1990, 3, 21))
				.municipio("Fortaleza")
				.dataCadastro(LocalDate.now())
				.processado(false)
				.build();
		
		final Usuario usuario = usuarioDTO.toEntity();
		
		assertAll("usuario",
			() -> assertNotNull(usuario),
			() -> assertNotNull(usuario.getId()),
			() -> assertEquals("f5456-f54a-bb", usuario.getId()),
			() -> assertEquals("Usuário Teste", usuario.getNome()),
			() -> assertEquals("teste@email.com", usuario.getEmail()),
			() -> assertEquals("123.456.789-12", usuario.getCpf()),
			() -> assertNotNull(usuario.getDataNascimento()),
			() -> assertEquals("Fortaleza", usuario.getMunicipio()),
			() -> assertFalse(usuario.isProcessado()),
			() -> assertNotNull(usuario.getDataCadastro()));
	}

}
