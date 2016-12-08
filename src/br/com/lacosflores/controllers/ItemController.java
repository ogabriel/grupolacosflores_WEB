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

import br.com.lacosflores.dao.ItemDao;
import br.com.lacosflores.dao.PedidoDao;
import br.com.lacosflores.models.Item;
import br.com.lacosflores.models.Pedido;

//falta alguns métodos(excluir etc)
@RestController
@CrossOrigin
public class ItemController {

	@Autowired
	private ItemDao itemDao;
	@Autowired
	private PedidoDao pedidoDao;

	@RequestMapping(value = "/{id}/item", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Item> inserir(@PathVariable("id") Long id, @RequestBody Item item) {	
		try {
			Pedido pedido = pedidoDao.consultar(id);
			item.setPedido(pedido);			
			itemDao.inserir(item);
			
			return ResponseEntity.created(new URI("/item" + item.getId())).body(item);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
		itemDao.remover(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Item> listar() {
		return itemDao.listar();
	}

	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Item consultar(@PathVariable("id") long id) {
		return itemDao.consultar(id);
	}

	@RequestMapping(value = "/item/contains/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Item> consultar_contains(@PathVariable("nome") String nome) {
		return itemDao.listar_contains(nome);
	}

}
