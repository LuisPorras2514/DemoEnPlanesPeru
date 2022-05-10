package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.RoomType;
import org.example.repository.RoomTypeRepository;

@Named
public class RoomTypeBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RoomTypeRepository roomTypeRepository;
	
	@Transactional
	public List<RoomType> getAll() throws Exception {
		return roomTypeRepository.findall();
	}

}
