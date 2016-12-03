package br.com.lacosflores.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lacosflores.dao.ItemDao;
import br.com.lacosflores.dao.PedidoDao;
import br.com.lacosflores.models.Item;
import br.com.lacosflores.models.Pedido;

//FAZER LIST DE PEDIDOS
//falta alguns métodos(excluir etc)
@RestController
public class PedidoController{

	@Autowired
	private PedidoDao pedidoDao;
	@Autowired
	private ItemDao itemDao;

/*@RequestMapping(value = "/pedido", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pedido> inserir(@RequestBody Pedido pedido) {
		try {
			for (Item item : pedido.getItens()) {
				item.setPedido(pedido);
			}
			pedidoDao.inserir(pedido);
			return ResponseEntity.created(new URI("/pedido" + pedido.getId())).body(pedido);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} */

	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> salvar(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
		try {
			pedidoDao.salvar(pedido);
			HttpHeaders responseHeader = new HttpHeaders();
			URI location = new URI("/pedido" + id);
			responseHeader.setLocation(location);
			return new ResponseEntity<Void>(responseHeader, HttpStatus.OK);
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

	/*@RequestMapping(value = "/pedido/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Pedido consultar(@PathVariable("id") long id) {
		return pedidoDao.consultar(id);
	}
	public double calcularValor(long idPedido, long idItem){
		Pedido pedido = pedidoDao.consultar(idPedido);
		Item item = itemDao.consultar(idItem);
		return pedido.getItens().size() * item.getValorUnitario();
				
	} */
	
	public void definirEntregue(long idPedido){
		Pedido pedido = pedidoDao.consultar(idPedido);
		pedido.setStatus("Pedido Realizado");
	}
}
