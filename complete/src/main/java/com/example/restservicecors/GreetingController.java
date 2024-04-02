package com.example.restservicecors;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	
	
	@CrossOrigin(origins = {"http://localhost:9000", "http://localhost:9080"})
	@PostMapping(path = "/greeting", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE )
	public Greeting greeting(@RequestBody(required = false) Data data) {
		System.out.println("==== get greeting ====");
		String name=data!=null?data.getName():"world";
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@PostMapping(path="/greeting-javaconfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE )
	public Greeting greetingWithJavaconfig(@RequestBody(required = false) Data data) {
		System.out.println("==== in greeting ====");
		String name=data!=null?data.getName():"world";
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
