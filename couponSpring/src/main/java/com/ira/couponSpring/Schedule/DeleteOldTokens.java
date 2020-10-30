package com.ira.couponSpring.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ira.couponSpring.Security.TokenManager;
//@Component
public class DeleteOldTokens {
	@Autowired
	private TokenManager tokenManager;

	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void deleteOldTokens() {
		tokenManager.removeExpiredToken();
	}
}
