package no.ntnu.iiir.mobapp.api.librarylocatorapi.repository;

import no.ntnu.iiir.mobapp.api.librarylocatorapi.model.Book;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public Map<String,Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
		Map<String,Book> result = new HashMap<>();
		var sql = "select * from books";
		books = jdbcTemplate.query(sql,
				(rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("bookisbn"))
		);
		for (int i = 0; i < books.size(); i++) {

			result.put(books.get(i).getIsbn(),XMLParser.parseXML("47BIBSYS_NTNU_UB", books.get(i).getIsbn()));
		}
		return result;
	}
}
