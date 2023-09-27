package com.nagarro.services;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.nagarro.dao.AuthorDao;
import com.nagarro.dao.BookDao;
import com.nagarro.models.Author;
import com.nagarro.models.Book;

import interfaces.BookServiceInterface;

import com.nagarro.constants.Constants;

@Component
public class BookService implements BookServiceInterface {
	ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	BookDao bookDao;
	@Autowired
	AuthorDao authorDao;
	@Autowired
	Book book;
	@Autowired
	Author author;

	// Method to list all books from database
	@Override
	public String listBooks() {
		String result = null;

		try {
			result = objMapper.writeValueAsString(bookDao.findAll());
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;
	}

	// Method to get Book By ID
	@Override
	public String getBookById(int bid) {
		String result = null;

		try {
			result = objMapper.writeValueAsString(bookDao.findById(bid).orElse(null));
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;
	}

	// Method to list all Authors from the database
	@Override
	public String listAllAuthor() {
		String result = null;

		try {
			result = objMapper.writeValueAsString(authorDao.findAll());
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}
		return result;
	}

	@Override
	public String addAuthor(Map<String, Object> library) {
		String result = null;
		try {
			int aid = Integer.parseInt(library.get("aid").toString());
			String aname = library.get("aname").toString();

			author.setAid(aid);
			author.setAname(aname);

			result = objMapper.writeValueAsString(authorDao.save(author));

		} catch (IllegalArgumentException iae) {
			result = Constants.Exceptions.AUTHOR_NOT_AVAILABLE;
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;

	}

	@Override
	public String getAuthorById(int aid) {
		String result = null;
		try {
			result = objMapper.writeValueAsString(authorDao.findById(aid).orElse(null));
		} catch (JsonProcessingException | NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;
	}

	// Method to add book in database
	@Override
	public String addBook(Map<String, Object> library) {
		String result = null;
		try {
			int bid = Integer.parseInt(library.get("bid").toString());
			String bname = library.get("bname").toString();
			int aid = Integer.parseInt(library.get("aid").toString());
			String date = library.get("addedOn").toString();

			book.setBid(bid);
			book.setBname(bname);

			Author author = authorDao.findById(aid).orElse(null);
			book.setAuthor(author);

			book.setAddedOn(date);

			if(Objects.nonNull(book.getAuthor()))
				result = objMapper.writeValueAsString(bookDao.save(book));
			else
				result = Constants.Exceptions.AUTHOR_NOT_AVAILABLE;

		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;
	}

	// Method to update Book from database
	@Override
	public String updateBook(Map<String, Object> library) {
		String result = null;
		try {
			int bid = Integer.parseInt(library.get("bid").toString());
			String bname = library.get("bname").toString();
			int aid = Integer.parseInt(library.get("aid").toString());

			Book book = bookDao.findById(bid).orElse(null);
			book.setBname(bname);
			book.setAuthor(authorDao.findById(aid).orElse(null));

			if(Objects.nonNull(book.getAuthor()))
				result = objMapper.writeValueAsString(bookDao.save(book));
			else
				result = Constants.Exceptions.AUTHOR_NOT_AVAILABLE;

		} catch (IllegalArgumentException iae) {
			result = Constants.Exceptions.AUTHOR_NOT_AVAILABLE;
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;
	}

	// Method to delete a book from database
	@Override
	public String deleteBook(int bid) {
		String result = null;
		try {
			Book book = bookDao.findById(bid).orElse(null);

			if (Objects.nonNull(book)) {
				bookDao.delete(book);
				result = "{\"success\" : true}";
			} else {
				result = Constants.Exceptions.BOOK_NOT_FOUND;
			}

		} catch (IllegalArgumentException iae) {
			result = Constants.Exceptions.AUTHOR_NOT_AVAILABLE;
		} catch (Exception e) {
			result = Constants.Exceptions.UNKNOWN_ERROR;
		}

		return result;
	}
}
