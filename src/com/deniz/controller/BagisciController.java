package com.deniz.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.deniz.crud.BagisciCRUD;
import com.deniz.model.Bagisci;

@ManagedBean
@SessionScoped
public class BagisciController {
	
	private Integer id;
	private String ad;
	private String soyad;
	private String adres;
	private Long telefon;
	private String eposta;
	private String kangrubu;
	private String parola;
	private Integer durum;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public Long getTelefon() {
		return telefon;
	}
	public void setTelefon(Long telefon) {
		this.telefon = telefon;
	}
	public String getEposta() {
		return eposta;
	}
	public void setEposta(String eposta) {
		this.eposta = eposta;
	}
	public String getKangrubu() {
		return kangrubu;
	}
	public void setKangrubu(String kangrubu) {
		this.kangrubu = kangrubu;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public Integer getDurum() {
		return durum;
	}
	public void setDurum(Integer durum) {
		this.durum = durum;
	}
	
	
	public void bagisciKayit()
	{
		Bagisci bagisci = new Bagisci();
		bagisci.setAd(ad);
		bagisci.setAdres(adres);
		bagisci.setEposta(eposta);
		bagisci.setSoyad(soyad);
		bagisci.setKangrubu(kangrubu);
		bagisci.setTelefon(telefon);
		bagisci.setParola(parola);
		bagisci.setDurum(1);
		
		FacesMessage mesaj = BagisciCRUD.bagisciKayit(bagisci);
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
	}
	
	
	public String bagisciGiris()
	{
		FacesMessage mesaj = BagisciCRUD.bagisciGiris(eposta, parola);
		
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
		
		if(mesaj.getSeverity()==FacesMessage.SEVERITY_INFO)
		{
			return "anasayfa";
		}
		else
		{
			return "index";
		}
		 
	}
	
 
	

}
