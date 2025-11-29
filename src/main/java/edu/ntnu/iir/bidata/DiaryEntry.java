package edu.ntnu.iir.bidata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an DiaryEntry, with apporirate attributes and methods.
 *
 * @auther Adrian Balunan
 * @verison 1.0
 */
public class DiaryEntry {
  /** DiaryEntry Attributes. */

  /* Release date of the entry in LocalDateTime. */
  private String releaseDate;

  /**
   * Accessible and available Id_counter, makes sure that after each occurrence of an DiaryEntry as
   * a unique ID.
   */
  private static int nextId = 1;

  /**
   * Used to reset nextId counter.
   */
  public static void resetEntryNextId() {
    nextId = 1;
  }

  /** Actual unique ID for the DiaryEntry object. */
  private final int Id;

  /** The title of the Entry. */
  private String title;

  /** Accosicated Author object of the Entry. */
  private Author Author;

  /** Content variables that store info. */
  private String content;

  /**
   * Contructur for the DiaryEntry class, with input parameters. Relase date is automatically set to
   * the current date and time, and custimized using a formatter
   *
   * @param Author input Author
   * @param title input title
   * @param content input content
   */
  public DiaryEntry(Author Author, String title, String content) {
    this.Author = Author;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm:ss)");
    this.releaseDate = LocalDateTime.now().format(formatter);

    this.Id = nextId++;
    this.title = title;
    this.content = content;
  }

  /**
   * Contructur for the DiaryEntry class, with input parameters AND with custom release date. Used
   * only with filler content, for better representation. Hours, minutes and seconds will be
   * 00:00:00.
   *
   * @param Author input Author
   * @param title input title
   * @param content input content
   * @param releaseDate input release date. Hours, minutes and seconds to 00:00:00
   */
  public DiaryEntry(Author Author, String title, String content, String releaseDate) {
    this.Author = Author;
    this.releaseDate = releaseDate + " (00:00:00)";
    this.Id = nextId++;
    this.title = title;
    this.content = content;
  }

  /**
   * Getter-methods for getting the release date.
   *
   * @return Relase date of the entry
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Getter-method for getting the title.
   *
   * @return Title of the entry
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter-method for getting the content.
   *
   * @return Returns the content of the 
   */
  public String getContent() {
    return content;
  }

  /**
   * Getter-method for getting the Author name.
   *
   * @return
   */
  public String getAuthorName() {
    return Author.getAuthor_name();
  }

  /**
   * Getter-method for getting the Author ID.
   *
   * @return
   */
  public int getAuthorID() {
    return Author.getAuthor_Id();
  }

  /**
   * Getter-method for getting the ID.
   *
   * @return
   */
  public int getId() {
    return Id;
  }

  /**
   * Getter-method for getting the ID.
   *
   * @return
   */
  public Author getAuthorObject() {
    return Author;
  }

  /**
   * Unique toString method that prints out all attributes of the DiaryEntry object The seeAll
   * method provides a better representation for the user, and therefore not used.
   *
   * @return
   */
  @Override
  public String toString() {
    return "ID: "
        + Id
        + ". ReleaseDate: "
        + releaseDate
        + ", Title: "
        + title
        + ", Author: "
        + Author.getAuthor_name()
        + ", Content: "
        + content;
  }
}
