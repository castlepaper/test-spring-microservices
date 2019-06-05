package com.example.chapter3.boothateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BoothateoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoothateoasApplication.class, args);
	}

}

@RestController
class GreetingController {
	@RequestMapping("/greeting")
	@ResponseBody
	public HttpEntity<Greet> greeting(@RequestParam(value="name", required=false, defaultValue="HATEOAS") String name) {
		Greet greet = new Greet("Hello " + name);
		greet.add(
				linkTo(
						methodOn(GreetingController.class).greeting(name)
				).withSelfRel()
		);
		return new ResponseEntity<Greet>(greet, HttpStatus.OK);
	}
}

class Greet extends ResourceSupport {
	private String message;
	public Greet() {}
	public Greet(String message) {
		this.setMessage(message);
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return this.message;
	}
}