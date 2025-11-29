package edu.ntnu.iir.bidata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class authorTest {
  /** Attribute Tests */
  @Before
  public void reset() {
    Author.resetNextId();
  }

  @Test
  public void FirstAuthorObjectIDShouldBeOne() {
    Author author1 = new Author("Author1");
    assertEquals(1, author1.getAuthor_Id());
  }

  @Test
  public void SecondAuthorObjectIDShouldBeTwo() {
    Author author1 = new Author("Author1");
    Author author2 = new Author("Author2");
    assertEquals(2, author2.getAuthor_Id());
  }

  @Test
  public void AuthorNameShouldBeInput() {
    Author author1 = new Author("Author1");
    assertTrue(author1.getAuthor_name().equalsIgnoreCase("Author1"));
  }

  @Test
  public void ToStringReturnsCorrect() {
    Author author1 = new Author("Author1");
    assertTrue(author1.toString().equals("Forfatter ID: 1, Navn: Author1"));
  }
}
