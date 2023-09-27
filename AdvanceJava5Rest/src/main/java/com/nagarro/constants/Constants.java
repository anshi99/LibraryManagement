package com.nagarro.constants;

public class Constants {

	// Exception
	public interface Exceptions {
		public static final String UNKNOWN_ERROR = "Some Error Occurred!";
		public static final String JSON_PROCESSING_ERROR = "Error While Processing JSON Data!";
		public static final String AUTHOR_NOT_AVAILABLE = "Author Not Available!";
		public static final String BOOK_NOT_FOUND = "Book Not Found!";
		public static final String AUTHOR_NOT_FOUND = "Author Not Found!";
	}
	
	public interface PathMapping
	{
		public static final String AUTHORS = "/authors";
		public static final String AUTHORSADD = "/authors/add";
		public static final String BOOKADD = "/add";
		public static final String BOOKUPDATE = "/update";
	}

}
