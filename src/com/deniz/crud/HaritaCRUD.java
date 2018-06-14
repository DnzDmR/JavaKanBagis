package com.deniz.crud;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
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
			Query sorgu = em.createQuery("Select h from Harita h where h.bagisciId=:id order by h.id desc");
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
			
			Query sorgu = em.createQuery("Select h from Harita h where bagisciId=:bagisciId");
			sorgu.setParameter("bagisciId", harita.getBagisciId());
			
			ts.begin();
			if(sorgu.getResultList().size()>0)
			{
				List<Harita> liste = sorgu.getResultList();
				harita.setID(liste.get(0).getID());
				em.merge(harita);
			}
			else	
			{
				em.persist(harita);
			}
			
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
	
	
	public static MapModel kanGrubuArama(String kanGrubu)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts =em.getTransaction();
		
		try {
			
			Query sorgu = em.createQuery("Select h from Harita h inner join Bagisci b on b.id = h.bagisciId where b.kangrubu=:kangrubu");
			sorgu.setParameter("kangrubu", kanGrubu);
			
			List<Harita> liste = sorgu.getResultList();
			ArrayList<LatLng> noktalar = new ArrayList<LatLng>();
			MapModel tempModel = new DefaultMapModel();
			
			if(liste.size()>0)
			{
				for(Harita harita :liste)
				{
					LatLng a = new LatLng(harita.getEnlem(), harita.getBoylam());
					tempModel.addOverlay(new Marker(a,harita.getAciklama()));
				}
				
			}			
			return tempModel;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
			mf.close();
		}
		
		
	}

}
