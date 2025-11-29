package edu.ntnu.iir.bidata;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class authorTest {
    /**
     * Attribute Tests
     */
    @Before
    public void reset(){
        author.resetAuthorNextID();
    }
    @Test
    public void FirstAuthorObjectIDShouldBeOne(){
        author author1 = new author("Author1");
        assertEquals(1, author1.getAuthor_Id());
    }
    @Test
    public void SecondAuthorObjectIDShouldBeTwo(){
        author author1 = new author("Author1");
        author author2 = new author("Author2");
        assertEquals(2, author2.getAuthor_Id());
    }
    @Test
    public void AuthorNameShouldBeInput(){
        author author1 = new author("Author1");
        assertTrue(author1.getAuthor_name().equalsIgnoreCase("Author1"));
    }
    @Test 
    public void ToStringReturnsCorrect(){
        author author1 = new author("Author1");
        assertTrue(author1.toString().equals("Forfatter ID: 1, Navn: Author1"));
    }
}
