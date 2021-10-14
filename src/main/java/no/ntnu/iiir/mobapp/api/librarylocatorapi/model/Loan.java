package no.ntnu.iiir.mobapp.api.librarylocatorapi.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents a loan of a book in a library.
 * Keeps track of start and end time of loan, book loaned, and loaner.
 */
@Entity
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long userId;
  private Long bookId;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

  /**
   * Default constructor for entity.
   */
  public Loan() {}

  /**
   * constructor for Loan entity.
   * @param id Loan ID.
   * @param userId ID of user loaning book.
   * @param bookId ID of book loaned.
   * @param startTime Start time of loan.
   * @param endTime End time/Due date of loan.
   */
  public Loan(Long id, Long userId, Long bookId, LocalDateTime startTime,
              LocalDateTime endTime) {
    this.id = id;
    this.userId = userId;
    this.bookId = bookId;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "Loan{" +
        "id=" + id +
        ", userId=" + userId +
        ", bookId=" + bookId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        '}';
  }
}
