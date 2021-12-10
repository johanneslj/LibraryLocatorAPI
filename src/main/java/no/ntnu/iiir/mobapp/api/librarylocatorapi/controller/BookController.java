package no.ntnu.iiir.mobapp.api.librarylocatorapi.controller;


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

	@RequestMapping("/message")
	public String message(){
		String books = "Hei";
		return books;
	}

}

