
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents an DiaryEntry, with apporirate attributes and methods.
 *
 * @auther Adrian Balunan
 * @verison 1.0
 */
public class DiaryEntry {
  /** DiaryEntry Attributes. */

  /* Release date of the entry in LocalDateTime. */
  private String releaseDate;

  /**
   * Accessible and available Id_counter, makes sure that after each occurrence of
   * an DiaryEntry as
   * a unique ID.
   */
  private static int nextId = 1;

  /**
   * Used to reset nextId counter.
   */
  public static void resetEntryNextId() {
    nextId = 1;
  }

  /** Actual unique ID for the DiaryEntry object. */
  private final int id;

  /** The title of the Entry. */
  private String title;

  /** Accosicated Author object of the Entry. */
  private Author author;

  /** Content variables that store info. */
  private String content;

  /** Contains details of your workout. */
  private String workout;

  /**
   * Contructur for the DiaryEntry class, with input parameters. Relase date is
   * automatically set to
   * the current date and time, and custimized using a formatter
   *
   * @param author  input Author
   * @param title   input title
   * @param content input content
   * @param workout input workout
   * @throws ArrayIndexOutOfBoundsException If the author doesnt exist.
   * @throws IllegalArgumentException The parameters are empty
   */
  public DiaryEntry(AuthorRegistry authors, Author author, String title, String content, String workout) {
    if (!authors.getAuthors().contains(author)) {
      throw new ArrayIndexOutOfBoundsException();
    }
    this.author = author;

    if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty() 
        || workout == null
        || workout.isEmpty()) {
      throw new IllegalArgumentException();
    }
    this.title = title;
    this.content = content;
    this.workout = workout;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm:ss)");
    this.releaseDate = LocalDateTime.now().format(formatter);
    this.id = nextId++;

  }

  /**
   * Contructur for the DiaryEntry class, with input parameters AND with custom
   * release date. Used
   * only with filler content, for better representation. Hours, minutes and
   * seconds will be
   * 00:00:00.
   *
   * @param author      input Author
   * @param title       input title
   * @param content     input content
   * @param releaseDate input release date. Hours, minutes and seconds to 00:00:00
   * @param workout     input workout
   */
  public DiaryEntry(Author author, String title, String content, String workout, String releaseDate) {
    this.author = author;
    this.releaseDate = releaseDate + " (00:00:00)";
    this.id = nextId++;
    this.title = title;
    this.content = content;
    this.workout = workout;
  }

  /**
   * Getter-methods for getting the release date.
   *
   * @return Relase date of the entry
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * Getter-method for getting the title.
   *
   * @return Title of the entry
   */
  public String getTitle() {
    return title;
  }

  /**
   * Getter-method for getting the content.
   *
   * @return Returns the content of the
   */
  public String getContent() {
    return content;
  }

  /**
   * Getter-method for getting the Author name.
   *
   * @return Returns the Author of the entrys name
   */
  public String getAuthorName() {
    return author.getAuthorName();
  }

  /**
   * Getter-method for getting the workout.
   *
   * @return Returns the Author of the entrys name
   */
  public String getWorkout() {
    return workout;
  }

  /**
   * Getter-method for getting the Author ID.
   *
   * @return Returns the author of the entrys ID
   */
  public int getAuthorId() {
    return author.getAuthorId();
  }

  /**
   * Getter-method for getting the ID.
   *
   * @return Returns the Entrys ID
   */
  public int getId() {
    return id;
  }

  /**
   * Getter-method for the actual Author Object.
   *
   * @return Returns all data (the object) about author.
   */
  public Author getAuthorObject() {
    return author;
  }

  private void updateReleasedate(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy (HH:mm:ss)");
    this.releaseDate = "(Editde):" + LocalDateTime.now().format(formatter);
  }
  public void setTitle(String inputName){
    this.name = inputName;
    updateReleasedate();
  }
  public void setContent(String inputContent){
    this.content = inputContent;
    updateReleasedate();
  }
  
  /**
   * Unique toString method that prints out all attributes of the DiaryEntry
   * object The seeAll
   * method provides a better representation for the user, and therefore not used.
   *
   * @return Fancy print
   */
  @Override
  public String toString() {
    return "ID: "
        + id
        + ". ReleaseDate: "
        + releaseDate
        + ", Title: "
        + title
        + ", Author: "
        + author.getAuthorName()
        + ", Content: "
        + content
        + ", Workout: "
        + workout;
  }
}
