package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.RoomType;

@Named
public class RoomTypeRepository implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public List<RoomType> findall() throws Exception {
		List<RoomType> roomtypes = new ArrayList<>();

		TypedQuery<RoomType> query = em.createQuery("FROM RoomType", RoomType.class);
		roomtypes = query.getResultList();
		return roomtypes;
	}
	
}
