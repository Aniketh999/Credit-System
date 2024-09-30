package com.qp.quantum_share.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.qp.quantum_share.dto.QuantumShareUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {

	@Value("${jwt.secret}")
	private String secretKey;

	@SuppressWarnings("deprecation")
	public String generateJWT(QuantumShareUser user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", user.getUserId());
		claims.put("email", user.getEmail());

		// Set expiration time to a very large value or never expire
		// In this example, expiration time is set to January 1, 3000
//		Date expirationDate = new Date(Long.MAX_VALUE);

		Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 2); // Add 2 hours
        Date expirationDate = calendar.getTime();
        
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
}
