package com.qp.quantum_share.dao;

import com.qp.quantum_share.dto.Credits;
import com.qp.quantum_share.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreditsDao {

    @Autowired
    private CreditRepository creditRepository;


    public Credits saveCredits(Credits credits) {
        return creditRepository.save(credits);
    }

//    public Credits saveDelCredit(int noOfCredit) {
//        return creditRepository.saveByCredit(noOfCredit);
//    }
//
//    public Credits saveDelTime(LocalDateTime dateTime) {
//        return creditRepository.saveDateTime(dateTime);
//    }

    public Optional<Credits> fetchCreditsByUserId(int userId) {
        return creditRepository.findById(userId);
    }




}
