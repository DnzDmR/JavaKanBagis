package com.deniz.controller;


import java.awt.Window;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.PrimeFacesContextFactory;

import com.deniz.crud.BagisciCRUD;
import com.deniz.model.Bagisci;
import com.deniz.session.BagisciSession;

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
			HttpSession session = BagisciSession.getSession();
			session.setAttribute("eposta",eposta);
			
			Bagisci bagisci =BagisciCRUD.bagisciCek(BagisciSession.getEposta());
			
			this.setId(bagisci.getId());
			this.setAd(bagisci.getAd());
			this.setSoyad(bagisci.getSoyad());
			this.setAdres(bagisci.getAdres());
			this.setDurum(bagisci.getDurum());
			this.setEposta(bagisci.getEposta());
			this.setKangrubu(bagisci.getKangrubu());
			this.setTelefon(bagisci.getTelefon());
			
			return "anasayfa.jsf?faces-redirect=true";
		}
		else
		{
			return "index";
		}
		 
	}
	
	public void sessionKontrol()  
	{
		if(BagisciSession.getEposta()==null)
		{
			try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			}catch(Exception e) {e.printStackTrace();}
		}
	}
	
	public String sessionDestroy()
	{
		BagisciSession.sessionDestroy();
		return "index.jsf?faces-redirect=true";
		
	}
	
	public Bagisci kullaniciProfilCek()
	{
		return BagisciCRUD.bagisciCek(BagisciSession.getEposta());
	}
	
	
	public void bagisciGuncelle()
	{
		Bagisci eskiBilgi = BagisciCRUD.bagisciCek(BagisciSession.getEposta());
		Bagisci yeniBilgi = new Bagisci();
		yeniBilgi.setId(eskiBilgi.getId());
		yeniBilgi.setAd(ad);
		yeniBilgi.setSoyad(soyad);
		yeniBilgi.setAdres(adres);
		yeniBilgi.setTelefon(telefon);
		yeniBilgi.setDurum(durum);
		yeniBilgi.setKangrubu(kangrubu);
		yeniBilgi.setParola(parola);
		yeniBilgi.setEposta(eposta);
		
		FacesMessage mesaj = BagisciCRUD.bagisciGuncelle(yeniBilgi);
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
	}
	
	
	public String bagisciSil()
	{
		BagisciCRUD.bagisciSil(id);
		sessionDestroy();
		return "index.jsf?faces-redirect=true";
	}
	
	public void mailGonder()
	{
		Bagisci bagisci = BagisciCRUD.bagisciCek(eposta);
		FacesMessage mesaj;
		if(bagisci==null)
		{
			mesaj = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Kayıt Bir eposta Girmediniz",null);
		}
		else
		{
			mesaj = BagisciCRUD.mailGonder(eposta,bagisci.getParola());	
		}
		
		FacesContext.getCurrentInstance().addMessage(null, mesaj);
		
	}
 
	

}
