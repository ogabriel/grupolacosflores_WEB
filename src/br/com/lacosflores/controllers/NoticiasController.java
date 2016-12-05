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

import br.com.lacosflores.dao.NoticiasDao;
import br.com.lacosflores.models.Noticias;

//FAZER LIST DE PEDIDOS
//falta alguns métodos(excluir etc)
@RestController
@CrossOrigin
public class NoticiasController {

	@Autowired
	private NoticiasDao noticiasDao;

	@RequestMapping(value = "/noticias/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> salvar(@PathVariable("id") Long id, @RequestBody Noticias noticias) {
		try {
			noticiasDao.salvar(noticias);
			HttpHeaders responseHeader = new HttpHeaders();
			URI location = new URI("/noticias/" + id);
			responseHeader.setLocation(location);
			return new ResponseEntity<Void>(responseHeader, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/noticias", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Noticias> inserir(@RequestBody Noticias noticias) {
		noticiasDao.inserir(noticias);

		try {
			URI location = new URI("/noticias" + noticias.getId());
			return ResponseEntity.created(location).body(noticias);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/noticias/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id") long id) {
		noticiasDao.remover(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/noticias", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Noticias> listar() {
		return noticiasDao.listar();
	}

	@RequestMapping(value = "/noticias/contains/{titulo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Noticias> listar_contains(@PathVariable("titulo") String titulo) {
		System.out.println(titulo);
		return noticiasDao.listar_contains(titulo);
	}

	@RequestMapping(value = "/noticias/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Noticias consultar(@PathVariable("id") long id) {
		return noticiasDao.consultar(id);
	}

}
