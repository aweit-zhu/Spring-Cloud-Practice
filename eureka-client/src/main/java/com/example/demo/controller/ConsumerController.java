package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	private static final String REST_URL_PREFIX = "http://RIBBON-CONSUMER";
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/consumer-hello", method = RequestMethod.GET)
	public String index() {
		return restTemplate.getForObject(REST_URL_PREFIX+"/hello", String.class);
	}
}
