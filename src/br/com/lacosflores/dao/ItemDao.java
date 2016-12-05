package br.com.lacosflores.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Item;
//import br.com.lacosflores.models.Noticias;

@Repository
public class ItemDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void inserir(Item item) {
		entityManager.persist(item);
	}

	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
	}

	public List<Item> listar() {
		TypedQuery<Item> query = entityManager.createQuery("select item from Item item", Item.class);
		return query.getResultList();
	}

	public Item consultar(Long id) {
		return entityManager.find(Item.class, id);
	}

	public List<Item> listar_contains(String nome) {
		TypedQuery<Item> query = entityManager
				.createQuery("select item from Item item WHERE item.nome LIKE '" + nome + "%'", Item.class);
		return query.getResultList();
	}

}
