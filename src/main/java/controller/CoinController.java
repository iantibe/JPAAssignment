package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.CoinItem;

public class CoinController {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPAAssignment");
	
	public List<CoinItem> showCoins() {
		EntityManager em = emfactory.createEntityManager();
		List<CoinItem> coinlist = em.createQuery("SELECT i FROM CoinItem i").getResultList();
		return coinlist;
		
	}
	
	public void insert(CoinItem coin) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(coin);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(CoinItem coin) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CoinItem> query = em.createQuery("select i from CoinItem i where i.type = :inputtype and i.price = :inputprice", CoinItem.class);
		query.setParameter("inputtype", coin.getType());
		query.setParameter("inputprice", coin.getPrice());
		query.setMaxResults(1);
		CoinItem item = query.getSingleResult();
		em.remove(item);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<CoinItem> searchByType(String type) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CoinItem> query = em.createQuery("select i from CoinItem i where i.type = :inputtype",CoinItem.class);
		query.setParameter("inputtype", type);
		List<CoinItem> list = query.getResultList();
		em.close();
		return list;
	}
	
	public List<CoinItem> searchByPrice(double price) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<CoinItem> query = em.createQuery("select i from CoinItem i where i.price = :inputprice", CoinItem.class);
		query.setParameter("inputprice", price);
		List<CoinItem> list = query.getResultList();
		em.close();
		return list;
	}
	
	public CoinItem searchById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		CoinItem result = em.find(CoinItem.class, id);
		em.close();
		return result;
	}
	
	public void updateCoin(CoinItem item) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(item);
		em.getTransaction().commit();
		em.close();
	}

}
