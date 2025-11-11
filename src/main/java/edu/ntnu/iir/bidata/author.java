package edu.ntnu.iir.bidata;

public class author {
    private static int author_nextId = 1;
    private final int author_Id;
    private String author_name;

    public author(String author_name) {
        this.author_Id = author_nextId++;
        this.author_name = author_name;
    }
    public int getAuthor_Id() {
        return author_Id;
    }
    public String getAuthor_name() {
        return author_name;
    }
    @Override
    public String toString() {
        return "Forfatter ID: " + author_Id + ", Navn: " + author_name;
    }
}
