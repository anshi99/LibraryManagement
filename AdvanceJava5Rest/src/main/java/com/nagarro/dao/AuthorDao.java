package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.models.Author;

public interface AuthorDao extends JpaRepository<Author, Integer> {

}
