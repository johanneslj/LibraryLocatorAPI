package no.ntnu.iiir.mobapp.api.librarylocatorapi.controller;


import no.ntnu.iiir.mobapp.api.librarylocatorapi.model.Book;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.repository.BookRepository;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing.XMLParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@Component
public class BookController{

	@Autowired
	BookRepository bookRepository;


	@PostMapping(path = "/getBookDetail")
	@ResponseBody
	public JSONArray getBookDetail(@RequestBody String requestBody) {
		JSONArray book = new JSONArray();
		book.put(XMLParser.parseXML("47BIBSYS_NTNU_UB",requestBody));
		return book;
	}


	@GetMapping(path = "/getAllBooks", headers = "Accept=application/json; charset=UTF-8")
	@ResponseBody
	public Map<String,Book> getAllBooks(){
		Map<String,Book> books;
		books = bookRepository.getAllBooks();
		return books;
	}

	@GetMapping("/message")
	public String message(){
		String books = "Hei";
		return books;
	}

}

