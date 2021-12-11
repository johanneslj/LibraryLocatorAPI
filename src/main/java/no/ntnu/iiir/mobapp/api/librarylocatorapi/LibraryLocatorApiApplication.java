package no.ntnu.iiir.mobapp.api.librarylocatorapi;

import no.ntnu.iiir.mobapp.api.librarylocatorapi.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

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
