package reboucas.nathalia.vacinajaapi.repositories;

import java.util.List;

import reboucas.nathalia.vacinajaapi.models.entities.Usuario;

public interface UsuarioRepositoryQuery {
	
	Integer getTotalByProcessado(boolean processado);
	List<Usuario> getByProcessadoWithLimit(boolean processado, int limit);
	
}
