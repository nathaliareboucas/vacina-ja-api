package reboucas.nathalia.vacinajaapi.models.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import reboucas.nathalia.vacinajaapi.models.dto.UsuarioDTO;

public class UsuarioTest {
	
	@Test
	public void deveConverterEntidadeEmDTO() {		
		final Usuario usuario = Usuario.builder()
				.id("f5456-f54a-bb")
				.nome("Usuário Teste")
				.email("teste@email.com")
				.cpf("123.456.789-12")
				.dataNascimento(LocalDate.of(1990, 3, 21))
				.municipio("Fortaleza")
				.dataCadastro(LocalDate.now())
				.processado(false)
				.build();
		
		final UsuarioDTO usuarioDTO = usuario.toDTO();
		
		assertAll("usuario",
			() -> assertNotNull(usuarioDTO),
			() -> assertNotNull(usuarioDTO.getId()),
			() -> assertEquals("f5456-f54a-bb", usuarioDTO.getId()),
			() -> assertEquals("Usuário Teste", usuarioDTO.getNome()),
			() -> assertEquals("123.456.789-12", usuarioDTO.getCpf()),
			() -> assertNotNull(usuarioDTO.getDataNascimento()),
			() -> assertEquals("Fortaleza", usuarioDTO.getMunicipio()),
			() -> assertFalse(usuarioDTO.isProcessado()),
			() -> assertNotNull(usuarioDTO.getDataCadastro()));
	}
	
	@Test
    public void deveCalcularIdade() {
		Usuario usuario = Usuario.builder()
				.dataNascimento(LocalDate.of(1985, 7, 11))
				.build();
		
		int idade = usuario.getIdade();
        
        assertEquals(36, idade);
    }
	
	@Test
	public void deveTerIdadeZero() {
		Usuario usuario = new Usuario();
		
		int idade = usuario.getIdade();
        
        assertEquals(0, idade);
	}
	
	@Test
	public void deveSerMaiorDeIdade() {
		Usuario usuario = Usuario.builder()
				.dataNascimento(LocalDate.of(1990, 03, 21))
				.build();
		
		boolean maiorIdade = !usuario.menorIdade();
		
		Assertions.assertTrue(maiorIdade);
	}
	
	@Test
	public void deveSerMenorDeIdade() {
		Usuario usuario = Usuario.builder()
				.dataNascimento(LocalDate.of(2021, 01, 21))
				.build();
		
		boolean menorIdade = usuario.menorIdade();
		
		Assertions.assertTrue(menorIdade);
	}

}
