package com.netflixzuul.zuulProxy.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflixzuul.zuulProxy.util.FilterUtils;

import lombok.extern.slf4j.Slf4j;


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

		if (isCorrelationIdPresent()) {
			LOGGER.info("correlation id found");
			filterUtils.getCorrelationId();
		} else {
			filterUtils.setCorrelationId(generateCorrelationId());
			LOGGER.info("correlation id found");
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
		return java.util.UUID.randomUUID().toString();
	}

}
