package com.deniz.crud;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.deniz.model.Harita;

public class HaritaCRUD {
	
	private static final String persistenceUnitName="JavaKanBagis";
	
	public static MapModel bagisciKonumCek(Integer id)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		
		MapModel haritaModel = new DefaultMapModel();
		try
		{
			Query sorgu = em.createQuery("Select h from Harita h where h.bagisiciId=:id order by h.id desc");
			sorgu.setParameter("id", id);
			
			List<Harita> harita = new ArrayList<Harita>(); 
			harita = sorgu.getResultList();
			
			if(harita.size()>0)
			{
				LatLng nokta = new LatLng(harita.get(0).getEnlem(), harita.get(0).getBoylam()); 
				haritaModel.addOverlay(new Marker(nokta,harita.get(0).getAciklama()));
			}
	
			return haritaModel;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
			mf.close();
		}
	}
	
	
	
	public static FacesMessage bagisciKonumKaydet(Harita harita)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts =em.getTransaction();
		
		try {
				
			ts.begin();
			em.persist(harita);
			ts.commit();
			
			
			
			return new FacesMessage(FacesMessage.SEVERITY_INFO,"Konum Kaydedildi",null);
		}catch (Exception e) {
			e.printStackTrace();
			return new FacesMessage(FacesMessage.SEVERITY_FATAL,"Hata Oluştu",null);
		}finally {
			em.close();
			mf.close();
		}
	}

}
