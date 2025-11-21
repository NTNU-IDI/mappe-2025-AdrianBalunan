package edu.ntnu.iir.bidata;
import java.time.LocalDateTime;

/**
 * This class represents an DiaryEntry, with apporirate attributes and methods
 * @auther Adrian Balunan
 * @verison 1.0
 */

public class DiaryEntry  {
    /**
     * DiaryEntry Attributes
     */

    /* Release date of the entry in LocalDateTime*/
    private LocalDateTime releaseDate;

    /** Accessible and available Id_counter, makes sure that after each occurrence of an DiaryEntry as a unique ID */
    private static int nextId = 1;

    /** Actual unique ID for the DiaryEntry object */
    private final int Id;

    /** The title of the Entry*/
    private String title;

    /** Accosicated author object of the Entry*/
    private author author;

    /** Content */
    private String content;

    /**
     * Contructur for the DiaryEntry class, with input parameters.
     * @param author input author
     * @param title input title
     * @param content input content
     */
    public DiaryEntry(author author, String title, String content){
        this.author = author;
        this.releaseDate =  LocalDateTime.now();
        this.Id = nextId++;
        this.title = title;
        this.content = content;
    }

    /**
     * Getter-methods for getting the release date
     * @return
     */
    public LocalDateTime getReleaseDate (){
        return releaseDate;
    }
    
    /**
     * Getter-method for getting the title
     * @return
     */
    public String getTitle (){
        return title;
    }

    /**
     * Getter-method for getting the content
     * @return
     */
    public String getContent (){
        return content;
    }

    /**
     * Getter-method for getting the author name
     * @return
     */
    public String getAuthorName (){
        return author.getAuthor_name() ; 
    }

    /**
     * Getter-method for getting the ID
     * @return
     */
    public int getId (){
        return Id;
    }

    /**
     * Unique toString method that prints out all attributes of the DiaryEntry object
     * The seeAll method provides a better representation for the user, and therefore not used.
     * @return
     */
    @Override
    public String toString(){
        return "ID: " + Id + ". ReleaseDate: " + releaseDate + ", Title: " + title + ", Author: "+ author + ", Content: " + content;
    }



    
}
