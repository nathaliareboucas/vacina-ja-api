package reboucas.nathalia.vacinajaapi.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reboucas.nathalia.vacinajaapi.models.dto.DashboardDTO;
import reboucas.nathalia.vacinajaapi.models.entities.Usuario;
import reboucas.nathalia.vacinajaapi.repositories.UsuarioRepository;
import reboucas.nathalia.vacinajaapi.services.exceptions.CpfCadastradoException;
import reboucas.nathalia.vacinajaapi.services.exceptions.MenorDeIdadeException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = repository.findByCpf(usuario.getCpf());
		
		if (usuario.menorIdade()) {
			throw new MenorDeIdadeException("Apenas pessoas maiores de 18 anos podem ser cadastradas");
		}
			
		if (Objects.nonNull(usuarioExistente)) {
			throw new CpfCadastradoException("CPF já cadastrado no sistema");
		}		
		
		return repository.save(usuario);
	}

	// TODO: Refazer consulta
	public DashboardDTO getDadosDashboardPor(String usuarioId) {			
		Usuario usuarioExistente = repository.findById(usuarioId);
		
		if (Objects.isNull(usuarioExistente)) {
			return DashboardDTO.builder().usuarioCadastrado(false).build();
		}
		
		final Integer totalCadastrado = (int) repository.count();
		final Integer totalVacinado = repository.getTotalByProcessado(true);
		final Integer totalNaoVacinado = repository.getTotalByProcessado(false);
		
		final DashboardDTO dashBoardDTO = DashboardDTO.builder()
				.usuarioId(usuarioId)
				.totalCadastrado(totalCadastrado)
				.totalVacinado(totalVacinado)
				.totalNaoVacinado(totalNaoVacinado)
				.usuarioCadastrado(true)
				.build();
		
		final List<String> idsUsuariosParaVacinar = repository.findByProcessado(false).stream()
			.map(Usuario::getId)
			.collect(Collectors.toList());
		
		dashBoardDTO.calcularPosicaoUsuarioNaFila(idsUsuariosParaVacinar);
	
		return dashBoardDTO;
	}
	
	public List<Usuario> getUsuariosNotificar(final int quantidade) {
		return repository.getByProcessadoWithLimit(false, quantidade);
	}
	
	public void atualizarUsuariosProcessados(final List<Usuario> usuarios) {
		if (Objects.nonNull(usuarios) && !usuarios.isEmpty()) {
			usuarios.forEach(usuario -> usuario.setProcessado(true));
			repository.saveAll(usuarios);
		}
	}

}
