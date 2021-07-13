package reboucas.nathalia.vacinajaapi.repositories.infra;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
