package interfaces;

import com.nagarro.models.Author;
import com.nagarro.models.Book;

public interface BookServiceInterface {
	
	public Book[] fetchAllBooks();
	public Book fetchBookByID(int bid);
	public Author[] fetchAllAuthors();
	public String addBook(Object library);
	public String updateBook(int bid, Object library);
	public String deleteBook(int bid);

}
