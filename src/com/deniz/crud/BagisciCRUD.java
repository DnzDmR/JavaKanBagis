package com.deniz.crud;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.deniz.model.Bagisci;

public class BagisciCRUD {
	
	private static final String persistenceUnitName="JavaKanBagis"; 
	
	public static FacesMessage bagisciKayit(Bagisci bagisci)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			ts.begin();
			em.persist(bagisci);
			ts.commit();
			
			return new FacesMessage(FacesMessage.SEVERITY_INFO,"Kayıt Başarılı",null);
			
		}catch (Exception e) {
			return new FacesMessage(FacesMessage.SEVERITY_INFO,"Kayıt Başarısız",null);
		}finally {
			em.close();
			mf.close();
		}
		
	}

}
