package com.qp.quantum_share.dao;

import java.util.List;
import java.util.Optional;

import com.qp.quantum_share.dto.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qp.quantum_share.dto.QuantumShareUser;
import com.qp.quantum_share.repository.QuantumShareUserRespository;

@Component
public class QuantumShareUserDao {

	@Autowired
	private QuantumShareUserRespository userRespository;

	public void saveUser(QuantumShareUser user) {
		userRespository.save(user);
	}

	public QuantumShareUser fetchUser(int userID) {
		return userRespository.findById(userID).orElse(null); // Use orElse to avoid NoSuchElementException
	}

	public List<QuantumShareUser> findByEmailOrPhoneNo(String email, long mobile) {
		return userRespository.findByEmailOrPhoneNo(email, mobile);
	}

	public QuantumShareUser findByVerificationToken(String token) {
		return userRespository.findByVerificationToken(token);
	}


}