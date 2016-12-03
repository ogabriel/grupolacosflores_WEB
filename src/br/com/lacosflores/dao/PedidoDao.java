package br.com.lacosflores.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Noticias;
import br.com.lacosflores.models.Pedido;


@Repository
public class PedidoDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void inserir(Pedido pedido) {
		entityManager.persist(pedido);

	}
	
	@Transactional
	public void salvar(Pedido pedido) {
		entityManager.merge(pedido);

	}


	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));

	}

	public List<Pedido> listar() {
		TypedQuery<Pedido> query = entityManager.createQuery("select pedido from Pedido pedido ", Pedido.class);
		return query.getResultList();
	}

	public Pedido consultar(Long id) {
		return entityManager.find(Pedido.class, id);

	}

	@Transactional
	public void definirEntregue(Long idPedido, String valor) {
		Pedido pedido = entityManager.find(Pedido.class, idPedido);
		pedido.setStatus(valor);
		entityManager.merge(pedido);

	}
	
	
	

	// FIXME: n sei exatamente porque, mas talvez tenha de colocar um método de
	// calculos aqui

}
