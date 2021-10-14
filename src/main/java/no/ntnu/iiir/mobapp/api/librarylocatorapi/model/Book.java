package no.ntnu.iiir.mobapp.api.librarylocatorapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a book in a library system.
 */
@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String isbn;
  private String title;
  private String description;
  private int pages;
  private String author;

  public Book() {}

  public Book(Long id, String isbn, String title, String description, int pages,
              String author) {
    this.id = id;
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.pages = pages;
    this.author = author;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", isbn='" + isbn + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", pages=" + pages +
        ", author='" + author + '\'' +
        '}';
  }
}
