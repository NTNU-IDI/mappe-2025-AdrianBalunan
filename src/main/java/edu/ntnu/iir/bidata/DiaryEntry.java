package edu.ntnu.iir.bidata;
import java.time.LocalDateTime;

public class DiaryEntry {
    /**
     * Klassen representerer et dagbok-innlegg
     * @auther Adrian Balunan
     * @verison 1.0
     */
    private LocalDateTime releaseDate;
    private String title;
    private String author;
    private String content;

    public DiaryEntry(String title, String content, String author){
        this.releaseDate =  LocalDateTime.now();
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


    @Override
    public String toString(){
        return "RelaseDate: " + releaseDate + ", Title: " + title + ", Author: "+ author + ", Content: " + content;
    }



    
}
