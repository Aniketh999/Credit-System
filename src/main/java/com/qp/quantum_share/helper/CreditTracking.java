package com.qp.quantum_share.helper;

import com.qp.quantum_share.dao.CreditsDao;
import com.qp.quantum_share.dao.QuantumShareUserDao;
import com.qp.quantum_share.dto.Credits;
import com.qp.quantum_share.dto.QuantumShareUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class CreditTracking {

    @Autowired
    QuantumShareUser user;

    @Autowired
    QuantumShareUser qpUser;

    @Autowired
    private QuantumShareUserDao userDao;

    @Autowired
    Credits credits;

    @Autowired
    CreditsDao creditsDao;

    @Value("${free.credits}")
    private int credit;

    public void creditTracking(QuantumShareUser user) {
        QuantumShareUser qpUser = userDao.fetchUser(user.getUserId());
        System.out.println(qpUser.getCredits().getCreditDate());
        System.out.println(LocalTime.now());
        if (user.getCredits() == null) {
            credits.setNumberOfCredits(credit);
            credits.setCreditDate(LocalDate.now());
            credits.setCreditTime(LocalTime.now());
            user.setCredits(credits);
            userDao.saveUser(user);
        } else if (qpUser.getCredits() != null&&!qpUser.getCredits().getCreditDate().equals(LocalDate.now())) {
            Credits credits = qpUser.getCredits();
//            checkAndDelete(user);
            // Update credits and set current time
            credits.setNumberOfCredits(credit);
            credits.setCreditDate(LocalDate.now());
            credits.setCreditTime(LocalTime.now());
            user.setCredits(credits);
            userDao.saveUser(user);
        } else {
            System.out.println("User or credits not found for user ID: " + user.getUserId());
        }
    }

//    public void checkAndDelete(QuantumShareUser user) {
//        QuantumShareUser qpUser = userDao.fetchUser(user.getUserId());
//
////        if (qpUser == null || qpUser.getCredits() == null) {
////            System.out.println("User or credits not found for user ID: " + user.getUserId());
////            return; // Exit if user or credits are not found
////        }
//
//        Credits deluseCredits = qpUser.getCredits();
//        LocalDateTime currentTime = LocalDateTime.now();
//        LocalDateTime lastCreditTime = deluseCredits.getCreditDateTime();
//
//        // Check if 24 hours have passed since last credit assignment
//        if (lastCreditTime == null || ChronoUnit.HOURS.between(lastCreditTime, currentTime) >= 24) {
//            // Reset credits to 5 if 24 hours have passed
//            deluseCredits.setNumberOfCredits(5);
//            deluseCredits.setCreditDateTime(currentTime);
//            creditsDao.saveCredits(deluseCredits);
//        } else {
//            // Check if the last credit assignment was on the previous day
//            LocalDate lastCreditDate = lastCreditTime.toLocalDate();
//            LocalDate currentDate = currentTime.toLocalDate();
//
//            if (lastCreditDate.isBefore(currentDate)) {
//                // If it was the previous day, update credits
//                deluseCredits.setNumberOfCredits(5);
//                deluseCredits.setCreditDateTime(currentTime);
//                creditsDao.saveCredits(deluseCredits);
//            } else if (lastCreditDate.isEqual(currentDate)) {
//                // If it is today, do not update credits or time
//                System.out.println("Credits not updated for user ID: " + user.getUserId() + " as it's still today.");
//                return; // Exit without making changes
//            } else {
//                // If it is today but not a new login, reset credits to 0
//                deluseCredits.setCreditDateTime(null);
//                deluseCredits.setNumberOfCredits(0);
//                creditsDao.saveCredits(deluseCredits);
//            }
//        }
//    }
}