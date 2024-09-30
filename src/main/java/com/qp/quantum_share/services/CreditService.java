//package com.qp.quantum_share.services;
//
//import com.qp.quantum_share.dao.QuantumShareUserDao;
//import com.qp.quantum_share.dto.Credits;
//import com.qp.quantum_share.dto.QuantumShareUser;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Optional;
//
//@Service
//public class CreditService {
//
//    private final QuantumShareUserDao quantumShareUserDao;
//
//    public CreditService(QuantumShareUserDao quantumShareUserDao) {
//        this.quantumShareUserDao = quantumShareUserDao;
//    }
//
//    public void updateCredits(int userId) {
//        QuantumShareUser optionalCredits = quantumShareUserDao.fetchUser(userId);
//        Credits credits;
//
//        if (optionalCredits.isPresent()) {
//            credits = optionalCredits.get();
//            LocalDateTime lastCreditDateTime = credits.getCreditDateTime();
//            long hoursSinceLastCredit = ChronoUnit.HOURS.between(lastCreditDateTime, LocalDateTime.now());
//
//            if (hoursSinceLastCredit >= 24) {
//                credits.setNumberOfCredits(5);
//                credits.setCreditDateTime(LocalDateTime.now());
//                quantumShareUserDao.saveCredits(credits); // Save updated credits
//            }
//        } else {
//            credits = new Credits();
//            credits.setNumberOfCredits(5);
//            credits.setCreditDateTime(LocalDateTime.now());
//            quantumShareUserDao.saveCredits(credits); // Save new credits
//        }
//    }
//}