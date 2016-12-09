package br.com.lacosflores.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lacosflores.dao.FloriculturaDao;
import br.com.lacosflores.dao.PedidoDao;
import br.com.lacosflores.models.Floricultura;
import br.com.lacosflores.models.Item;
import br.com.lacosflores.models.Pedido;

//FAZER LIST DE PEDIDOS
//falta alguns métodos(excluir etc)
@RestController
@CrossOrigin
public class PedidoController {

	@Autowired
	private PedidoDao pedidoDao;
	@Autowired
	private FloriculturaDao floriculturaDao;

	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pedido> salvar(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
		try {
			if (pedido.getItens() != null) {
				for (Item itens : pedido.getItens()) {
					itens.setPedido(pedido);
				}
			}
			pedidoDao.salvar(pedido);
			return ResponseEntity.ok(pedido);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "{id}/pedido", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pedido> inserir(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
		try {
			if (pedido.getItens() != null) {
				for (Item itens : pedido.getItens()) {
					itens.setPedido(pedido);
				}
			}

			Floricultura floricultura = floriculturaDao.consultar(id);
			pedido.setFloricultura(floricultura);
			pedidoDao.inserir(pedido);
			return ResponseEntity.created(new URI("/pedido" + pedido.getId())).body(pedido);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id") long id) {
		pedidoDao.remover(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/pedido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Pedido> listar() {
		return pedidoDao.listar();
	}

	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Pedido consultar(@PathVariable("id") long id) {
		return pedidoDao.consultar(id);
	}

	/*
	 * public double calcularValor(long idPedido, long idItem){ Pedido pedido =
	 * pedidoDao.consultar(idPedido); Item item = itemDao.consultar(idItem);
	 * return pedido.getItens().size() * item.getValorUnitario();
	 * 
	 * }
	 */
	
	@RequestMapping(value = "{id}/pedido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Pedido> listar_floricultura(@PathVariable("id") Long id) {
		Floricultura floricultura = floriculturaDao.consultar(id);
		return floricultura.getPedidos();
		 
	}	

	public void definirEntregue(long idPedido) {
		Pedido pedido = pedidoDao.consultar(idPedido);
		pedido.setStatus("Pedido Realizado");
	}
}
