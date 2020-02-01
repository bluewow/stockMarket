package com.stockmarket.www.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@GetMapping("card/error")
	public String Error() {
		return "error/403";
	}
}
