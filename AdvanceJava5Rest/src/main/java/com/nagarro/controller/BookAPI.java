package com.nagarro.controller;

import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nagarro.constants.Constants;
import com.nagarro.services.BookService;

@Controller
@RequestMapping("/api/books")
public class BookAPI {
	@Autowired
	BookService bookService;
	@RequestMapping(value = " ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String fetchBooks(@RequestParam(required = false) String bid) {
		String result;
		if (Objects.isNull(bid)) {
			// if bookId is not present return all books
			result = bookService.listBooks();
		} else {
			// if bookId is present return only one book
			result = bookService.getBookById(Integer.parseInt(bid));
		}
		return result;
	}

	@RequestMapping(value = Constants.PathMapping.AUTHORS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String fetchAuthors(@RequestParam(required = false) String aid) {
		String result;
		if (Objects.isNull(aid)) {
			// if authorId is not present return all books
			result = bookService.listAllAuthor();
		} else {
			// if authorId is present return only one book
			result = bookService.getAuthorById(Integer.parseInt(aid));
		}
		return result;
	}

	@RequestMapping(value = Constants.PathMapping.AUTHORSADD, method = RequestMethod.POST, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addAuthor(@RequestBody Map<String, Object> library) {
		return bookService.addAuthor(library);
	}

	@RequestMapping(value = Constants.PathMapping.BOOKADD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addBook(@RequestBody Map<String, Object> library) {
		return bookService.addBook(library);
	}

	@RequestMapping(value = Constants.PathMapping.BOOKUPDATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updateBook(@RequestBody Map<String, Object> library) {
		return bookService.updateBook(library);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteBook(@RequestParam int bid) {
		return bookService.deleteBook(bid);
	}
}
