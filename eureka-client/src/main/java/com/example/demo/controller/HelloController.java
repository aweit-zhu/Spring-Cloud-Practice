package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Value("${server.port}")
	private int port;
	
	@Autowired
    private Registration registration;
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String index() {
		ServiceInstance instance = serviceInstance();
		logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getInstanceId());
		return "Hello World";
	}
	
    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
            	System.out.println(itm);
                if(itm.getPort() == port)
                    return itm;
            }   
        }
        return null;
    }
}
