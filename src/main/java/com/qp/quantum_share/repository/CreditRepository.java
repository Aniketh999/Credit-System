package com.qp.quantum_share.repository;


import com.qp.quantum_share.dto.Credits;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credits ,  Integer> {


}
