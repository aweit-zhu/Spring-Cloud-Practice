package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ConsumerController {

	private static final String REST_URL_PREFIX = "http://HELLO-SERVICE";
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/ribbon-consumer", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod="indexFallback")
	public String index() {
		return restTemplate.getForObject(REST_URL_PREFIX+"/hello", String.class);
	}
	
	private String indexFallback() {
        return "很抱歉，网络拥挤！";
    }
}
