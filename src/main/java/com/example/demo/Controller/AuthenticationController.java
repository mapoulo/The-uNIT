package com.example.demo.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@PostMapping("/signIn")
	@Operation(
			
			description = "The SignIn Service",
			responses = {
					@ApiResponse(
							responseCode = "200",
							ref = "ok"
							
							),
					
					@ApiResponse(
							responseCode = "400", ref = "badRequest"
							),
					
					@ApiResponse(
							responseCode = "500",
							ref = "internalServerError"
							
							)
			}
			)
	public String signIn() {
		return "";
	}
}
