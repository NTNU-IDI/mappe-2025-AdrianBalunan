package edu.ntnu.iir.bidata;
import java.time.LocalDateTime;
import java.util.Random;

public class DiaryEntry {
    /**
     * Klassen representerer et dagbok-innlegg
     * @auther Adrian Balunan
     * @verison 1.0
     */
    private LocalDateTime releaseDate;
    private static int nextId = 1;
    private final int Id;
    private String title;
    private String author;
    private String content;

    public DiaryEntry(String title, String content, String author){
        this.releaseDate =  LocalDateTime.now();
        this.Id = nextId++;
        this.title = title;
        this.content = content;
        this.author = author;
    }
    // Getter
    public LocalDateTime getReleaseDate (){
        return releaseDate;
    }
    public String getTitle (){
        return title;
    }
    public String getContent (){
        return content;
    }
    public String getAuthor (){
        return author;
    }
    public int getId (){
        return Id;
    }


    @Override
    public String toString(){
        return "ID: " + Id + ". ReleaseDate: " + releaseDate + ", Title: " + title + ", Author: "+ author + ", Content: " + content;
    }



    
}
