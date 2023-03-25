package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Exceptions.BookNotFoundException;
import com.example.demo.Model.Book;
import com.example.demo.Repositories.BookRepo;
import com.example.demo.Services.BookService;

@ExtendWith(MockitoExtension.class)

public class BookServiceTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock private BookRepo repo;
	private BookService bookServiceUnderTest;
	
	
	@BeforeEach
	void setUp() {
		bookServiceUnderTest = new BookService(repo);
	}
	

	
	@Test
	@Disabled
	public void getAllBooks() {
		//When
		bookServiceUnderTest.getAllBooks();
		//Then
		verify(repo).findAll();
	}
	
	
	@Test
	@Disabled
	public void saveBookTest() {
		//Give
		Book book = Book.builder().id(0).name("Java").build();
		//when
		bookServiceUnderTest.saveBook(book);
		//Then
		ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
		verify(repo).save(argumentCaptor.capture());
		
		Book capturedValue = argumentCaptor.getValue();
		
		assertThat(capturedValue).isEqualTo(book);
	}
	
	
	
	 @Test
	 @Disabled
	 public void deleteBookById() {
		long id = 2L;
		Book book = Book.builder().id(id).name("Java").build();
        when(repo.findById(id)).thenReturn(Optional.of(book));
		bookServiceUnderTest.deleteBookById(id);
	    verify(repo, timeout(1)).deleteById(id);
	 }
	
	 
	 @Test
	 @Disabled
	 public void deleteBookById_BookNotFound() {
		 long id = 1L;
		 when(repo.findById(id)).thenReturn(Optional.empty());
		 assertThrows(BookNotFoundException.class, ()->{
			 bookServiceUnderTest.deleteBookById(id);
		 });
		 verify(repo, never()).deleteById(id);
	 }
	 
	 

	 @Test
	    public void testDeleteBookById_RollbackOnException() {
	        long id = 123L;
	        Optional<Book> optionalBook = Optional.of(new Book(id, "Test Book"));
	        when(repo.findById(id)).thenReturn(optionalBook);
	        doThrow(new RuntimeException()).when(repo).deleteById(id);
	        
	        assertThrows(RuntimeException.class, () -> bookServiceUnderTest.deleteBookById(id));

	        verify(repo, times(1)).findById(id);
	        verify(repo, times(1)).deleteById(id);  
	    }

}
