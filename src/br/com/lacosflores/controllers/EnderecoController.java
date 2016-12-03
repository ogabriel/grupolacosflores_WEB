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

import br.com.lacosflores.dao.EnderecoDao;
import br.com.lacosflores.models.Endereco;


//FAZER LIST DE PEDIDOS
//falta alguns métodos(excluir etc)
@RestController
public class EnderecoController	 {

	@Autowired
	private EnderecoDao enderecoDao;

	@RequestMapping(value = "/endereco", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Endereco> inserir(@RequestBody Endereco endereco) {
		enderecoDao.inserir(endereco);

		try {
			URI location = new URI("/endereco" + endereco.getId());
			return ResponseEntity.created(location).body(endereco);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/endereco/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> salvar(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
		try {
			enderecoDao.salvar(endereco);
			HttpHeaders responseHeader = new HttpHeaders();
			URI location = new URI("/endereco" + id);
			responseHeader.setLocation(location);
			return new ResponseEntity<Void>(responseHeader, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/endereco/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id") long id) {
		enderecoDao.remover(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/endereco", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Endereco> listar() {
		return enderecoDao.listar();
	}

	@RequestMapping(value = "/endereco/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Endereco consultar(@PathVariable("id") long id) {
		return enderecoDao.consultar(id);
	}
	
	@RequestMapping(value = "/endereco/cep/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Endereco consultar_cep(@PathVariable("cep") String cep) {
		return enderecoDao.listar_cep(cep);
	}

}
