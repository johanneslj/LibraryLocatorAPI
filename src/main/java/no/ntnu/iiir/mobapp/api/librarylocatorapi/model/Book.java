package no.ntnu.iiir.mobapp.api.librarylocatorapi.model;


/**
 * Represents a book in a library system.
 */
public class Book {
  private Long id;
  private String isbn;


  public Book(Long id, String isbn) {
    this.id = id;
    this.isbn = isbn;

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }


  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", isbn='" + isbn + '\'' +
        '}';
  }
}
