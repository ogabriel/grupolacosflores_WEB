package br.com.lacosflores.dtoandroid;

import br.com.lacosflores.models.Floricultura;

public class DTOAndroid {
	
	private Long id;
	private String imei;
	private String senha;
	private Long id_floricultura;
	public DTOAndroid(Long id, String imei, String senha, Long id_floricultura) {
		this.id = id;
		this.imei = imei;
		this.senha = senha;
		this.id_floricultura = id_floricultura;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getId_floricultura() {
		return id_floricultura;
	}
	public void setId_floricultura(Long id_floricultura) {
		this.id_floricultura = id_floricultura;
	}
	
	
	
	

}
