package com.opsly.newsfeed.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.opsly.newsfeed.app.Application;
import com.opsly.newsfeed.configs.URLConfig;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { Application.class, URLConfig.class })
public class RequestProcessingTests {

	private final Logger log = LogManager.getLogger(getClass());

	private final int MAX_REQUESTS = 5;

	@Autowired
	private WebApplicationContext wac;

	@Test
	@Order(1)
	public void singleRequest() throws Throwable {

		MockMvc mvc = MockMvcBuilders.webAppContextSetup(wac).build();

		log.info("Executing single request");

		mvc.perform(MockMvcRequestBuilders.get("/"));

		log.info("Execution completed");

	}

	@Test
	@Order(2)
	public void orderedRequests() throws Throwable {

		MockMvc mvc = MockMvcBuilders.webAppContextSetup(wac).build();

		for (int i = 0; i < MAX_REQUESTS; i++) {

			log.info("Executing request");

			mvc.perform(MockMvcRequestBuilders.get("/"));

			log.info("Execution completed");

		}

	}

	@Test
	@Order(3)
	public void parallelRequests() throws Throwable {

		log.info("Exectuting request in parallel mode");

		MockMvc mvc = MockMvcBuilders.webAppContextSetup(wac).build();

		ExecutorService executor = Executors.newCachedThreadPool();

		List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();

		for (int i = 0; i < MAX_REQUESTS; i++) {

			tasks.add(() -> {
				mvc.perform(MockMvcRequestBuilders.get("/"));

				return null;
			});
		}

		List<Future<Void>> results = executor.invokeAll(tasks);

		for (Future<Void> r : results) {
			r.get();
		}

		log.info("All requests completed");
	}
}
