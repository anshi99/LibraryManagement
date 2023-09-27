package com.nagarro.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nagarro.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.constants.Constants;
import com.nagarro.models.Book;
import com.nagarro.services.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	LoginService loginService;

	@GetMapping("/")
	public ModelAndView getBooks() {
		ModelAndView page = new ModelAndView(Constants.Index.INDEX);
		page.addObject("books", bookService.fetchAllBooks());
		return page;
	}


	@GetMapping(Constants.pathMaping.LOGIN_MAPPING)
	public ModelAndView loginUserView() {
		ModelAndView page = new ModelAndView(Constants.Index.LOGIN_INDEX);
		return page;
	}
	@PostMapping(Constants.pathMaping.LOGIN_MAPPING)
	public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request,
							HttpServletResponse response) {
		if(loginService.verifyUser(username, password, request, response))
			return "redirect:/";
		return "login";
	}

	
	@GetMapping(Constants.pathMaping.BOOK)
	@ResponseBody
	public Book fetchBookById(@RequestParam int bid) {
		Book book = bookService.fetchBookByID(bid);
		System.out.println( " fetch Book = " + book);
		return book;
	}

	@GetMapping(Constants.pathMaping.BOOKSADD)
	public ModelAndView addBookView() {
		ModelAndView page = new ModelAndView(Constants.Index.MODIFYBOOK);
		page.addObject("title", "Add Book");
		page.addObject("formHead", "Add Book Details");
		page.addObject("authors", bookService.fetchAllAuthors());
		page.addObject("addedOn", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));
		
		return page;
	}

	@PostMapping(Constants.pathMaping.BOOKSADD)
	@ResponseBody
	public String addBook(@RequestBody Object library) {
		String result = bookService.addBook(library);
		System.out.println(result);
		System.out.println("Rahil");
		return result;
	}

	@GetMapping(Constants.pathMaping.BOOKSUPDATE)
	public ModelAndView updateBookView(@RequestParam int bid) {
		ModelAndView page = new ModelAndView(Constants.Index.MODIFYBOOK);

		Book book = bookService.fetchBookByID(bid);
		page.addObject("title", "Edit Book");
		page.addObject("formHead", "Edit Book Details");
		page.addObject("authors", bookService.fetchAllAuthors());
		page.addObject("book", book);
		System.out.println(book);
		return page;
	}

	@PostMapping(Constants.pathMaping.BOOKSUPDATE)
	@ResponseBody
	public String updateBook(@RequestParam int bid, @RequestBody Object library) {
		String result = bookService.updateBook(bid, library);
		return result;
	}
	@DeleteMapping(Constants.pathMaping.BOOKS)
	@ResponseBody
	public void deleteBook(@RequestParam int bid) {
		bookService.deleteBook(bid);
	}

	@GetMapping(Constants.pathMaping.LOGOUT_MAPPING)
	public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constants.Session.USER_SESSION_ATTRIBUTE);

		try {
			response.sendRedirect("./login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
