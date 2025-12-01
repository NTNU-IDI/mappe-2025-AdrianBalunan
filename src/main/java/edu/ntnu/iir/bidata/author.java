package edu.ntnu.iir.bidata;


/**
 * Author class contains the name of the Author and associates an unqiue ID.
 *
 * @Author Adrian Balunan
 * @version 1.0
 */
public class Author {
  /**
   * Accessible and available Id_counter, makes sure that after each occurrence of. an Author as a
   * unique ID
   */
  private static int author_nextId = 1;

  /** Actual unique ID for the Author. */
  private final int author_Id;

  /** Name of the Author. */
  private String author_name;

  /**
   * COnstructor for the Author class.
   *
   * @param author_name input name of the Author from the user
   */
  public Author(String author_name) {
    this.author_Id = author_nextId++;
    this.author_name = author_name;
  }

  /**
   * Method that resets author_nextid.
   */
  public static void resetNextId() {
    author_nextId = 1;
  }

  /**
   * Getter-method, returns the unique ID of the Author.
   *
   * @return author_Id, the id of the Author
   */
  public int getAuthor_Id() {
    return author_Id;
  }

  /**
   * Getter-method, returns the name of the Author.
   *
   * @return author_name, the name of the Author
   */
  public String getAuthor_name() {
    return author_name;
  }

  /**
   * toString method, returns a unique representation of the Author.
   *
   * @return A unqiue string with the Author's ID and name.
   */
  @Override
  public String toString() {
    return "Forfatter ID: " + author_Id + ", Navn: " + author_name;
  }
}
