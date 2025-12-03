import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

/**
 * Test file for the AuthorRegistry-class.
 * 
 * @author Adrian Balunan
 */
public class AuthorRegistryTest {
  private static AuthorRegistry Authors;

  /**
   * Create a AuthorRegistry object to test onwards.
   */
  @Before
  public void setup() {
    Authors = new AuthorRegistry();
    Author.resetNextId();
  }

  @Test
  public void addingAuthorShouldWork() {
    Author author1 = new Author("Author1");
    Authors.addAuthor(author1);
    assertFalse(Authors.getAuthors().isEmpty());
    assertTrue(Authors.getAuthors().get(0).getAuthorName().equals("Author1"));
  }

  @Test
  public void givingAuthorIdShouldReturnTheCorrectAuthor() {
    Author author1 = new Author("Author1"); // ID: 1
    Authors.addAuthor(author1);
    Author author2 = new Author("Author2"); // ID: 2
    Authors.addAuthor(author2);
    Author author3 = new Author("Author3"); // ID: 3
    Authors.addAuthor(author3);
    assertEquals(2, Authors.getAuthorById(2).getAuthorId());
  }

  @Test
  public void searchingForAnDeletedItemShouldReturnAnExpection() {
    Author author1 = new Author("Author1"); 
    Authors.addAuthor(author1);
    Author author2 = new Author("Author2"); 
    Authors.addAuthor(author2);
    Author author3 = new Author("Author3"); 
    Authors.addAuthor(author3);
    Authors.deleteById(2);
    assertThrows(
        NullPointerException.class,
        () -> Authors.getAuthorById(2).getAuthorId());
    assertEquals(author3, Authors.getAuthorById(3));
  }

  @Test
  public void printingOutShouldCorrespond() throws Exception {
    Author author1 = new Author("Author1"); 
    Authors.addAuthor(author1);
    Author author2 = new Author("Author2"); 
    Authors.addAuthor(author2);
    Author author3 = new Author("Author3"); 
    Authors.addAuthor(author3);

    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    Authors.seeAll();
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("3"));
    assertTrue(outcontent.toString().contains("List of Authors:"));
  }

  @Test 
  public void deletingAnNonExistingAuthorShouldGiveException() {
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> Authors.deleteById(1));
  }

  @Test 
  public void seeAllingAnEmptyArrayShouldGiveException() {
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> Authors.seeAll());
  }
}
