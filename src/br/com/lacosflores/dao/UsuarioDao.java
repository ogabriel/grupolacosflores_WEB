package br.com.lacosflores.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Usuario;


@Repository
public class UsuarioDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvar(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Transactional
	public void inserir(Usuario usuario) {
		entityManager.persist(usuario);
	}
	
	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
	}

	public List<Usuario> listar() {
		TypedQuery<Usuario> query = entityManager.createQuery("select usuario from Usuario usuario", Usuario.class);
		return query.getResultList();
	}

	public Usuario consultar(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	public Usuario logar(Usuario usuario) {
		TypedQuery<Usuario> query = entityManager.createQuery(
				"selecte usuario form Usuario usuario where usuario.email = :email and usuario.senha = :senha",
				Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}


}
