package com.deniz.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.deniz.crud.BagisciCRUD;
import com.deniz.crud.HaritaCRUD;
import com.deniz.model.Bagisci;
import com.deniz.model.Harita;
import com.deniz.session.BagisciSession;

@ManagedBean
@SessionScoped
public class HaritaController {
	
	private Integer id;
	private Double enlem;
	private Double boylam;
	private String aciklama;
	private Integer bagisiciId;
	
    private MapModel tempModel;
    private String mevcutKonum="39.920697210705406,32.854013442993164";
    private String arananKanGrubu;
    private Marker nokta;
    private Bagisci arananBagisci;
    
   
    
	public Bagisci getArananBagisci() {
		return arananBagisci;
	}
	public void setArananBagisci(Bagisci arananBagisci) {
		this.arananBagisci = arananBagisci;
	}
	public Marker getNokta() {
		return nokta;
	}
	public void setNokta(Marker nokta) {
		this.nokta = nokta;
	}
	public String getArananKanGrubu() {
		return arananKanGrubu;
	}
	public void setArananKanGrubu(String arananKanGrubu) {
		this.arananKanGrubu = arananKanGrubu;
	}
	public String getMevcutKonum() {
		return mevcutKonum;
	}
	public void setMevcutKonum(String mevcutKonum) {
		this.mevcutKonum = mevcutKonum;
	}
	public MapModel getTempModel() {
		return tempModel;
	}
	public void setTempModel(MapModel tempModel) {
		this.tempModel = tempModel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getEnlem() {
		return enlem;
	}
	public void setEnlem(Double enlem) {
		this.enlem = enlem;
	}
	public Double getBoylam() {
		return boylam;
	}
	public void setBoylam(Double boylam) {
		this.boylam = boylam;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public Integer getBagisiciId() {
		return bagisiciId;
	}
	public void setBagisiciId(Integer bagisiciId) {
		this.bagisiciId = bagisiciId;
	}
	
	
	
	public void bagisciKonumKaydet()
	{
			Bagisci bagisci = BagisciCRUD.bagisciCek(BagisciSession.getEposta());
			Harita harita = new Harita();
			harita.setAciklama(aciklama);
			harita.setBoylam(boylam);
			harita.setEnlem(enlem);
			harita.setBagisciId(bagisci.getId());
			
			mevcutKonum = ""+enlem+","+boylam;
			
			
			FacesMessage mesaj = HaritaCRUD.bagisciKonumKaydet(harita);
			FacesContext.getCurrentInstance().addMessage(null, mesaj);
			 
	}
	
	public void konumCek()
	{
		if(BagisciSession.getEposta()!=null)
		{
			Bagisci bagisci = BagisciCRUD.bagisciCek(BagisciSession.getEposta());
			tempModel = HaritaCRUD.bagisciKonumCek(bagisci.getId());
				
		}
				
	}
	
	
	public void kanGrubuArama()
	{
		tempModel=HaritaCRUD.kanGrubuArama(arananKanGrubu);
	}
	
	public void noktaSecimi(OverlaySelectEvent event) 
	{
	        nokta = (Marker) event.getOverlay();
	        arananBagisci = HaritaCRUD.secilenBagisciCek(nokta.getLatlng().getLat(), nokta.getLatlng().getLng());
	}
	      
	

}
