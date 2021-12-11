package no.ntnu.iiir.mobapp.api.librarylocatorapi.controller;


import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.model.Book;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class BookController{

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

//	@PostMapping(path = "/getBookDetail")
//	@ResponseBody
//	public Map<String,Book> getBookDetail(@RequestBody String requestBody) {
//		Map<String,Book> book;
//		book.put(XMLParser.parseXML("47BIBSYS_NTNU_UB",requestBody));
//		return book;
//	}


	@GetMapping(path = "/getAllBooks", headers = "Accept=application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Book> getAllBooks(){
		Map<String,Book> books;
		books = bookRepository.getAllBooks();
		return books;
	}

	/**
	 * Gets search result from API, searching for author, title, and ISBN.
	 * @param query search query
	 * @return search result
	 */
	@GetMapping(path = "/search={query}", headers = "Accept=application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Book> search(@PathVariable(value = "query") String query) {
		HashMap<String, Book> result = new HashMap<>();

		Book isbnBook = findBooksByIsbn(query);

		result.putAll(searchForBooksByAuthor(query));
		result.putAll(searchForBooksByTitle(query));

		if (isbnBook != null) {
			result.put(isbnBook.getIsbn(), isbnBook);
		}

		return result;
	}

	/**
	 * Gets books which contain the query string in the title.
	 * @param query search query
	 * @return search result
	 */
	private Map<String, Book> searchForBooksByTitle(String query) {
		HashMap<String, Book> result = new HashMap<>();
		Collection<Book> allBooks = bookRepository.getAllBooks().values();

		for (Book book : allBooks) {
			if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
				result.put(book.getIsbn(), book);
			}
		}

		return result;
	}

	/**
	 * Searches for books where the author contains query
	 * @param query Search query
	 * @return List of books containing query in author
	 */
	private Map<String, Book> searchForBooksByAuthor(String query) {
		HashMap<String, Book> result = new HashMap<String, Book>();
		Collection<Book> allBooks = bookRepository.getAllBooks().values();

		for (Book book : allBooks) {
			if (book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
				result.put(book.getIsbn(), book);
			}
		}

		return result;
	}

	private Book findBooksByIsbn(String isbn) {
		return bookRepository.getAllBooks().get(isbn);
	}

	@RequestMapping("/message")
	public String message(){
		String books = "Hei";
		return books;
	}

}

