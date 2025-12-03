

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
  private final int authorId;

  /** Name of the Author. */
  private String authorName;

  /**
   * COnstructor for the Author class.
   *
   * @param authorName input name of the Author from the user
   * @throws IllegalArgumentException If the name is empty
   */
  public Author(String authorName) {
    if (authorName.trim().isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.authorId = author_nextId++;
    this.authorName = authorName;
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
   * @return authorId, the id of the Author
   */
  public int getAuthorId() {
    return authorId;
  }

  /**
   * Getter-method, returns the name of the Author.
   *
   * @return authorName, the name of the Author
   */
  public String getAuthorName() {
    return authorName;
  }

  /**
   * toString method, returns a unique representation of the Author.
   *
   * @return A unqiue string with the Author's ID and name.
   */
  @Override
  public String toString() {
    return "Author ID: " + authorId + ", Name: " + authorName;
  }
}
