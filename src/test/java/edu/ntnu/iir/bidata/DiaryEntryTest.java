package edu.ntnu.iir.bidata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents an DiaryEntry, with apporirate attributes and methods
 *
 * @auther Adrian Balunan
 * @verison 1.0
 */
public class DiaryEntryTest {
  private DiaryEntry Entry;
  private DiaryEntry EntryNoReleaseDate;
  private Author author1;

  @Before
  public void setup() {
    author1 = new Author("Author1");
    Author.resetNextId();
    DiaryEntry.resetEntryNextId();
  }

  @Test
  public void InputNameShouldBeCorrect() {
    DiaryEntry Entry =
        new DiaryEntry(
            author1, "First Instance", "This is useless text used for testing dada", "23-10-2024");
    assertTrue(Entry.getTitle().equals("First Instance"));
  }

  @Test
  public void AuthorsNameShouldBePrinted() {
    DiaryEntry Entry =
        new DiaryEntry(
            author1, "First Instance", "This is useless text used for testing dada", "23-10-2024");
    assertTrue(Entry.getAuthorName().equals("Author1"));
  }

  @Test
  public void FirstInstanceShouldHaveID1() {
    DiaryEntry Entry =
        new DiaryEntry(
            author1, "First Instance", "This is useless text used for testing dada", "23-10-2024");
    assertEquals(1, Entry.getId());
  }

  @Test
  public void SecondInstanceShouldHaveID2() {
    DiaryEntry Entry =
        new DiaryEntry(
            author1, "First Instance", "This is useless text used for testing dada", "23-10-2024");
    Author author2 = new Author("Author2");
    DiaryEntry Entry2 = new DiaryEntry(author2, "Second", "Useless Text,");
    assertEquals(2, Entry2.getId());
  }

  @Test
  public void TestingToString() {
    DiaryEntry Entry =
        new DiaryEntry(
            author1, "First Instance", "This is useless text used for testing dada", "23-10-2024");
    assertTrue(
        Entry.toString()
            .equals(
                "ID: 1. ReleaseDate: 23-10-2024 (00:00:00), Title: First Instance, Author: Author1, Content: This is useless text used for testing dada"));
  }

  @Test
  public void NoReleaseContructurShouldWork() {
    DiaryEntry EntryNoReleaseDate =
        new DiaryEntry(author1, "First Instance", "This is useless text used for testing dada");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    assertTrue(
        EntryNoReleaseDate.getReleaseDate()
            .substring(0, 10)
            .equals(LocalDate.now().format(formatter)));
  }

  @Test
  public void GettingAuthorIDShouldWork() {
    DiaryEntry Entry1 =
        new DiaryEntry(author1, "First Instance", "This is useless text used for testing dada");
    DiaryEntry Entry2 =
        new DiaryEntry(author1, "Second Instance", "This is useless text used for testing dada");
    assertEquals(1, Entry1.getAuthorID());
    assertEquals(1, Entry2.getAuthorID());
  }

  @Test
  public void GettingAuthorNameShouldWork() {
    DiaryEntry Entry1 =
        new DiaryEntry(author1, "First Instance", "This is useless text used for testing dada");
    DiaryEntry Entry2 =
        new DiaryEntry(author1, "Second Instance", "This is useless text used for testing dada");
    assertTrue(Entry1.getAuthorName().equals("Author1"));
    assertTrue(Entry2.getAuthorName().equals("Author1"));
  }

  @Test
  public void TestingResetEntryNextID() {
    DiaryEntry Entry1 =
        new DiaryEntry(author1, "First Instance", "This is useless text used for testing dada");
    DiaryEntry.resetEntryNextId();
    DiaryEntry Entry2 =
        new DiaryEntry(author1, "Second Instance", "This is useless text used for testing dada");
    assertEquals(1, Entry2.getId());
  }

  @Test
  public void GetContentShouldWork() {
    DiaryEntry Entry1 =
        new DiaryEntry(author1, "First Instance", "This is useless text used for testing dada");
    assertTrue(Entry1.getContent().equals("This is useless text used for testing dada"));
  }
}
