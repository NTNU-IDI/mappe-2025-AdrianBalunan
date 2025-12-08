
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
  private AuthorRegistry authors;

  /**
   * Setup nessasary variables too write code with.
   */
  @Before
  public void setup() {
    Author.resetNextId();
    DiaryEntry.resetEntryNextId();
    author1 = new Author("Author1");

    entry = new DiaryEntry(
        author1,
        "First Instance",
        "This is useless text used for testing dada",
        "Pushups",
        "23-10-2024");
    entryNoReleaseDate = new DiaryEntry(
        author1,
        "First Instance",
        "This is useless text used for testing dada",
        "Pushups 3x15");
    authors = new AuthorRegistry();
  }

  @Test
  public void inputNameShouldBeCorrect() {
    assertTrue(entry.getTitle().equals("First Instance"));
  }

  @Test
  public void authorsNameShouldBePrinted() {
    assertTrue(entry.getAuthorObject().getAuthorName().equals("Author1"));
  }

  @Test
  public void firstInstanceShouldHaveId1() {
    assertEquals(1, entry.getId());
  }

  @Test
  public void thirdInstanceShouldHaveId3() {
    Author author2 = new Author("Author2");
    DiaryEntry entry2 = new DiaryEntry(
        author2,
        "Second",
        "Useless Text,",
        "Pushups 3x15",
        "01-01-2025");
    assertEquals(3, entry2.getId());
  }

  @Test
  public void userTailoredContructurShouldGiveExceptionWhenAuthorDoesntExist() {
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> new DiaryEntry(authors, author1, "Title1", "Content", "Workout"));
  }

  @Test
  public void userTailoredContructurShouldGiveExceptionWhenEmptyTitle() {
    authors.addAuthor(author1);
    assertThrows(IllegalArgumentException.class, () -> 
        new DiaryEntry(authors, author1, "", "Content", "Workout"));
    assertThrows(IllegalArgumentException.class, () -> 
        new DiaryEntry(authors, author1, null, "Content", "Workout"));
  }

  @Test
  public void userTailoredContructurShouldGiveExceptionWhenEmptyContent() {
    authors.addAuthor(author1);
    assertThrows(IllegalArgumentException.class, () -> 
        new DiaryEntry(authors, author1, "Title1", "", "Workout"));
    assertThrows(IllegalArgumentException.class, () -> 
        new DiaryEntry(authors, author1, "Title1", null, "Workout"));
  }

  @Test
  public void userTailoredContructurShouldGiveExceptionWhenEmptyWorkout() {
    authors.addAuthor(author1);
    assertThrows(IllegalArgumentException.class, () -> 
        new DiaryEntry(authors, author1, "Title1", "Content", ""));
    assertThrows(IllegalArgumentException.class, () -> 
        new DiaryEntry(authors, author1, "Title1", "Content", null));
  }

  @Test
  public void userTailoredContructurShouldGiveWork() {
    authors.addAuthor(author1);
    DiaryEntry entry2 = new DiaryEntry(authors, author1, "Title1", "Content", "Workout");
    assertTrue(entry2.getTitle().equals("Title1"));
    assertTrue(entry2.getContent().equals("Content"));
    assertTrue(entry2.getWorkout().equals("Workout"));
  }

  @Test
  public void testingToString() {
    assertTrue(
        entry.toString()
            .contains(
                "ID: 1. ReleaseDate: 23-10-2024 (00:00:00), Title: First Instance"));
  }

  @Test
  public void noReleaseContructurShouldWork() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    assertTrue(
        entryNoReleaseDate.getReleaseDate()
            .substring(0, 10)
            .equals(LocalDate.now().format(formatter)));
  }

  @Test
  public void gettingAuthorIdShouldWork() {
    DiaryEntry entry1 = new DiaryEntry(
        author1,
        "First Instance",
        "This is useless text used for testing dada, ",
        "Pushups 3x15",
        "01-01-2025");
    DiaryEntry entry2 = new DiaryEntry(
        author1,
        "Second Instance",
        "This is useless text used for testing dada, ",
        "Pushups 3x15",
        "01-01-2025");
    assertEquals(1, entry1.getAuthorObject().getAuthorId());
    assertEquals(1, entry2.getAuthorObject().getAuthorId());
  }

  @Test
  public void gettingAuthorNameShouldWork() {
    assertTrue(entry.getAuthorObject().getAuthorName().equals("Author1"));
  }

  @Test
  public void testingResetEntryNextId() {
    DiaryEntry.resetEntryNextId();
    DiaryEntry entry2 = new DiaryEntry(
        author1,
        "Second Instance",
        "This is useless text used for testing dada",
        "Pushups 3x15",
        "01-01-2025");
    assertEquals(1, entry2.getId());
  }

  @Test
  public void getContentShouldWork() {
    assertTrue(entry.getContent().equals("This is useless text used for testing dada"));
  }

  @Test
  public void getReleaseDateShouldWork() {
    assertTrue(entry.getReleaseDate().equals("23-10-2024 (00:00:00)"));
  }

  @Test
  public void getWorkoutShouldWork() {
    assertTrue(entry.getWorkout().equals("Pushups"));
  }

  @Test
  public void setTitleShouldWork() {
    entry.setTitle("New Title");
    assertTrue(entry.getTitle().equals("New Title"));
  }

  @Test
  public void setTitleShouldNotWorkWhenEmpty() {
    assertThrows(IllegalArgumentException.class, () -> entry.setTitle(""));
  }

  @Test
  public void setContentShouldWork() {
    entry.setContent("New Content");
    assertTrue(entry.getContent().equals("New Content"));
  }

  @Test
  public void setContentShouldNotWorkWhenEmpty() {
    assertThrows(IllegalArgumentException.class, () -> entry.setContent(""));
  }

  @Test
  public void setWorkoutShouldWork() {
    entry.setWorkout("New Workout");
    assertTrue(entry.getWorkout().equals("New Workout"));
  }

  @Test
  public void setWorkoutShouldNotWorkWhenEmpty() {
    assertThrows(IllegalArgumentException.class, () -> entry.setWorkout(""));
  }

  @Test
  public void getAuthorObjectWorks() {
    assertEquals(author1, entry.getAuthorObject());
  }
}
