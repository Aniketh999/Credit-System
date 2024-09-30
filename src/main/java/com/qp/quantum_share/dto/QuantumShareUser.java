package com.qp.quantum_share.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;

@Data
@Entity
@Component
public class QuantumShareUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private long phoneNo;
	private String password;
	private String company;
	private boolean verified;
	private String verificationToken;
	private LocalDate signUpDate;
	private String profilePic;
	private boolean trial;
	private int credit;

@OneToOne(cascade = CascadeType.ALL)
	private Credits credits;



}
