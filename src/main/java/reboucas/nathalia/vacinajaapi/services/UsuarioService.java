package reboucas.nathalia.vacinajaapi.services;

import java.time.LocalDate;
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
		usuario.setDataCadastro(LocalDate.now());
		usuario.setProcessado(false);
		Usuario usuarioExistente = repository.findByCpf(usuario.getCpf());
		
		if (usuario.menorIdade()) {
			throw new MenorDeIdadeException("Apenas pessoas maiores de 18 anos podem ser cadastradas");
		}
			
		if (Objects.nonNull(usuarioExistente)) {
			throw new CpfCadastradoException("CPF já cadastrado no sistema");
		}		
		
		return repository.save(usuario);
	}

	public DashboardDTO getDadosDashboardPor(Long usuarioId) {
		final Integer totalCadastrado = (int) repository.count();
		final Integer totalVacinado = repository.getTotalByProcessado(true);
		final Integer totalNaoVacinado = repository.getTotalByProcessado(false);
		
		final DashboardDTO dashBoardDTO = DashboardDTO.builder()
				.usuarioId(usuarioId)
				.totalCadastrado(totalCadastrado)
				.totalVacinado(totalVacinado)
				.totalNaoVacinado(totalNaoVacinado)
				.build();
		
		final List<Long> idsUsuariosParaVacinar = repository.findByProcessado(false).stream()
			.map(Usuario::getId)
			.collect(Collectors.toList());
		
		dashBoardDTO.calcularPosicaoUsuarioNaFila(idsUsuariosParaVacinar);
	
		return dashBoardDTO;
	}

}
