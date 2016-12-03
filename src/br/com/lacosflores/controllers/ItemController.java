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
import br.com.lacosflores.models.Item;
import br.com.lacosflores.models.Noticias;


//falta alguns métodos(excluir etc)
@RestController
public class ItemController {
	
	@Autowired
	private ItemDao itemDao;
	
	@RequestMapping(value="/pedido/{id}/item", method=RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Item> addPedido(@PathVariable long id, @RequestBody Item item){
		itemDao.inserir(item);
		
		try {
			URI location = new URI("/item/" + item.getId());
			
			return ResponseEntity.created(location).body(item);
		} catch (Exception exception) {
			exception.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> salvar(@PathVariable("id") Long id, @RequestBody Item item) {
		try {
			itemDao.inserir(item);
			HttpHeaders responseHeader = new HttpHeaders();
			URI location = new URI("/item" + id);
			responseHeader.setLocation(location);
			return new ResponseEntity<Void>(responseHeader, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/lista/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		itemDao.remover(id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Item> listar() {
		return itemDao.listar();
	}
	
	@RequestMapping(value = "/item/contains/{nome}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Item> consultar_contains(@PathVariable("nome") String nome) {
		return itemDao.listar_contains(nome);
	}
	
}
