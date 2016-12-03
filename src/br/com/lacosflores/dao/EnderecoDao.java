package br.com.lacosflores.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Endereco;

@Repository
public class EnderecoDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvar(Endereco endereco) {
		entityManager.merge(endereco);
		
	}
	
	@Transactional
	public void inserir(Endereco endereco) {
		entityManager.persist(endereco);
		
	}

	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
		
	}

	public List<Endereco> listar() {
		TypedQuery<Endereco> query = entityManager.createQuery("select endereco from Endereco endereco ", Endereco.class);
		return query.getResultList();
	}
	
	public Endereco listar_cep(String cep) {
		return entityManager.find(Endereco.class, cep);
	}

	public Endereco consultar(Long id) {
		return entityManager.find(Endereco.class, id);
		 
	}
	
	
	
	
}
