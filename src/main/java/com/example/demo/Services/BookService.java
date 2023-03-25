package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.hibernate.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Model.Book;
import com.example.demo.Repositories.BookRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {
	
	@Autowired
	private BookRepo repo;
	
	
	public Book saveBook(Book book) {
		return repo.save(book);
	}
	
	public List<Book> getAllBooks(){
		return repo.findAll().stream().toList();
	}
	
	public Book updateBook(Book book) {
		if(book == null || book.getId() == 0){
			throw new PropertyNotFoundException("The book field cannot be empty");
		}
		
		Optional<Book> optionalBook = repo.findById(book.getId());
		if(!optionalBook.isPresent()) {
			throw new BookNotFoundException("Book Not Found");
		}
		
		
		Book existingBook = Book.builder()
				.id(book.getId())
		.name(book.getName())
		.build();
		
		return repo.save(existingBook);
	}
	
	
	public Book getBookById(long id) {
		Optional<Book> optionalBook = repo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book Not Found");
		}
	}
	
	@Transactional
	public ResponseEntity<String> deleteBookById(long id) {
	    Optional<Book> optionalBook = repo.findById(id);
	    if (optionalBook.isPresent()) {
	        Book existingBook = optionalBook.get();
	        try {
	            repo.deleteById(id);
	            return new ResponseEntity<String>("Book deleted successfully!", HttpStatus.OK);
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to delete book with id " + id, e);
	        }
	    } else {
	        throw new BookNotFoundException("Book with id " + id + " is not found");
	    }  
	}

}
