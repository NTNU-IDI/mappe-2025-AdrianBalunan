package edu.ntnu.iir.bidata;
import java.util.ArrayList;

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
                Thread.sleep(1000*diaryEntries.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("");
        System.out.println("---------------------------");
        return "Antall innlegg: " + diaryEntries.size();
    }
    /**
     * Delete-method that deletes an author by given id, if it exists.
     * @param inputID
     */
    public void deleteEntry (int inputID){
        diaryEntries.removeIf(x -> x.getId() == inputID);
    }

}
