
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

/**
 * Test file for the Diary-class.
 * 
 * @author Adrian Balunan
 */

public class DiaryTest {
  private AuthorRegistry authors;
  public Diary diary;

  /**
   * Nessesary variables each test needs.
   */
  @Before
  public void setup() {
    Author.resetNextId();
    DiaryEntry.resetEntryNextId();
    diary = new Diary();
    authors = new AuthorRegistry();

    // Filler content:
    Author author2 = new Author("Adrian Balunan");
    authors.addAuthor(author2);

    DiaryEntry entry1 = new DiaryEntry(
        author2,
        "Første innlegget",
        "Dette er det første innlegget i dagboken min!",
        "Push ups",
        "23-10-2024");
    diary.addEntry(entry1);

    DiaryEntry entry3 = new DiaryEntry(
        author2,
        "Noe innlegg",
        "Noe tilfeldig skal stå her eller noe slikt!",
        "Pushups",
        "30-10-2024");
    diary.addEntry(entry3);

    DiaryEntry entry4 = new DiaryEntry(
        author2,
        "Denne forfatteren",
        "Denne forfatteren har mange innlegg i denne dagboken!",
        "pushups",
        "31-10-2024");
    diary.addEntry(entry4);

    Author author3 = new Author("Ola Nordmann");
    authors.addAuthor(author3);

    DiaryEntry entry2 = new DiaryEntry(
        author3,
        "Andre innlegget",
        "Dette er det andre innlegget i dagboken min!",
        "pushups",
        "30-10-2025");
    diary.addEntry(entry2);
  }

  @Test
  public void givingTheDiaryAnNameShouldWork() {
    Diary diaryWithName = new Diary("DiaryName");
    assertTrue(diaryWithName.getDiaryName().equals("DiaryName"));
  }

  @Test
  public void addingEntryShouldWork() {
    Author author = new Author("Author");
    DiaryEntry addEntry = new DiaryEntry(author, "Title", "Content", "pushups", "30-10-2025");
    diary.addEntry(addEntry);
    assertFalse(diary.getEntries().isEmpty());
    assertTrue(diary.getEntries().getLast().getTitle().equals("Title"));
  }

  @Test
  public void deletingTheFirstEntryWillWork() {
    diary.deleteEntry(1);
    assertFalse(diary.getEntries().getFirst().getTitle().equals("Første innlegget"));
  }

  @Test
  public void seeAllingAnEmptyArrayShouldGiveException() {
    Diary diary2 = new Diary();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary2.seeAll());
  }

  @Test
  public void testingPrintOut() {
    final PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAll();
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("Title"));
    assertTrue(outcontent.toString().contains("innlegget"));
  }

  @Test
  public void testingAllByAuthorNoTfOuNd() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    assertThrows(IllegalArgumentException.class, () -> diary.seeAllByAuthor(4, authors));
    System.setOut(orignalOut);
  }

  @Test
  public void testingAllByAuthorFoUnDandHasEnTrIeS() {
    final PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllByAuthor(1, authors);
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("#-------#"));
    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("Første innlegget"));
    assertTrue(outcontent.toString().contains("Denne forfatteren"));
  }

  @Test
  public void testingAllByAuthorFoUnDandHasNoEnTrIeS() {
    final PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    Author author = new Author("Author");
    authors.addAuthor(author);

    System.setOut(new PrintStream(outcontent));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary.seeAllByAuthor(3, authors));
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("Found Author with id"));
  }

  @Test
  public void searchBetweenDatesWithBadInputGivesExpection() throws IllegalAccessException {
    assertThrows(IllegalArgumentException.class, () -> 
        diary.seeAllBetweenDates("10-10-20244", "13-32-2024"));
    assertThrows(IllegalArgumentException.class, () -> 
        diary.seeAllBetweenDates("10-10-2024", "13-12-20244"));
    assertThrows(IllegalArgumentException.class, () -> 
        diary.seeAllBetweenDates("10010-2024", "13-12-2024"));
    assertThrows(IllegalArgumentException.class, () ->
        diary.seeAllBetweenDates("10-10-2024", "13012-2024"));
    assertThrows(IllegalArgumentException.class, () -> 
        diary.seeAllBetweenDates("10-1002024", "13-12-2024"));
    assertThrows(IllegalArgumentException.class, () -> 
        diary.seeAllBetweenDates("10-10-2024", "13-1202024"));
  }

  @Test
  public void searchBetweenDatesWithBadIntervallGivesExpection() throws IllegalAccessException {
    assertThrows(IllegalAccessException.class, () -> 
        diary.seeAllBetweenDates("31-12-2024", "01-01-2024"));
  }

  @Test
  public void searchBetweenDatesShouldGiveCorrectEntriesPoSiTiVe() throws IllegalAccessException {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllBetweenDates("22-10-2024", "24-10-2024");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("23-10-2024"));
  }

  @Test
  public void searchBetweenDatesShouldGiveCorrectEntriesNeGaTiVe() throws IllegalAccessException {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));

    assertThrows(ArrayIndexOutOfBoundsException.class, () -> 
        diary.seeAllBetweenDates("10-10-2024", "12-10-2024"));
    System.setOut(orignalOut);

  }

  @Test
  public void searchByDateWithBadInputGivesExpection() throws IllegalAccessException {
    assertThrows(IllegalArgumentException.class, () -> diary.seeAllInDate("1202202231322"));
    assertThrows(IllegalArgumentException.class, () -> diary.seeAllInDate("1201202025"));
    assertThrows(IllegalArgumentException.class, () -> diary.seeAllInDate("12-1202025"));
  }

  @Test
  public void searchByDateShouldGiveCorrectEntriesPoSiTiVe() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllInDate("23-10-2024");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("---------------------------"));
    assertTrue(outcontent.toString().contains("23-10-2024"));
  }

  @Test
  public void searchByDateShouldGiveCorrectEntriesNeGatIvE() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary.seeAllInDate("23-10-1990"));
    System.setOut(orignalOut);
  }

  @Test
  public void searchByDateShouldGiveExpectionWhenNothing() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();
    Diary diary2 = new Diary();

    System.setOut(new PrintStream(outcontent));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary2.seeAllInDate("23-10-1990"));
    System.setOut(orignalOut);
  }

  @Test
  public void authorStatisticsShouldBeCorrect() {
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
  public void authorStatisticsButEmptyShouldGiveExecption() {
    Diary diary2 = new Diary();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary2.showAuthorStatistics());
  }

  @Test
  public void searchingEntryByKeyWordShouldGiveCorrectEntriesPoSiTiVe() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllWithWord("noe");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("noe"));
    assertTrue(outcontent.toString().contains("---- Training ---"));
    assertTrue(outcontent.toString().contains("---------------------------"));
  }

  @Test
  public void searchingEntryByKeyWordWillWorkOnTitleField() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllWithWord("innlegg");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("innlegg"));
    assertTrue(outcontent.toString().contains("---- Training ---"));
    assertTrue(outcontent.toString().contains("---------------------------"));
  }

  @Test
  public void searchingEntryByKeyWordWillWorkOnWorkoutField() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    diary.seeAllWithWord("innlegg");
    System.setOut(orignalOut);

    assertTrue(outcontent.toString().contains("innlegg"));
    assertTrue(outcontent.toString().contains("---- Training ---"));
    assertTrue(outcontent.toString().contains("---------------------------"));
  }

  @Test
  public void searchingEntryByKeyWordShouldGiveCorrectEntriesNeGaTiVe() {
    PrintStream orignalOut = System.out;
    ByteArrayOutputStream outcontent = new ByteArrayOutputStream();

    System.setOut(new PrintStream(outcontent));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary.seeAllWithWord("blablabla"));
    System.setOut(orignalOut);

  }

  @Test
  public void searchingEntryByKeyWordButGivesEmptyShouldGiveExecption() {
    assertThrows(IllegalArgumentException.class, () -> diary.seeAllWithWord(""));
  }

  @Test
  public void deletingAnEntryThatDoesntExistShouldGiveExecption() {
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> diary.deleteEntry(10));
  }

  @Test
  public void getEntriesShouldReturnList() {
    assertFalse(diary.getEntries().isEmpty());
  }
}
