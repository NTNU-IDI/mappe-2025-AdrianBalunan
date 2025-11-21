package edu.ntnu.iir.bidata;

/**
 * Author class contains the name of the author and associates an unqiue ID.
 * @author Adrian Balunan
 * @version 1.0
 */
public class author {
    /**
     * Author attributes:
     */
    /** Accessible and available Id_counter, makes sure that after each occurrence of an author as a unique ID */
    private static int author_nextId = 1;
    
    /** Actual unique ID for the author */
    private final int author_Id;

    /** Name of the author */
    private String author_name;

    /**
     * COnstructor for the author class
     * @param author_name input name of the author from the user
     */
    public author(String author_name) {
        this.author_Id = author_nextId++;
        this.author_name = author_name;
    }

    /**
     * Getter-method, returns the unique ID of the author
     * @return author_Id, the id of the author
     */
    public int getAuthor_Id() {
        return author_Id;
    }
    /**
     * Getter-method, returns the name of the author
     * @return author_name, the name of the author
     */
    public String getAuthor_name() {
        return author_name;
    }
    /**
     * toString method, returns a unique representation of the author.
     * @return A unqiue string with the author's ID and name.
     */
    @Override
    public String toString() {
        return "Forfatter ID: " + author_Id + ", Navn: " + author_name;
    }
}
