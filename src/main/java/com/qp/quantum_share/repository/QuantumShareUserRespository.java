package com.qp.quantum_share.repository;

import java.util.List;

import com.qp.quantum_share.dto.Credits;
import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.quantum_share.dto.QuantumShareUser;
import org.springframework.stereotype.Repository;
@Repository
public interface QuantumShareUserRespository extends JpaRepository<QuantumShareUser, Integer> {

	public List<QuantumShareUser> findByEmailOrPhoneNo(String email, long phoneNo);

	public QuantumShareUser findTopByOrderByUserIdDesc();

	public QuantumShareUser findByVerificationToken(String token);


}

