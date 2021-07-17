package reboucas.nathalia.vacinajaapi.repositories.infra;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import reboucas.nathalia.vacinajaapi.models.entities.Usuario;
import reboucas.nathalia.vacinajaapi.repositories.UsuarioRepositoryQuery;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery{

	@PersistenceContext 
	private EntityManager manager;

	@Override
	public Integer getTotalByProcessado(final boolean processado) {
		 return ((BigInteger) manager.createNativeQuery("SELECT COUNT(*) FROM USUARIO WHERE PROCESSADO = :processado")
				.setParameter("processado", processado)
				.getResultList().iterator().next()).intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> getByProcessadoWithLimit(boolean processado, int limit) {
		return manager.createNativeQuery("SELECT * FROM USUARIO usu "
				+ "WHERE usu.PROCESSADO = :processado "
				+ "ORDER BY usu.ID ASC LIMIT :limit", Usuario.class)
			.setParameter("processado", processado)
			.setParameter("limit", limit)
			.getResultList();
	}

}
