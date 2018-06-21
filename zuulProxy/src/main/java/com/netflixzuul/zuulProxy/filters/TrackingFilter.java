package com.netflixzuul.zuulProxy.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflixzuul.zuulProxy.util.FilterUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TrackingFilter extends ZuulFilter {

	private final static int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static Logger LOGGER = LoggerFactory.getLogger(TrackingFilter.class);
	@Autowired
	private FilterUtils filterUtils;

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		
		getUserName();

		if (isCorrelationIdPresent()) {
			LOGGER.info("correlation id found");
			filterUtils.getCorrelationId();
		} else {
			filterUtils.setCorrelationId(generateCorrelationId());
			LOGGER.info("correlation id not found");
		}

		RequestContext ctx = RequestContext.getCurrentContext();
		LOGGER.info("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return FilterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	private boolean isCorrelationIdPresent() {

		try {
			if (filterUtils.getCorrelationId() != null) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.debug("No Correlation Id");
		}
		return false;
	}

	private String generateCorrelationId() {
		String generatedCorId = java.util.UUID.randomUUID().toString();
		System.out.println(generatedCorId);
		return generatedCorId;
	}

	private String getUserName() {
		String result = "";
		if (filterUtils.getAuthToken() != null) {
			String authToken = filterUtils.getAuthToken().replace("Bearer ", "");
			try {
				Claims claims = Jwts.parser().setSigningKey(("123").getBytes("UTF-8")).parseClaimsJws(authToken)
						.getBody();
				result = (String) claims.get("username");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
