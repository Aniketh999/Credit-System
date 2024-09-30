package com.qp.quantum_share.controller;

import java.util.HashMap;
import java.util.Map;

import com.qp.quantum_share.dto.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qp.quantum_share.configuration.JwtUtilConfig;
import com.qp.quantum_share.dao.QuantumShareUserDao;
import com.qp.quantum_share.dto.QuantumShareUser;
import com.qp.quantum_share.response.ResponseStructure;
import com.qp.quantum_share.services.QuantumShareUserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/quantum-share/user")
public class QuantumShareUserController {

	@Autowired
	QuantumShareUserService quantumShareUserService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	ResponseStructure<String> structure;

	@Autowired
	JwtUtilConfig jwtUtilConfig;

	@Autowired
	QuantumShareUserDao userDao;


	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<String>> userLogin(@RequestParam String emph,
			@RequestParam String password) {
		return quantumShareUserService.login(emph, password);
	}

	@PostMapping("/signup")
	public ResponseEntity<ResponseStructure<String>> signup(@RequestBody QuantumShareUser userDto) {
		return quantumShareUserService.userSignUp(userDto);
	}

//	@GetMapping("/access/remainingdays")
//	public ResponseEntity<PackageResponse> userRemainingDays(@RequestBody User user) {
//		return quantumShareUserService.calculateRemainingPackageDays(user);
//	}

	@GetMapping("/verify")
	public ResponseEntity<ResponseStructure<String>> verifyEmail(@RequestParam("token") String token) {
		return quantumShareUserService.verifyEmail(token);
	}

	@GetMapping("/account-overview")
	public ResponseEntity<ResponseStructure<String>> accountOverView() {
		String token = request.getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			structure.setCode(115);
			structure.setMessage("Missing or invalid authorization token");
			structure.setStatus("error");
			structure.setPlatform(null);
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
		}
		String jwtToken = token.substring(7); // remove "Bearer " prefix
		int userId = jwtUtilConfig.extractUserId(jwtToken);
		return quantumShareUserService.accountOverView(userId);
	}

	@PostMapping("/account-overview")
	public ResponseEntity<ResponseStructure<String>> accountOverView(
			@RequestParam(required = false) MultipartFile file, @RequestParam String name) {
		System.out.println(name );
		if (file.isEmpty() || !file.getContentType().startsWith("image")) {
			structure.setCode(HttpStatus.BAD_REQUEST.value());
			structure.setMessage("Missing or invalid file type");
			structure.setStatus("error");
			structure.setPlatform(null);
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
		}
		String token = request.getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			structure.setCode(115);
			structure.setMessage("Missing or invalid authorization token");
			structure.setStatus("error");
			structure.setPlatform(null);
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
		}
		String jwtToken = token.substring(7);
		int userId = jwtUtilConfig.extractUserId(jwtToken);
		return quantumShareUserService.accountOverView(userId, file);
	}

//	@GetMapping("/access/remainingdays")
//	public ResponseEntity<ResponseStructure<String>> userRemainingDays() {
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.calculateRemainingPackageDays(userId);
//	}

	@GetMapping("/test/session")
	public Map<String, Object> test() {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("coming");
		map.put("name", "meghana");
		map.put("company", "QP");
		map.put("id", "QSU24001");
		return map;
	}

//	@GetMapping("/connected/socialmedia/facebook")
//	public ResponseEntity<ResponseStructure<String>> fetchConnectedFB() {
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.fetchConnectedFb(userId);
//
//	}
//
//	@GetMapping("/connected/socialmedia/instagram")
//	public ResponseEntity<ResponseStructure<String>> fetchConnectedinsta() {
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.fetchConnectedInsta1(userId);
//
//	}
//
//	@GetMapping("/connected/socialmedia/telegram")
//	public ResponseEntity<ResponseStructure<String>> fetchConnectedTelegram() {
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.fetchConnectedTelegram(userId);
//	}
//	
//	@GetMapping("/connected/socialmedia/linkedIn")
//	public ResponseEntity<ResponseStructure<String>> fetchConnectedLinkedIn() {
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.fetchLinkedIn(userId);
//	}
//	
//	@GetMapping("/info")
//	public ResponseEntity<ResponseStructure<String>> fetchUserInfo(){
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.fetchUserInfo(userId);	
//	}
//
//	@GetMapping("/connected/socialmedia/youtube")
//	public ResponseEntity<ResponseStructure<String>> fetchConnectedYoutube() {
//		String token = request.getHeader("Authorization");
//		if (token == null || !token.startsWith("Bearer ")) {
//			structure.setCode(115);
//			structure.setMessage("Missing or invalid authorization token");
//			structure.setStatus("error");
//			structure.setPlatform(null);
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.UNAUTHORIZED);
//		}
//		String jwtToken = token.substring(7); // remove "Bearer " prefix
//		int userId = jwtUtilConfig.extractUserId(jwtToken);
//		return quantumShareUserService.fetchConnectedYoutube(userId);
//
//	}


	@PostMapping("/request-credits")
	public ResponseEntity<ResponseStructure> requestCredits(@RequestBody Credits credits) {
		ResponseStructure response = new ResponseStructure();
		try {
			// Call the service method to process credit request
//			boolean isSuccess = QuantumShareUserService.processCreditRequest(credits);
			if (true) {
				response.setCode(HttpStatus.OK.value());
				response.setMessage("Credits successfully requested.");
				response.setStatus("success");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setCode(HttpStatus.BAD_REQUEST.value());
				response.setMessage("Failed to request credits.");
				response.setStatus("error");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage("An error occurred while requesting credits: " + e.getMessage());
			response.setStatus("error");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

