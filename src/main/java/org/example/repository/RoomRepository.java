package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.Room;

@Named
public class RoomRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(Room room) throws Exception {
		em.persist(room);
		return room.getId();
	}
	
	public Long update(Room room) throws Exception {
		em.merge(room);
		return room.getId();
	}
	
	public void delete(Room room)throws Exception {
		em.remove(em.contains(room) ? room : em.merge(room));
	}
	
	public List<Room> findAll() throws Exception {
		List<Room> rooms = new ArrayList<>();

		TypedQuery<Room> query = em.createQuery("FROM Room", Room.class);
		rooms = query.getResultList();
		return rooms;
	}
	
	public List<Room> findAllByService(Long id) throws Exception {
		List<Room> rooms = new ArrayList<>();

		TypedQuery<Room> query = em.createQuery("FROM Room WHERE service_id = ?1", Room.class);
		query.setParameter(1, id);
		rooms = query.getResultList();
		return rooms;
	}
}
