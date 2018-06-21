package com.netflixzuul.zuulProxy.util;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

@Component
public class FilterUtils {

	public static final String PRE_FILTER_TYPE = "pre";
	// public static final String PRE_FILTER_TYPE = "not_pre";
	public static final String POST_FILTER_TYPE = "post";
	public static final String CORRELATION_ID = "CORRELATION_ID";

	public static final String AUTH_TOKEN = "Authorization";

	public String getCorrelationId() {

		RequestContext ctx = RequestContext.getCurrentContext();

		System.out.println(ctx.getRequest().getHeader(CORRELATION_ID));
		if (ctx.getRequest().getHeader(CORRELATION_ID) != null) {
			return ctx.getRequest().getHeader(CORRELATION_ID);
		} else {
			return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
		}
	}

	public void setCorrelationId(String correlationId) {

		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
	}

	
	
	public String getAuthToken() {

		RequestContext ctx = RequestContext.getCurrentContext();

		System.out.println(ctx.getRequest().getHeader(AUTH_TOKEN));
		if (ctx.getRequest().getHeader(AUTH_TOKEN) != null) {
			return ctx.getRequest().getHeader(AUTH_TOKEN);
		} else {
			return ctx.getZuulRequestHeaders().get(AUTH_TOKEN);
		}
	}

	public void setAuthToken(String token) {

		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(AUTH_TOKEN, token);
	}

}
