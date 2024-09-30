package com.qp.quantum_share.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qp.quantum_share.dao.QuantumShareUserDao;

@Component
public class GenerateId {

		@Autowired
	QuantumShareUserDao userDao;

	public String generateFbId(String lastUserId) {
		String userId;
		if (lastUserId == null) {
			userId = "QSFB2401";
		} else {
			int lastNumber = Integer.parseInt(lastUserId.substring(6));
			int newNumber = lastNumber + 1;
			userId = "QSFB24" + String.format("%02d", newNumber);
		}
		return userId;
	}

	public String generateinstaId(String lastUserId) {
		String userId;
		if (lastUserId == null) {
			userId = "QSINS2401";
		} else {
			int lastNumber = Integer.parseInt(lastUserId.substring(7));
			int newNumber = lastNumber + 1;
			userId = "QSINS24" + String.format("%02d", newNumber);
		}
		return userId;
	}

//	public String generateuserId() {
//		String lastUserId = userDao.findLastUserId();
//		String userId;
//		if (lastUserId == null || lastUserId.isEmpty()) {
//			userId = "QSU2401";
//		} else {
//			int lastNumber = Integer.parseInt(lastUserId.substring(6));
//			int newNumber = lastNumber + 1;
//			userId = "QSU24" + String.format("%02d", newNumber);
//		}
//		System.out.println(userId);
//		return userId;
//	}

//	int pagenum=1;
//	public String generateFbPageId(String lastPageId) {
//        String pageId;
//        if (lastPageId == null) {
//            pageId = "QSFBP2401";
//        } else {
//        	pagenum=pagenum++;
//            pageId = "QSFBP240" +pagenum ;
//        }
//        return pageId;
//    }
}
