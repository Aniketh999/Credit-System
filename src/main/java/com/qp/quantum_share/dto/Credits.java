package com.qp.quantum_share.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Component
public class Credits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int cid;
    private LocalDate creditDate;
    private LocalTime creditTime;
    private  int numberOfCredits;

}
