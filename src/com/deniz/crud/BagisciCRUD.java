package com.deniz.crud;

import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
			e.printStackTrace();
			return new FacesMessage(FacesMessage.SEVERITY_INFO,"Kayıt Başarısız",null);
		}finally {
			em.close();
			mf.close();
		}
		
	}
	
	
	public static Bagisci bagisciCek(String eposta)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts = em.getTransaction();

		try {
			
			Query sorgu = em.createQuery("Select b from Bagisci b where b.eposta=:eposta ");
			sorgu.setParameter("eposta", eposta);
			List<Bagisci> liste = sorgu.getResultList();
			
			if(liste.size()>0)
			{
				return liste.get(0);
			}
			else
			{
				return null;
			}
					
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			em.close();
			mf.close();
		}
	 
	}
	
	
	public static FacesMessage bagisciGuncelle(Bagisci yeniBilgi)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			ts.begin();
			em.merge(yeniBilgi);
			ts.commit();
			return new FacesMessage(FacesMessage.SEVERITY_INFO,"Güncelleme Başarılı",null);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new FacesMessage(FacesMessage.SEVERITY_INFO,"Güncelleme Başarısız",null);
		}finally {
			em.close();
			mf.close();
		}
		
	}
	
	
	
	public static FacesMessage bagisciGiris(String eposta,String parola)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		try {
			Query sorgu = em.createQuery("Select b from Bagisci b where b.eposta=:eposta and b.parola=:parola and b.durum=1");
			sorgu.setParameter("eposta", eposta);
			sorgu.setParameter("parola", parola);
			
			List<Bagisci> liste = sorgu.getResultList();
			
			if(liste.size()>0)
			{
				return new FacesMessage(FacesMessage.SEVERITY_INFO,"Kullanıcı Bulundu.",null);
			}
			else
			{
				return new FacesMessage(FacesMessage.SEVERITY_ERROR,"Kullanıcı Bulunamadı.",null);
			}
			
			
		}catch (Exception e) {
			return new FacesMessage(FacesMessage.SEVERITY_FATAL,"Hata ile Karşılaşıldı.",null);
		}finally {
			em.close();
			mf.close();
		}
	}
	
	public static void bagisciSil(Integer id)
	{
		EntityManagerFactory mf = Persistence.createEntityManagerFactory(persistenceUnitName);
		EntityManager em = mf.createEntityManager();
		EntityTransaction ts = em.getTransaction();
		
		
		try {
			ts.begin();
			
			Bagisci bagisci = em.find(Bagisci.class, id);
			bagisci.setDurum(0);
			em.merge(bagisci);
			
			ts.commit();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			mf.close();
		}
			
		
	}
	
	
	public static FacesMessage mailGonder(String eposta,String parola)
	{
		
		String gonderenAdres="eposta adresi";
		String gonderenSifre="eposta parolası";
		
		Properties ozellik = System.getProperties();
		ozellik.put("mail.smtp.auth", "true");
		ozellik.put("mail.smtp.starttls.enable", "true");
		ozellik.put("mail.smtp.host", "smtp.gmail.com");
		ozellik.put("mail.smtp.port", "587");
		
		try{
		Session session = Session.getInstance(ozellik,new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gonderenAdres, gonderenSifre); }}); 		
		
			
			  Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("deniizcandemir@gmail.com"));
              message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(eposta));
              message.setSubject("Kan Bağışı Yönetim Uygulaması - Parola Talebi");
              message.setText("Talep ettiğiniz parolanız : "+parola); 
              Transport.send(message);
              return new FacesMessage(FacesMessage.SEVERITY_INFO,"Parola gönderildi.",null);
		}catch (Exception e) {
			e.printStackTrace();
			return new FacesMessage(FacesMessage.SEVERITY_FATAL,"Bir hata oluştu.",null);
		}
	
	}
	
	
	
	
 

}
