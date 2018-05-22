package com.rko.myspring.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	private Object message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";

	}

}
