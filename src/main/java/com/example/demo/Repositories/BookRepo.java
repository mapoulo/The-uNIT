package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
