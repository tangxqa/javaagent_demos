package com.rrs.appplat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
	
	
	@GetMapping("hello")
	public Object sayHello() {
		
		return SingletonClass.getInstance().sayHelloWorld();
	}
	
}
