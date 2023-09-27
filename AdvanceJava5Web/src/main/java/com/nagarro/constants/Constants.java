package com.nagarro.constants;

public class Constants {
	// Query for User
	public interface Query {
		public static final String GET_USER_QUERY = "FROM User WHERE username=:uName";
	}

	// Session Attributes
	public interface Session {
		public static final String USER_SESSION_ATTRIBUTE = "user";
	}

	// Login Form Attribute
	public interface Login {
		public static final String USERNAME_FIELD = "username";
		public static final String PASSWORD_FIELD = "password";
		public static final String USERNAME_ERROR_FIELD = "username_error";
		public static final String PASSWORD_ERROR_FIELD = "password_error";
	}

	// Index attributes
	public interface Index
	{
		public static final String LOGIN_INDEX = "login";
		public static final String INDEX = "index";
		public static final String MODIFYBOOK = "modifyBook";
	}
	
	//Path attributes
	public interface pathMaping{
		public static final String LOGIN_MAPPING = "/login";
		public static final String LOGOUT_MAPPING="/logout";
		public static final String BOOK = "/books/book";
		public static final String BOOKS = "/books";
		public static final String BOOKSADD = "/books/add";
		public static final String BOOKSUPDATE = "/books/update";
		public static final String BOOKSAUTHOR="/books/authors";
	}
	// Exceptions
	public interface Exceptions {
		public static final String UNKNOWN_ERROR = "Some Error Occurred.";
		public static final String USERNAME_DOES_NOT_EXISTS = "Username does not exists.";
		public static final String INCORRECT_PASSWORD = "Incorrect Password.";
		public static final String RESPONSE_BODY_PARSING_ERROR = "Error Parsing Response Body!";
		public static final String RETRIEVING_RESPONSE_ERROR = "Error Retrieving Books From REST Server!";
	}
}
