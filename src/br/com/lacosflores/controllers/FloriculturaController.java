package br.com.lacosflores.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
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
import br.com.lacosflores.dtoandroid.DTOAndroid;
import br.com.lacosflores.models.Dispositivo;
import br.com.lacosflores.models.Floricultura;
import br.com.lacosflores.models.Item;
import br.com.lacosflores.models.Pedido;
import br.com.lacosflores.models.Usuario;

//apagar
@RestController
@CrossOrigin
public class FloriculturaController {

	@Autowired
	private FloriculturaDao floriculturaDao;
	
		

	@RequestMapping(value = "/floricultura/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Floricultura> salvar(@PathVariable("id") Long id, @RequestBody Floricultura floricultura) {
		try {
			for (Dispositivo disp : floricultura.getDispositivos()) {
				disp.setFloricultura(floricultura);
			}

			for (Usuario usu : floricultura.getUsuarios()) {
				usu.setFloricultura(floricultura);
			}

			for (Pedido pedido : floricultura.getPedidos()) {
				for (Item item : pedido.getItens()) {
					item.setPedido(pedido);
				}
				pedido.setFloricultura(floricultura);
			}
			
			floriculturaDao.salvar(floricultura);
			//HttpHeaders responseHeader = new HttpHeaders();
			//URI location = new URI("/floricultura" + id);
			//responseHeader.setLocation(location);
			//return new ResponseEntity<Void>(responseHeader, HttpStatus.OK);
			return ResponseEntity.ok(floricultura);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/floricultura", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Floricultura> inserir(@RequestBody Floricultura floricultura) {
		// Usuario e Long criados por padrao do parametro inserirUsuario
		try {

			for (Dispositivo disp : floricultura.getDispositivos()) {
				disp.setFloricultura(floricultura);
			}

			for (Usuario usu : floricultura.getUsuarios()) {
				usu.setFloricultura(floricultura);
			}

			for (Pedido pedido : floricultura.getPedidos()) {
				for (Item item : pedido.getItens()) {
					item.setPedido(pedido);
				}
				pedido.setFloricultura(floricultura);
			}

			floriculturaDao.inserir(floricultura);

			URI location = new URI("/floricultura/" + floricultura.getId());
			return ResponseEntity.created(location).body(floricultura);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/floricultura/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id") long id) {
		floriculturaDao.remover(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/floricultura", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Floricultura> listar() {
		return floriculturaDao.listar();
	}	
	
	@RequestMapping(value = "/floricultura/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Floricultura consultar(@PathVariable("id") long id) {
		return floriculturaDao.consultar(id);
	}

	@RequestMapping(value = "/floricultura/contains/{nomeFantasia}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Floricultura> listar_contains(@PathVariable("nomeFantasia") String nomeFantasia) {
		return floriculturaDao.listar_contains(nomeFantasia);
	}
	
	@RequestMapping(value = "/floricultura/cep/{cep}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Floricultura> consultar_cep(@PathVariable("cep") String cep) {
		return floriculturaDao.procurar_cep(cep);
	}
	
	@RequestMapping(value = "/pedido/quantidade/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int consultar_pedidos(@PathVariable("id") long id) {
		
		try{
			Floricultura f = floriculturaDao.consultar(id);
			return f.getPedidos().size();
			
		} catch(Exception e){
			return 0;
		}
		
		
		
	}

}
