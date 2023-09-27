package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.models.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

}

