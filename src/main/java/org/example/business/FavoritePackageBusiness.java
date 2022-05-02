package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.FavoritePackage;
import org.example.repository.FavoritePackageRepository;

@Named
public class FavoritePackageBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FavoritePackageRepository favoritePackageRepository;
	
	@Transactional
	public Long insert(FavoritePackage favoritePackage) throws Exception {
		return favoritePackageRepository.insert(favoritePackage);
	}
	
	@Transactional
	public Long update(FavoritePackage favoritePackage) throws Exception {
		return favoritePackageRepository.update(favoritePackage);
	}

	@Transactional
	public void delete(FavoritePackage favoritePackage) throws Exception {
		favoritePackageRepository.delete(favoritePackage);
	}

	@Transactional
	public List<FavoritePackage> getAll() throws Exception {
		return favoritePackageRepository.findAll();
	}

	@Transactional
	public List<FavoritePackage> getAllByUser(Long id) throws Exception {
		return favoritePackageRepository.findAllByUser(id);
	}

}
