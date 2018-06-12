package com.deniz.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bagisci
 *
 */
@Entity
@Table(name="Bagisci")
public class Bagisci implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String ad;
	private String soyad;
	private String adres;
	private Long telefon;
	private String eposta;
	private String kangrubu;
	private String parola;
	
	private static final long serialVersionUID = 1L;

	public Bagisci() {
		super();
	}

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
	
	 
   
}
