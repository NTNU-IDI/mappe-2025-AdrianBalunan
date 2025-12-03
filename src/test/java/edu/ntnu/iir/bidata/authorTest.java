
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test file for the Author-class.
 * 
 * @author Adrian Balunan
 */

public class AuthorTest {
  /** Attribute Tests. */
  @Before
  public void reset() {
    Author.resetNextId();
  }

  @Test
  public void firstAuthorObjectIdShouldBeOne() {
    Author author1 = new Author("Author1");
    assertEquals(1, author1.getAuthorId());
  }

  @Test
  public void secondAuthorObjectIdShouldBeTwo() {
    Author author1 = new Author("Author1");
    Author author2 = new Author("Author2");
    assertNotEquals(2, author1.getAuthorId());
    assertEquals(2, author2.getAuthorId());
  }

  @Test
  public void authorNameShouldBeInput() {
    Author author1 = new Author("Author1");
    assertTrue(author1.getAuthorName().equalsIgnoreCase("Author1"));
  }

  @Test
  public void toStringReturnsCorrect() {
    Author author1 = new Author("Author1");
    assertTrue(author1.toString().equals("Author ID: 1, Name: Author1"));
  }

  @Test
  public void emptyNameShouldGiveExpection() {
    assertThrows(IllegalArgumentException.class, () -> new Author(""));
  }
}
