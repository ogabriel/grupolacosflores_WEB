package br.com.lacosflores.dtoandroid;

import br.com.lacosflores.models.Floricultura;

public class DTOAndroid {
	
	private Long id;
	private String imei;
	private String senha;
	public DTOAndroid(Long id, String imei, String senha, Floricultura floricultura) {
		this.id = id;
		this.imei = imei;
		this.senha = senha;
		this.floricultura = floricultura;
	}
	private Floricultura floricultura;
	
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
	public Floricultura getFloricultura() {
		return floricultura;
	}
	public void setFloricultura(Floricultura floricultura) {
		this.floricultura = floricultura;
	}
	
	
	

}
