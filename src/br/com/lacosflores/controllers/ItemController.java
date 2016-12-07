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
import br.com.lacosflores.models.Item;
import br.com.lacosflores.models.Noticias;

//falta alguns métodos(excluir etc)
@RestController
@CrossOrigin
public class ItemController {

	@Autowired
	private ItemDao itemDao;

	@RequestMapping(value = "/item", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Item> inserir(@RequestBody Item noticias) {
		itemDao.inserir(noticias);

		try {
			URI location = new URI("/item" + noticias.getId());
			return ResponseEntity.created(location).body(noticias);
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
