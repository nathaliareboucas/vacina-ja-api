package reboucas.nathalia.vacinajaapi.services;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Usuario usuarioExistente = repository.findByCpf(usuario.getCpf());
		
		if (usuario.menorIdade()) {
			throw new MenorDeIdadeException("Apenas pessoas maiores de 18 anos podem ser cadastradas");
		}
			
		if (Objects.nonNull(usuarioExistente)) {
			throw new CpfCadastradoException("CPF já cadastrado no sistema");
		}		

		return repository.save(usuario);
	}

}
