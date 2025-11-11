package edu.ntnu.iir.bidata;

import java.util.ArrayList;

public class authorRegistry {
    private ArrayList<author> Authors;

    public authorRegistry(){
        Authors = new ArrayList<>();
    }

    public void addAuthor(author f) {
        Authors.add(f);
    }

    public ArrayList<author> getAuthors() {
        return Authors;
    }

    public author getAuthorByID(int id){
        author foundAuthor = Authors.stream()
            .filter(x -> x.getAuthor_Id() == id)
            .findFirst()
            .orElse(null);
        return foundAuthor;
    }
    public void DeleteByID(int inputId){
        Authors.removeIf(x -> x.getAuthor_Id() == inputId);
    }
    public void seeAll() {
        System.out.println("List of Authors:");
        Authors.forEach(x -> System.out.println("-  " + x.toString()));
        System.out.println("");

    }
}
