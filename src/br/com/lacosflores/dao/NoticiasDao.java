package br.com.lacosflores.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Noticias;


@Repository
public class NoticiasDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvar(Noticias noticias) {
		entityManager.merge(noticias);
		
	}
	
	@Transactional
	public void inserir(Noticias noticias) {
		entityManager.persist(noticias);
		
	}

	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
		
	}

	public List<Noticias> listar() {
		TypedQuery<Noticias> query = entityManager.createQuery("select noticias from Noticias noticias ", Noticias.class);
		return query.getResultList();
	}
	
	
	public Noticias consultar(Long id) {
		return entityManager.find(Noticias.class, id);
		 
	}
	
	public List<Noticias> listar_contains(String titulo) {
		TypedQuery<Noticias> query = entityManager.createQuery("select noticias from Noticias noticias WHERE noticias.titulo LIKE '%"+titulo+"%'", Noticias.class);
		return query.getResultList();
	}
	
	
	
	
	
}
