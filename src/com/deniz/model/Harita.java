package com.deniz.model;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Harita
 *
 */
@Entity
@Table(name="Harita")
public class Harita implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer ID;
	private Double enlem;
	private Double boylam;
	private String aciklama;
	@Column(name="bagisci_id")
	private Integer bagisiciId;
	
	private static final long serialVersionUID = 1L;

	public Harita() {
		super();
	}   
 
	
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getBagisiciId() {
		return bagisiciId;
	}
	public void setBagisiciId(Integer bagisiciId) {
		this.bagisiciId = bagisiciId;
	}
	public Double getEnlem() {
		return this.enlem;
	}
	public void setEnlem(Double enlem) {
		this.enlem = enlem;
	}   
	public Double getBoylam() {
		return this.boylam;
	}
	public void setBoylam(Double boylam) {
		this.boylam = boylam;
	}   
	public String getAciklama() {
		return this.aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
   
}
