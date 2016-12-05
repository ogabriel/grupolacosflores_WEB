package br.com.lacosflores.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lacosflores.models.Dispositivo;
//import br.com.lacosflores.models.Floricultura;
//import br.com.lacosflores.models.Usuario;

@Repository
public class DispositivoDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvar(Dispositivo dispositivo) {
		entityManager.merge(dispositivo);
	}

	@Transactional
	public void inserir(Dispositivo dispositivo) {
		entityManager.persist(dispositivo);
	}

	@Transactional
	public void remover(Long id) {
		entityManager.remove(this.consultar(id));
	}

	public List<Dispositivo> listar() {
		TypedQuery<Dispositivo> query = entityManager.createQuery("select dispositivo from Dispositivo dispositivo ",
				Dispositivo.class);
		return query.getResultList();
	}

	public Dispositivo consultar(Long id) {
		return entityManager.find(Dispositivo.class, id);
	}

	public List<Dispositivo> consultar_imei(String imei) {
		TypedQuery<Dispositivo> query = entityManager.createQuery(
				"select dispositivo from Dispositivo dispositivo where dispositivo.imei = :imei",
				Dispositivo.class);
		query.setParameter("imei", imei);
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public Dispositivo consultar_filial(Long id) {
		return null;
	}
	
	// TODO: falta as paradas de rastreamento
}
