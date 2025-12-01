
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Diary class contains the name of the diary and a list of diary entries. This is the main class
 * for this system.
 *
 * @Author Adrian Balunan
 * @version 1.0
 */
public class Diary {
  /** Diary Attributes. */

  /* Name of the diary */
  private String diaryName;

  /* ArrayList that contain the DiaryEntry object(s) */
  private ArrayList<DiaryEntry> diaryEntries;

  /**
   * Constructur for the diary name, with name input.
   *
   * @param diaryName Input name from the user
   */
  public Diary(String diaryName) {
    this.diaryName = diaryName;
    this.diaryEntries = new ArrayList<>();
  }

  /**
   * Constructur for the diary name, no input from the user.
   *
   */
  public Diary() {
    this.diaryName = "Sports-Diary";
    this.diaryEntries = new ArrayList<>();
  }

  /**
   * Getter-method that returns DiaryName.
   *
   * @return the name of the diary
   */
  public String getDiaryName() {
    return diaryName;
  }

  /**
   * Getter-method that returns the arrayList of DiaryEntry object(s).
   *
   * @return ArrayList of DiaryEntry object(s)
   */
  public ArrayList<DiaryEntry> getEntries() {
    return diaryEntries;
  }

  /**
   * Adds an input entry to the arrayList of DiaryEntry object(s).
   *
   * @param entry Entry
   */
  public void addEntry(DiaryEntry entry) {
    diaryEntries.add(entry);
  }

  /**
   * Delete-method that deletes an Author by given id, if it exists.
   *
   * @param inputId Input of the Id
   */
  public void deleteEntry(int inputId) {
    diaryEntries.removeIf(x -> x.getId() == inputId);
  }

  /**
   * Unique print method that prints out all objects in the ArrayList.
   *
   * @return Simple string
   */
  public String seeAll() {
    System.out.println("---------------------------");
    System.out.println("");

    diaryEntries.forEach(
        entry -> {
          System.out.println("#----" + entry.getId() + "---#");
          System.out.println(entry.getTitle());
          System.out.println(entry.getAuthorName() + "(" + entry.getAuthorId() + ")");
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
   * Search Algorithm that prints out all entries by a spesific Author ID.
   *
   * @param authorId Id of the author
   * @param authors AuthorRegistry object
   */
  public void seeAllByAuthor(int authorId, AuthorRegistry authors) {

    Author foundAuthor = authors.getAuthorById(authorId);
    if (foundAuthor == null) {
      System.out.println("No Author found with Id of, " + authorId + ".");
    } else {
      System.out.println(
          "Found Author with id, " + authorId + ": " + foundAuthor.getAuthorName() + ".");
      List<DiaryEntry> filiteredAuthor =
          diaryEntries.stream().filter(x -> x.getAuthorId() == authorId).toList();
      if (filiteredAuthor.isEmpty()) {
        System.out.println("Unfortunately, this Author wasent published an entry");
      } else {
        System.out.println("# Entries by: " + foundAuthor.getAuthorName());
        printout(filiteredAuthor);
      }
    }
    ;
  }

  /**
   * Search function that searches for entries between given DATES.
   *
   * @param inputStart Start date
   * @param inputEnd Ending date
   */
  public void seeAllBetweenDates(String inputStart, String inputEnd) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate start = LocalDate.parse(inputStart, formatter);
    LocalDate end = LocalDate.parse(inputEnd, formatter);

    List<DiaryEntry> filitedentries =
        diaryEntries.stream()
            .filter(
                e -> {
                  LocalDate date = LocalDate.parse(e.getReleaseDate().substring(0, 10), formatter);
                  return !date.isBefore(start) && !date.isAfter(end);
                })
            .toList();
    if (filitedentries.isEmpty()) {
      System.out.println("No entries between the spesified dates");
    } else {
      System.out.println("# Entries between: " + start + " <-> " + end + " #");
      printout(filitedentries);
    }
  }

  /**
   * Search function that shows all entries in one spesified date.
   *
   * @param date input date from user
   */
  public void seeAllInDate(String date) {
    List<DiaryEntry> filteredDates =
        diaryEntries.stream()
            .filter(e -> e.getReleaseDate().substring(0, 10).equalsIgnoreCase(date))
            .toList();
    if (filteredDates.isEmpty()) {
      System.out.println("No entries found in this date");
    } else {
      System.out.println("# Entries found for: " + date + " #");
      printout(filteredDates);
    }
  }

  /**
   * Search function that shows all entries containing a spesific word.
   *
   * @param word input word from user
   */
  public void seeAllWithWord(String word) {
    List<DiaryEntry> filteredWord =
        diaryEntries.stream()
            .filter(x -> x.getContent().toLowerCase().contains(word.toLowerCase()))
            .toList();
    if (filteredWord.isEmpty()) {
      System.out.println("No entries found that contains this word: " + word);
    } else {
      System.out.println("# All entries with content that includes this word: " + word + " #");
      printout(filteredWord);
    }
  }

  /** Method that shows statistics from the authors. */
  public void showAuthorStatistics() {
    System.out.println("Total number of entries: " + diaryEntries.size());
    // Further statistics can be added here
    List<Author> authors = new ArrayList<>();
    List<Integer> authorEntryCounts = new ArrayList<>();

    for (DiaryEntry entry : diaryEntries) {
      Author entryAuthor = entry.getAuthorObject();
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
      System.out.println(
          "|   "
              + authorEntryCounts.get(i)
              + " ".repeat(2)
              + " || (ID: "
              + authors.get(i).getAuthorId()
              + "): "
              + authors.get(i).getAuthorName());
    }
    System.out.println("-----------------------------------------");
  }

  /**
   * Helper functions that takes a (usually) streamlined list and prints the contents (usually
   * DiaryEntry(s)) out. Used in the seeAll-- functions.
   *
   * @param List Filiterd List
   */
  private static void printout(List<DiaryEntry> list) {
    System.out.println("---------------------------");
    System.out.println("");
    list.forEach(
        entry -> {
          try {
            Thread.sleep(200);
          } catch (Exception e) {
            e.printStackTrace();
          }

          System.out.println("#---" + entry.getId() + "---#");
          System.out.println(entry.getTitle());
          System.out.println(entry.getAuthorName());
          System.out.println(entry.getReleaseDate());
          System.out.println("");
          System.out.println(entry.getContent());
          System.out.println("#-------#");
          System.out.println("");

          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });
    System.out.println("");
    System.out.println("---------------------------");
  }
}
