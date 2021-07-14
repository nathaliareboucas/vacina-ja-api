package reboucas.nathalia.vacinajaapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reboucas.nathalia.vacinajaapi.models.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{
	
	Usuario findByCpf(String cpf);
	List<Usuario> findByProcessado(boolean processado);
	Usuario findById(String usuarioId);

}
