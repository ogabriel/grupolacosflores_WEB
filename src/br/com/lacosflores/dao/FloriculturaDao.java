package br.com.lacosflores.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Floricultura;
//import br.com.lacosflores.models.Usuario;

@Repository
public class FloriculturaDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvar(Floricultura floricultura) {
		entityManager.merge(floricultura);
	}
	
	@Transactional
	public void inserir(Floricultura floricultura) {
		entityManager.persist(floricultura);
	}

	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
	}

	public List<Floricultura> listar() {
		TypedQuery<Floricultura> query = entityManager.createQuery("select floricultura from Floricultura floricultura",
				Floricultura.class);
		return query.getResultList();
	}

	public Floricultura consultar(Long id) {
		return entityManager.find(Floricultura.class, id);
	}

	
	public List<Floricultura> procurar_cep(String cep) {
		TypedQuery<Floricultura> query = entityManager.createQuery(
				"select floricultura from Floricultura floricultura where floricultura.cep = :cep",
				Floricultura.class);
		query.setParameter("cep", cep);
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}
	
	
	public List<Floricultura> listar_contains(String nomeFantasia) {
		TypedQuery<Floricultura> query = entityManager.createQuery("select floricultura from Floricultura floricultura WHERE floricultura.nomeFantasia LIKE '%"+ nomeFantasia + "%'", Floricultura.class);
		return query.getResultList();
	}
	
	

}
