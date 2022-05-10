package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.FavoritePackage;

@Named
public class FavoritePackageRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(FavoritePackage favoritePackage) throws Exception {
		em.persist(favoritePackage);
		return favoritePackage.getId();
	}

	public Long update(FavoritePackage favoritePackage) throws Exception {
		em.merge(favoritePackage);
		return favoritePackage.getId();
	}
	
	public void delete(FavoritePackage favoritePackage)throws Exception {
		em.remove(em.contains(favoritePackage) ? favoritePackage : em.merge(favoritePackage));
	}

	public List<FavoritePackage> findAll() throws Exception {
		List<FavoritePackage> favoritePackages = new ArrayList<>();
		TypedQuery<FavoritePackage> query = em.createQuery("FROM FavoritePackage", FavoritePackage.class);
		favoritePackages = query.getResultList();
		return favoritePackages;
	}
	
	public List<FavoritePackage> findAllByUser(Long id) throws Exception {
		List<FavoritePackage> favoritePackages = new ArrayList<>();
		TypedQuery<FavoritePackage> query = em.createQuery("FROM FavoritePackage WHERE user_id = ?1", FavoritePackage.class);
		query.setParameter(1, id);
		favoritePackages = query.getResultList();
		return favoritePackages;
	}
	
}
