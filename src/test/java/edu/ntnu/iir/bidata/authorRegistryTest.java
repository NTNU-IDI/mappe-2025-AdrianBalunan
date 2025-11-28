import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import edu.ntnu.iir.bidata.author;
import edu.ntnu.iir.bidata.authorRegistry;

public class authorRegistryTest {
    private static authorRegistry Authors;

    @Before
    public void setup(){
        Authors = new authorRegistry();
        author.resetAuthorNextID();
    }
    @Test
    public void AddingAuthorShouldWork(){
        author author1 = new author("Author1");
        Authors.addAuthor(author1);
        assertFalse(Authors.getAuthors().isEmpty());
        assertTrue(Authors.getAuthors().get(0).getAuthor_name() == "Author1");
    }
    @Test 
    public void GivingAuthorIdShouldReturnTheCorrectAuthor(){
        author author1 = new author("Author1"); // ID: 1
        Authors.addAuthor(author1);
        author author2 = new author("Author2"); // ID: 2
        Authors.addAuthor(author2);
        author author3 = new author("Author3"); // ID: 3
        Authors.addAuthor(author3);
        assertTrue(Authors.getAuthorByID(2).getAuthor_Id() == 2);
    }
    @Test 
    public void SearchingForADeletedItemShouldReturnAnExpection(){
        author author1 = new author("Author1"); // ID: 1
        Authors.addAuthor(author1);
        author author2 = new author("Author2"); // ID: 2
        Authors.addAuthor(author2);
        author author3 = new author("Author3"); // ID: 3
        Authors.addAuthor(author3);
        Authors.DeleteByID(2);
        assertThrows(NullPointerException.class, () -> {
            Authors.getAuthorByID(2).getAuthor_Id();
        });
        assertTrue(Authors.getAuthorByID(3).getAuthor_Id() == 3);
    }
    @Test
    public void PrintingOutShouldCorrespond(){
        author author1 = new author("Author1"); // ID: 1
        Authors.addAuthor(author1);
        author author2 = new author("Author2"); // ID: 2
        Authors.addAuthor(author2);
        author author3 = new author("Author3"); // ID: 3
        Authors.addAuthor(author3);
        
        PrintStream orignalOut = System.out;
        ByteArrayOutputStream outcontent = new ByteArrayOutputStream();
        
        System.setOut(new PrintStream(outcontent));
        Authors.seeAll();
        System.setOut(orignalOut);

        assertTrue(outcontent.toString().contains("3"));
        assertTrue(outcontent.toString().contains("List of Authors:"));
    }
}
