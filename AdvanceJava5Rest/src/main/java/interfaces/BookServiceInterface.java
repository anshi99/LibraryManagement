package interfaces;

import java.util.Map;

public interface BookServiceInterface {
	
	public String listBooks();
	public String getBookById(int bid);
	public String listAllAuthor();
	public String addAuthor(Map<String, Object> library);
	public String getAuthorById(int aid);
	public String addBook(Map<String, Object> library);
	public String updateBook(Map<String, Object> library);
	public String deleteBook(int bid);

}
