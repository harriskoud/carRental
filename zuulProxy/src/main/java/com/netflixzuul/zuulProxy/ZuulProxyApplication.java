package com.netflixzuul.zuulProxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflixzuul.zuulProxy.filters.ErrorFilter;
import com.netflixzuul.zuulProxy.filters.PostFilter;
import com.netflixzuul.zuulProxy.filters.PreFilter;
import com.netflixzuul.zuulProxy.filters.RouteFilter;
import com.netflixzuul.zuulProxy.filters.TrackingFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}
	
/*    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }*/
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
    @Bean
    public TrackingFilter trackFilter() {
        return new TrackingFilter();
    }
}
