package com.example.chapter3;

import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

//	@Test
//	public void contextLoads() {
//	}
	
	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void testSpringBootApp() throws JsonProcessingException, IOException {
		String body = restTemplate.getForObject("/", String.class);
		org.junit.Assert.assertThat(new ObjectMapper().readTree(body).get("message").textValue(), is("Hello World!"));
	}

}
