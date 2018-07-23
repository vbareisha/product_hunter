package com.bareisha.producthunter.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {HealthStatusTestConfiguration.class})
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
public class HealthStatusControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(healthStatusController)
				.build();
	}

	@InjectMocks
	private HealthStatusController healthStatusController;

	/**
	 * Test for health controller
	 * @throws Exception
	 */
	@Test
	public void checkStatus() throws Exception {
		setup();
		HttpHeaders headers = new HttpHeaders();

		MvcResult mvcResult = this.mockMvc
				.perform(get("/status").contentType(contentType).headers(headers))
				.andExpect(status().isOk()).andReturn();
		Map response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Map.class);
		Assert.assertTrue(response.containsKey("status"));
		Assert.assertTrue(response.containsValue("ok"));
	}
}