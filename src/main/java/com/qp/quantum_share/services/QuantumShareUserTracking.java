package com.qp.quantum_share.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.quantum_share.configuration.ConfigurationClass;
import com.qp.quantum_share.dto.QuantumShareUser;

@Service
public class QuantumShareUserTracking {

	@Autowired
	ConfigurationClass configure;

//	public Map<String, Object> isValidCredit(QuantumShareUser user) {
//		Map<String, Object> map = configure.getMap();
//		if (user.isTrial()) {
//			if (user.getCredit() <= 0) {
//				map.put("validcredit", false);
//				map.put("message", "credit depleted. Please upgrade to a subscription.");
//				return map;
//			} else {
//				map.put("validcredit", true);
//				return map;
//			}
//		} else if (user.getSubscriptionDetails().isSubscribed()) {
//			map.put("validcredit", true);
//			return map;
//		} else {
//			map.put("validcredit", false);
//			map.put("message", "Package has been expired!! Please Subscribe Your package.");
//			return map;
//		}
//	}

}
