package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public boolean login(String email, String matKhau) {
		String jpql = "SELECT k FROM KhachHang k WHERE k.Email = :Email AND k.MatKhau = :MatKhau";
        TypedQuery<KhachHang> query = em.createQuery(jpql, KhachHang.class);
        query.setParameter("Email", email);
        query.setParameter("MatKhau", matKhau);
        List<KhachHang> result = query.getResultList();
        if (!result.isEmpty()) {
        	return true;
        } else {
        	return false;
        } 
	}
}
