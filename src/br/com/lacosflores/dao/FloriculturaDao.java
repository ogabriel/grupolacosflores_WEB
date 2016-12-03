package br.com.lacosflores.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Floricultura;
import br.com.lacosflores.models.Noticias;

@Repository
public class FloriculturaDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void inserir(Floricultura item) {
		entityManager.persist(item);
	}


	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
	}
	
	public List<Floricultura> listar() {
		TypedQuery<Floricultura> query = entityManager.createQuery("select floricultura from Floricultura floricultura", Floricultura.class);
		return query.getResultList();
	}

	public Floricultura consultar(Long id) {
		return entityManager.find(Floricultura.class, id);
	}
	
	public List<Floricultura> listar_contains(String nomeFantasia) {
		TypedQuery<Floricultura> query = entityManager.createQuery("select floricultura from Floricultura floricultura WHERE floricultura.nomeFantasia LIKE '"+nomeFantasia+"%'", Floricultura.class);
		return query.getResultList();
	}
	
	public Floricultura consultar_cep(String cep) {
		return entityManager.find(Floricultura.class, cep);
	}
	
}
