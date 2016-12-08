package br.com.lacosflores.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//import org.hibernate.mapping.List;
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

import br.com.lacosflores.dao.DispositivoDao;
import br.com.lacosflores.dao.FloriculturaDao;
import br.com.lacosflores.dtoandroid.DTOAndroid;
import br.com.lacosflores.models.Dispositivo;
import br.com.lacosflores.models.Floricultura;

//TODO: FAZER LIST DE PEDIDOS
@RestController
@CrossOrigin()
public class DispositivoController {

	@Autowired
	private DispositivoDao dispositivoDao;
	@Autowired
	private FloriculturaDao floriculturaDao;

	@RequestMapping(value = "/dispositivo/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Dispositivo> salvar(@PathVariable("id") Long id, @RequestBody Dispositivo dispositivo) {
		try {
			dispositivoDao.salvar(dispositivo);
			//HttpHeaders responseHeader = new HttpHeaders();
			//URI location = new URI("/dispositivo" + id);
			//responseHeader.setLocation(location);
			//return new ResponseEntity<Void>(responseHeader, HttpStatus.OK);
			return ResponseEntity.ok(dispositivo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO: checar as URIs
	@RequestMapping(value = "/{id}/dispositivo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Dispositivo> inserir(@PathVariable("id") Long id,@RequestBody Dispositivo dispositivo) {
		try {
			Floricultura floricultura = floriculturaDao.consultar(id);
			dispositivo.setFloricultura(floricultura);
			dispositivoDao.inserir(dispositivo);

			return ResponseEntity.created(new URI("/dispositivo" + dispositivo.getId())).body(dispositivo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/dispositivo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remover(@PathVariable("id") long id) {
		dispositivoDao.remover(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/dispositivo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Dispositivo> listar() {
		return dispositivoDao.listar();
	}

	@RequestMapping(value = "/dispositivo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Dispositivo consultar(@PathVariable("id") long id) {
		return dispositivoDao.consultar(id);
	}

	@RequestMapping(value = "/dispositivo/celular/{imei}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public DTOAndroid consultar_imei(@PathVariable("imei") String imei) {
		Dispositivo disp = dispositivoDao.consultar_imei(imei);
		DTOAndroid dto = new DTOAndroid(disp.getId(), disp.getImei(), disp.getSenha(), disp.getFloricultura().getId());
		
		return dto;
	}
	
	@RequestMapping(value = "/dispositivo/filial/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<DTOAndroid> consultar_filial(@PathVariable("id") long id) {
		Floricultura floricultura = floriculturaDao.consultar(id);
		java.util.List<Dispositivo> listaDisp = floricultura.getDispositivos();
		
		//dispositivoDao.consultar_filial(id);
		//System.out.println(dispositivoDao.consultar_filial(id));
		//Floricultura floricultura = dispositivo.getFloricultura();
		List<DTOAndroid> dtoAndrods = new ArrayList<DTOAndroid>();
		for (Dispositivo disp : listaDisp) {
			DTOAndroid dto = new DTOAndroid(disp.getId(), disp.getImei(), disp.getSenha(), floricultura.getId());
			dtoAndrods.add(dto);
		}
		
		return dtoAndrods;
	}
	
}
