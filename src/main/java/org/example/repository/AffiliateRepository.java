package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.Affiliate;

@Named
public class AffiliateRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(Affiliate affiliate) throws Exception {
		em.persist(affiliate);
		return affiliate.getId();
	}

	public Long update(Affiliate affiliate) throws Exception {
		em.merge(affiliate);
		return affiliate.getId();
	}

	public List<Affiliate> findAll() throws Exception {
		List<Affiliate> affiliates = new ArrayList<>();
		TypedQuery<Affiliate> query = em.createQuery("FROM Affiliate", Affiliate.class);
		affiliates = query.getResultList();
		return affiliates;
	}
	
	public List<Affiliate> login(String email,String password)  throws Exception{
		List<Affiliate> affiliates=new ArrayList<>();
		TypedQuery<Affiliate> query=em.createQuery("FROM Affiliate WHERE mail=?1 AND password=?2", Affiliate.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		affiliates=query.getResultList();
		return affiliates;
	}

}
