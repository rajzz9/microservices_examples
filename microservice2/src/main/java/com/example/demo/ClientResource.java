package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest/hello/client")
public class ClientResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	@HystrixCommand(fallbackMethod = "defaultStores")
	public String hello(){
		String url = "http://eureka-service/rest/hello/service";
		return restTemplate.getForObject(url, String.class);
	}
	
	public String defaultStores(){
		return "Hystrix Fallback method";
	}
}
