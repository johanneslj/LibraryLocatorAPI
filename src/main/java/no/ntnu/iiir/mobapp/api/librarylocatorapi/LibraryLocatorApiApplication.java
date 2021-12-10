package no.ntnu.iiir.mobapp.api.librarylocatorapi;

import com.fasterxml.jackson.databind.JsonNode;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.repository.BookRepository;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing.XMLParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class LibraryLocatorApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryLocatorApiApplication.class);
	}

	@Autowired
	BookRepository bookRepository;



	public static void main(String[] args) {
		SpringApplication.run(LibraryLocatorApiApplication.class, args);
	}
}
