package edu.ntnu.iir.bidata;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

public class DiaryTest {
  private AuthorRegistry Authors;
  public Diary diary;

  @Before
  public void setup() {
    Author.resetNextId();
    DiaryEntry.resetEntryNextId();
    diary = new Diary();
    Authors = new AuthorRegistry();

    // Filler content:
    Author author2 = new Author("Adrian Balunan");
    Authors.addAuthor(author2);

    DiaryEntry entry1 =
        new DiaryEntry(
            author2,
            "Første innlegget",
            "Dette er det første innlegget i dagboken min!",
            "23-10-2024");
    diary.addEntry(entry1);

    DiaryEntry entry3 =
        new DiaryEntry(
            author2, "Noe innlegg", "Noe tilfeldig skal stå her eller noe slikt!", "30-10-2024");
    diary.addEntry(entry3);

    DiaryEntry entry4 =
        new DiaryEntry(
            author2,
            "Denne forfatteren",
            "Denne forfatteren har mange innlegg i denne dagboken!",
            "31-10-2024");
    diary.addEntry(entry4);

    Author author3 = new Author("Ola Nordmann");
    Authors.addAuthor(author3);

    DiaryEntry entry2 =
        new DiaryEntry(
            author3,
            "Andre innlegget",
            "Dette er det andre innlegget i dagboken min!",
            "30-10-2025");
    diary.addEntry(entry2);
  }

  @Test
  public void GivingTheDiaryAnNameShouldWork() {
    Diary diaryWithName = new Diary("DiaryName");
    assertTrue(diaryWithName.getDiaryName().equals("DiaryName"));
  }

  @Test
  public void AddingEntryShouldWork() {
    Author Author = new Author("Author");
    DiaryEntry AddEntry = new DiaryEntry(Author, "Title", "Content", "30-10-2025");
    diary.addEntry(AddEntry);
    assertFalse(diary.getEntries().isEmpty());
    assertTrue(diary.getEntries().getLast().getTitle().equals("Title"));
  }

  @Test
  public void DeletingTheFirstEntryWillWork() {
    diary.deleteEntry(1);
    assertFalse(diary.getEntries().getFirst().getTitle().equals("Første innlegget"));
  }

  @Test
  public void TestingPrintOut() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    Author Author = new Author("Author");
    DiaryEntry AddEntry = new DiaryEntry(Author, "Title", "Content", "30-10-2025");
    diary.addEntry(AddEntry);

    System.setOut(new PrintStream(outcontent));
    diary.seeAll();
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("Title"));
    assertTrue(outcontent.toString().contains("Content"));
  }

  @Test
  public void TestingAllByAuthorNOTFOUND() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllByAuthor(4, Authors);
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("No Author found with Id of, 4."));
  }

  @Test
  public void TestingAllByAuthorFOUNDandHasENTRIES() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    Author Author = new Author("Author");
    Authors.addAuthor(Author);
    DiaryEntry AddEntry = new DiaryEntry(Author, "Title", "Content", "30-10-2025");
    diary.addEntry(AddEntry);

    System.setOut(new PrintStream(outcontent));
    diary.seeAllByAuthor(3, Authors);
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("Title"));
    assertTrue(outcontent.toString().contains("Content"));
  }

  @Test
  public void TestingAllByAuthorFOUNDandHasNOENTRIES() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    Author Author = new Author("Author");
    Authors.addAuthor(Author);

    System.setOut(new PrintStream(outcontent));
    diary.seeAllByAuthor(3, Authors);
    System.setOut(orignalOut);

    assertTrue(
        outcontent.toString().contains("Unfortunately, this Author wasent published an entry"));
  }

  @Test
  public void SearchBetweenDatesShouldGiveCorrectEntriesPOSITIVE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllBetweenDates("22-10-2024", "24-10-2024");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("23-10-2024"));
  }

  @Test
  public void SearchBetweenDatesShouldGiveCorrectEntriesNEGATIVE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllBetweenDates("10-10-2024", "12-10-2024");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("No entries between the spesified dates"));
  }

  @Test
  public void SearchByDateShouldGiveCorrectEntriesPOSITIVE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllInDate("23-10-2024");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("23-10-2024"));
  }

  @Test
  public void SearchByDateShouldGiveCorrectEntriesNEGATIVE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllInDate("23-10-1990");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("No entries found in this date"));
  }

  @Test
  public void AuthorStatisticsShouldBeCorrect() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.showAuthorStatistics();
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("|"));
    assertTrue(outcontent.toString().contains("Author"));
    assertTrue(outcontent.toString().contains("Adrian"));
    assertTrue(outcontent.toString().contains("3"));
    assertTrue(outcontent.toString().contains("1"));
  }

  @Test
  public void SearchingEntryByKeyWordShouldGiveCorrectEntriesPOSITIVE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllWithWord("noe");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("noe"));
    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
  }

  @Test
  public void SearchingEntryByKeyWordShouldGiveCorrectEntriesNEGATIVE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllWithWord("blablabla");
    System.setOut(orignalOut);

    assertTrue(
        outcontent.toString().contains("No entries found that contains this word: blablabla"));
  }
}
