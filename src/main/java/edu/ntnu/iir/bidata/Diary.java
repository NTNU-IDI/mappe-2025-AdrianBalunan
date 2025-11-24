package edu.ntnu.iir.bidata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Diary class contains the name of the diary and a list of diary entries.
 * This is the main class for this system.
 * 
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
     * 
     * @param diaryName Input name from the user
     */
    public Diary(String diaryName) {
        this.diaryName = diaryName;
        this.diaryEntries = new ArrayList<>();
    }

    /**
     * Constructur for the diary name, no input from the user
     * 
     * @param diaryName Input name from the user
     */
    public Diary() {
        this.diaryName = "Sports-Diary";
        this.diaryEntries = new ArrayList<>();
    }

    /**
     * Getter-method that returns DiaryName
     * 
     * @return the name of the diary
     */
    public String getDiaryName() {
        return diaryName;
    }

    /**
     * Getter-method that returns the arrayList of DiaryEntry object(s)
     * 
     * @return ArrayList of DiaryEntry object(s)
     */
    public ArrayList<DiaryEntry> getEntries() {
        return diaryEntries;
    }

    /*******/
    /**
     * Adds an input entry to the arrayList of DiaryEntry object(s)
     * 
     * @param entry
     */
    public void addEntry(DiaryEntry entry) {
        diaryEntries.add(entry);
    }

    /**
     * Delete-method that deletes an author by given id, if it exists.
     * 
     * @param inputID
     */
    public void deleteEntry(int inputID) {
        diaryEntries.removeIf(x -> x.getId() == inputID);
    }

    /**
     * Unique print method that prints out all objects in the ArrayList
     * 
     * @return Simple string
     */
    public String seeAll() {
        System.out.println("---------------------------");
        System.out.println("");

        diaryEntries.forEach(entry -> {
            System.out.println("#----" + entry.getId() + "---#");
            System.out.println(entry.getTitle());
            System.out.println(entry.getAuthorName());
            System.out.println(entry.getReleaseDate());
            System.out.println("");
            System.out.println(entry.getContent());
            System.out.println("#-------#");
            System.out.println("");

            try {
                Thread.sleep(200 * diaryEntries.size());
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
     * 
     * @param authorId
     * @param Authors
     */
    public void seeAllByAuthor(int authorId, authorRegistry Authors) {
        author foundAuthor = Authors.getAuthorByID(authorId);

        if (foundAuthor == null) {
            System.out.println("No author found with Id of, " + authorId + ".");
        } else {
            System.out.println("Found author with id, " + authorId + ": " + foundAuthor.getAuthor_name() + ".");
            List<DiaryEntry> filiteredAuthor = diaryEntries.stream()
                    .filter(x -> x.getAuthorID() == authorId)
                    .toList();
            if (filiteredAuthor.isEmpty()){
                System.out.println("Unfortunately, this author wasent published an entry");
            } else {
                System.out.println("# Entries by: " + foundAuthor.getAuthor_name());
                Printout(filiteredAuthor);
            }
        }
        ;
    }

    /**
     * Search function that searches for entries between given DATES.
     * 
     * @param startdate Start date
     * @param endingDate Ending date 
     */
    public void seeAllBetweenDates(String startDate, String endingDate) {
        List<DiaryEntry> filitedentries = diaryEntries.stream()
                .filter(e -> e.getReleaseDate().substring(0, 10).compareTo(startDate) >= 0
                        && e.getReleaseDate().substring(0, 10).compareTo(endingDate) <= 0)
                .toList();
        if (filitedentries.isEmpty()) {
            System.out.println("No entries between the spesified dates");
        } else {
            System.out.println("# Entries between: " + startDate + " <-> " + endingDate + " #");
            Printout(filitedentries);
        }
    }

    /**
     * Search function that shows all entries in one spesified date
     * 
     * @param Date input date from user
     */
    public void seeAllInDate(String Date) {
        List<DiaryEntry> filteredDates = diaryEntries.stream()
                .filter(e -> e.getReleaseDate().substring(0, 10).equalsIgnoreCase(Date))
                .toList();
        if (filteredDates.isEmpty()) {
            System.out.println("No entries found in this date");
        } else {
            System.out.println("# Entries found for: " + Date + " #");
            Printout(filteredDates);
        }
    }

    /**
     * Search function that shows all entries containing a spesific word
     * 
     * @param word input word from user
     */
    public void seeAllWithWord(String word) {
        List<DiaryEntry> filteredWord = diaryEntries.stream()
                .filter(x -> x.getContent().contains(word))
                .toList();
        if (filteredWord.isEmpty()) {
            System.out.println("No entries found that contains this word: " + word);
        } else {
            System.out.println("# All entries with content that includes this word: " + word + " #");
            Printout(filteredWord);
        }
    }

    /**
     * Method that shows statistics from the authors
     */
    public void showAuthorStatistics() {
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
        System.out.println("| Entries Count | Author        ");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println("|   " + authorEntryCounts.get(i) + " ".repeat(2) + " || " + authors.get(i));
        }
        System.out.println("-----------------------------------------");

    }

    /**
     * Helper functions that takes a (usually) streamlined list and prints the contents (usually DiaryEntry(s)) out.
     * Used in the seeAll-- functions.
     * 
     * @param List Filiterd List
     */
    private static void Printout(List<DiaryEntry> List){
        System.out.println("---------------------------");
        System.out.println("");
        List.forEach(entry -> {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("#----" + entry.getId() + "---#");
            System.out.println(entry.getTitle());
            System.out.println(entry.getAuthorName());
            System.out.println(entry.getReleaseDate());
            System.out.println("");
            System.out.println(entry.getContent());
            System.out.println("#-------#");
            System.out.println("");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("");
        System.out.println("---------------------------");
    }
}
