
import java.util.ArrayList;

/**
 * AuthorRegistry class contains a list of authors and methods to manage them.
 *
 * @Author Adrian Balunan
 * @version 1.0
 */
public class AuthorRegistry {
  /* ArrayList that stores authors of the Author class. */
  private ArrayList<Author> authors;

  /** Constructor for the authorRegistry class. */
  public AuthorRegistry() {
    authors = new ArrayList<>();
  }

  /**
   * Method to add an Author to the ArrayList Authors.
   *
   * @param author input Author
   */
  public void addAuthor(Author author) {
    authors.add(author);
  }

  /**
   * Getter-method, returns the list of authors.
   *
   * @return list of authors
   */
  public ArrayList<Author> getAuthors() {
    return authors;
  }

  /**
   * Search function, searches for a spesific Author by given id.
   *
   * @param id given id
   * @return the Author with matching id, null if not found
   */
  public Author getAuthorById(int id) {
    Author foundAuthor =
        authors.stream().filter(x -> x.getAuthorId() == id).findFirst().orElse(null);
    return foundAuthor;
  }

  /**
   * Delete function, deletes an Author by given id.
   *
   * @param inputId given id
   * @throws ArrayIndexOutOfBoundsException if it cant find the author
   */
  public void deleteById(int inputId) {
    boolean authorExists = authors.stream()
          .anyMatch(x -> x.getAuthorId() == inputId);
    if (authorExists) { 
      authors.removeIf(x -> x.getAuthorId() == inputId);
    } else {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  /** 
   * See all function, prints all authors in the registry. 
   * 
   * @throws ArrayIndexOutOfBoundsException Usually throws when empty
   */
  public void seeAll() throws Exception {
    if (authors.isEmpty()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    System.out.println("List of Authors:");
    authors.forEach(x -> System.out.println("-  " + x.toString()));
    System.out.println("");
  }
}
