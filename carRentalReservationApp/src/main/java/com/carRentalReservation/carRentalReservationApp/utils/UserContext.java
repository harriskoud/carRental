package com.carRentalReservation.carRentalReservationApp.utils;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

	public static final String CORRELATION_ID = "CORRELATION_ID";
	public static final String USER_ID = "USER_ID";
	public static final String AUTH_TOKEN = "Authorization";
	public static final String ORG_ID = "ORG_ID";

	private String correlationId = new String();
	private String authToken = new String();
	private String userId = new String();
	private String orgId = new String();

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
