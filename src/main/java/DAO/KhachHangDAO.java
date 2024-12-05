package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Model.KhachHang;

public class KhachHangDAO {
	// kết nối csdl
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("BIITS");
	// truy vấn csdl
	EntityManager em = factory.createEntityManager();
	
	public void create(KhachHang kh) {
		try {
			em.getTransaction().begin();
			em.persist(kh);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
}
