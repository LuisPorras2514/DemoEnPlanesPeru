package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.Affiliate;
import org.example.repository.AffiliateRepository;

@Named
public class AffiliateBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AffiliateRepository affiliateRepository;

	@Transactional
	public Long insert(Affiliate affiliate) throws Exception {
		return affiliateRepository.insert(affiliate);
	}

	@Transactional
	public Long update(Affiliate affiliate) throws Exception {
		return affiliateRepository.update(affiliate);
	}
	
	@Transactional
	public List<Affiliate> listarAfiliado() throws Exception {
		return affiliateRepository.findAll();
	}
	
	@Transactional
	public List<Affiliate> login(String email, String password) throws Exception {
		return affiliateRepository.login(email, password);
	}
}
