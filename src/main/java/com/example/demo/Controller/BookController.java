package com.example.demo.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Book;
import com.example.demo.Services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService service;

	
	@PostMapping("/save")
@Operation(
			
			description = "The SaveBook Service",
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
	public Book saveBook(@Validated @RequestBody Book book) {
		return service.saveBook(book);
	}
	
	
	@PutMapping("/update")
@Operation(
			
			description = "The Update Service",
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
	public Book updateBook(@Validated @RequestBody  Book book) {
		return service.updateBook(book);
	}
	
	
	@GetMapping("/")
@Operation(
			
			description = "Get All Books Service",
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
	public List<Book> getAllBooks(){
		return service.getAllBooks();
	}
	
	@GetMapping("/byId/{id}")
@Operation(
			
			description = "Get Book By Id",
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
	public Book getBookById(@Validated @PathVariable long id) {
		return service.getBookById(id);
	}
	
	@DeleteMapping("/delete/{id}")
@Operation(
			
			description = "Delete Book By Id",
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
	public ResponseEntity<String> deleteBookById(@Validated @PathVariable long id) {
		return service.deleteBookById(id);
	}
}
