package no.ntnu.iiir.mobapp.api.librarylocatorapi.repository;

import java.util.List;
import no.ntnu.iiir.mobapp.api.librarylocatorapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Book findById(long id);

  List<Book> findByIsbn(String isbn);

  List<Book> findByAuthor(String author);

  List<Book> findByTitle(String title);

}
