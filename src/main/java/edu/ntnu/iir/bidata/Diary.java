package edu.ntnu.iir.bidata;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Diary class contains the name of the diary and a list of diary entries.
 * This is the main class for this system.
 * @author Adrian Balunan
 * @version 1.0
 */

public class Diary {
    /**
     * Diary Attributes
     */

    /* Name of the diary */
    private String diaryName;
    /* ArrayList that contain the DiaryEntry object(s) */
    private ArrayList<DiaryEntry> diaryEntries; 

    /**
     * Constructur for the diary name, with name input
     * @param diaryName Input name from the user
     */
    public Diary (String diaryName){
        this.diaryName = diaryName;
        this.diaryEntries = new ArrayList<>();
    }

    /**
     * Constructur for the diary name, no input from the user
     * @param diaryName Input name from the user
     */
    public Diary (){
        this.diaryName = "Sports-Diary";
        this.diaryEntries = new ArrayList<>();
    }

    /**
     * Getter-method that returns DiaryName
     * @return the name of the diary
     */
    public String getDiaryName (){
        return diaryName;
    }

    /**
     * Getter-method that returns the arrayList of DiaryEntry object(s)
     * @return ArrayList of DiaryEntry object(s)
     */
    public ArrayList<DiaryEntry> getEntries (){
        return diaryEntries;
    }
    /*******/
    /**
     * Adds an input entry to the arrayList of DiaryEntry object(s)
     * @param entry
     */
    public void addEntry (DiaryEntry entry){
        diaryEntries.add(entry);
    }
    /**
     * Delete-method that deletes an author by given id, if it exists.
     * @param inputID
     */
    public void deleteEntry (int inputID){
        diaryEntries.removeIf(x -> x.getId() == inputID);
    }
    
    /**
     * Unique print method that prints out all objects in the ArrayList
     * @return Simple string
     */
    public String seeAll(){
        System.out.println("---------------------------");
        System.out.println("");

        diaryEntries.forEach(entry -> {
            System.out.println("#-------#");
            System.out.println(entry.getId() + ": " + entry.getTitle());
            System.out.println(entry.getAuthorName() + ": " + entry.getReleaseDate());
            System.out.println("");
            System.out.println(entry.getContent());
            System.out.println("#-------#");
            System.out.println("");


            try {
                Thread.sleep(200*diaryEntries.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("");
        System.out.println("---------------------------");
        return "Antall innlegg: " + diaryEntries.size();
    }

    
    
    /**
     * Search Algorithm that prints out all entries by a spesific author ID
     * @param authorId
     * @param Authors
     */
    public void seeAllByAuthor(int authorId, authorRegistry Authors){
        System.out.println("---------------------------");
        author foundAuthor = Authors.getAuthorByID(authorId);
        
        if (foundAuthor == null){
            System.out.println("No author found with Id of, " + authorId + ".");
        } else {
            System.out.println("Found author with id, " + authorId + ": " + foundAuthor.getAuthor_name() + ".");
            diaryEntries.stream()
                .filter(x -> x.getAuthorID() == authorId) 
                .forEach(x -> {
                    System.out.println("#-------#");
                    System.out.println(x.getId() + ": " + x.getTitle());
                    System.out.println(x.getAuthorName() + ": " + x.getReleaseDate());
                    System.out.println("");
                    System.out.println(x.getContent());
                    System.out.println("#-------#");
                    System.out.println("");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        }
    }
    /**
     * Method that shows statistics from the authors
     */
    public void showAuthorStatistics(){
        System.out.println("Total number of entries: " + diaryEntries.size());
        // Further statistics can be added here
        List<String> authors = new ArrayList<>();
        List<Integer> authorEntryCounts = new ArrayList<>();

        for (DiaryEntry entry : diaryEntries) {
            String entryAuthor = entry.getAuthorName();
            if (!authors.contains(entryAuthor)) {
                authors.add(entryAuthor);
                authorEntryCounts.add(1);
            } else {
                int index = authors.indexOf(entryAuthor);
                authorEntryCounts.set(index, authorEntryCounts.get(index) + 1);
            }
        }

        System.out.println("----------- Author Statistics------------");
            int autherWidth = 0;
            for (String author : authors) {
                if (author.length() > autherWidth) {
                    autherWidth = author.length();
                }
            }
            int authorWidth = autherWidth + 2;
        System.out.println("| Entries Count | Author        ");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println("|   " + authorEntryCounts.get(i) + " ".repeat(2) + " || " + authors.get(i));
        }
        System.out.println("-----------------------------------------");

 
    }
}
