package com.example.demo.Config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.example.demo.JsonFiles.JsonFilesToObjects;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;



@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
	
	JsonFilesToObjects jsonFilesToObjects = new JsonFilesToObjects();
	
	
	ApiResponse badRequest = new ApiResponse().content(
			new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(jsonFilesToObjects.read().get("badRequest").toString())))
			).description("Bad Request!");
	
	ApiResponse internalServerError = new ApiResponse().content(
			new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(jsonFilesToObjects.read().get("internalServerError").toString())))
			).description("Internal Server Error!");
		
	
	ApiResponse ok = new ApiResponse().content(
			new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, new io.swagger.v3.oas.models.media.MediaType().addExamples("default", new Example().value(jsonFilesToObjects.read().get("ok").toString())))
			).description("Success");
		
	


@Bean
public OpenAPI openBaseAPI() {
	Components component = new Components();
	component.addResponses("badRequest", badRequest);
	component.addResponses("internalServerError", internalServerError);
    component.addResponses("ok", ok);
	return new OpenAPI().components(component).info(new Info().title("My JUnit Application").version("1.0.0").description("Writing JUnit Tests"));
}


@Bean
public GroupedOpenApi bookApi() {
	String[] paths = {"/book/**"};
	return GroupedOpenApi.builder().group("Books").pathsToMatch(paths).build();
}

@Bean
public GroupedOpenApi authApi() {
	String[] paths = {"/auth/**"};
	return GroupedOpenApi.builder().group("Authentication").pathsToMatch(paths).build();
}

}
