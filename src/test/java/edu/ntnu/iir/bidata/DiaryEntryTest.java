
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents an DiaryEntry, with apporirate attributes and methods.
 *
 * @auther Adrian Balunan
 * @verison 1.0
 */
public class DiaryEntryTest {
  private DiaryEntry entry;
  private DiaryEntry entryNoReleaseDate;
  private Author author1;

  /**
   * Setup nessasary variables too write code with.
   */
  @Before
  public void setup() {
    Author.resetNextId();
    DiaryEntry.resetEntryNextId();
    author1 = new Author("Author1");

  }

  @Test
  public void inputNameShouldBeCorrect() {
    DiaryEntry entry =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada", 
            "Pushups",
            "23-10-2024");
    assertTrue(entry.getTitle().equals("First Instance"));
  }

  @Test
  public void authorsNameShouldBePrinted() {
    DiaryEntry entry =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada", 
            "pushups",
            "23-10-2024");
    assertTrue(entry.getAuthorName().equals("Author1"));
  }

  @Test
  public void firstInstanceShouldHaveId1() {
    DiaryEntry entry =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada", 
            "pushups",
            "23-10-2024");
    assertEquals(1, entry.getId());
  }

  @Test
  public void secondInstanceShouldHaveId2() {
    DiaryEntry entry =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada", 
            "pushups",
            "23-10-2024");
    Author author2 = new Author("Author2");
    DiaryEntry entry2 = new DiaryEntry(author2, "Second", "Useless Text,", "Pushups 3x15");
    assertEquals(2, entry2.getId());
  }

  @Test
  public void testingToString() {
    DiaryEntry entry =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada", 
            "Pushups 3x15", 
            "23-10-2024");
    assertTrue(
        entry.toString()
            .contains(
                "ID: 1. ReleaseDate: 23-10-2024 (00:00:00), Title: First Instance"));
  }

  @Test
  public void noReleaseContructurShouldWork() {
    DiaryEntry entryNoReleaseDate =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada",
            "Pushups 3x15");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    assertTrue(
        entryNoReleaseDate.getReleaseDate()
            .substring(0, 10)
            .equals(LocalDate.now().format(formatter)));
  }

  @Test
  public void gettingAuthorIdShouldWork() {
    DiaryEntry entry1 =
        new DiaryEntry(
              author1, 
              "First Instance", 
              "This is useless text used for testing dada, ", 
              "Pushups 3x15\"");
    DiaryEntry entry2 =
        new DiaryEntry(
              author1, 
              "Second Instance", 
              "This is useless text used for testing dada, ", 
              "Pushups 3x15\"");
    assertEquals(1, entry1.getAuthorId());
    assertEquals(1, entry2.getAuthorId());
  }

  @Test
  public void gettingAuthorNameShouldWork() {
    DiaryEntry entry1 =
        new DiaryEntry(
            author1, 
            "First Instance", 
            "This is useless text used for testing dada", 
            "Pushups 3x15");
    DiaryEntry entry2 =
        new DiaryEntry(
              author1, 
              "Second Instance", 
              "This is useless text used for testing dada", 
              "Pushups 3x15");
    assertTrue(entry1.getAuthorName().equals("Author1"));
    assertTrue(entry2.getAuthorName().equals("Author1"));
  }

  @Test
  public void testingResetEntryNextId() {
    DiaryEntry entry1 =
        new DiaryEntry(
              author1, 
              "First Instance", 
              "This is useless text used for testing dada", 
              "Push-ups 3x15");
    DiaryEntry.resetEntryNextId();
    DiaryEntry entry2 =
        new DiaryEntry(
              author1, 
              "Second Instance", 
              "This is useless text used for testing dada", 
              "Pushups 3x15");
    assertEquals(1, entry2.getId());
  }

  @Test
  public void getContentShouldWork() {
    DiaryEntry entry1 =
        new DiaryEntry(
              author1, 
              "First Instance", 
              "This is useless text used for testing dada", 
              "Pushups 3x15");
    assertTrue(entry1.getContent().equals("This is useless text used for testing dada"));
  }
}
