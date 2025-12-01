
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Main method, UI and user input goes in here.
 */
public class Main {
  /**
   * Initalizes and creates variables and objects.
   * 
   * @param scanner Scanner object
   * @throws Exception 
   */
  public static void init(Scanner scanner) throws Exception {
    // Welcome:
    System.out.println(" ");
    System.out.println("------------------");
    System.out.println("- Welcome to your own digital (Sports) diary");
    System.out.println("------------------");
    System.out.println("You'll be able do add an entry, search for said entry and manage authors.");

    pressToContinue(scanner);

    // Make
    System.out.print(
        "What would you like to name your Diary (Press enter for default name: SportsDiary): ");
    String inputName = scanner.nextLine();
    if (inputName.isEmpty()) {
      inputName = "Sports-Diary";
    }
    final Diary Diary1 = new Diary(inputName);

    System.out.print(
        "Write down the authors name (Add or delete later)(Press enter for default: John Doe): ");
    String authorName = scanner.nextLine();
    if (authorName.isEmpty()) {
      authorName = "John Doe";
    }
    Author author1 = new Author(authorName);
    AuthorRegistry authors = new AuthorRegistry();
    authors.addAuthor(author1);

    System.out.print("Add filler content? Recommended to test the systems functionality (y/n): ");
    String fillerChoice = scanner.nextLine();
    if (fillerChoice.equalsIgnoreCase("y")) {
      // Filler content:
      Author author2 = new Author("Steve");
      authors.addAuthor(author2);

      DiaryEntry entry1 =
          new DiaryEntry(
              author2,
              "First Entry",
              "This is the first Entry, workout feelt great",
              "Running and Intervals",
              "23-10-2024");
      Diary1.addEntry(entry1);

      DiaryEntry entry3 =
          new DiaryEntry(
              author2, 
              "Second Entry", 
              "Did somethings here and there but nothing too crazy", 
              "Something something strenght training",
              "30-10-2024");
      Diary1.addEntry(entry3);

      DiaryEntry entry4 =
          new DiaryEntry(
              author2,
              "Third Entry",
              "Gym closed so i went outside",
              "Stamina Training",
              "31-10-2024");
      Diary1.addEntry(entry4);

      Author author3 = new Author("Jane Doe");
      authors.addAuthor(author3);

      DiaryEntry entry2 =
          new DiaryEntry(
              author3,
              "Woahhhhhhh",
              "Look im using the system, workout was alright.",
              "Running and Strenght",
              "30-10-2025");
      Diary1.addEntry(entry2);
    }
    System.out.println(" ");
    System.out.println("Loading the Main menu...");

    // Start
    start(scanner, Diary1, authors);
  }

  /**
   * Starts the main menu sequence.
   * 
   * @param scanner Scanner Object made in init()
   * @param d Diary Object made in init()
   * @param authors AuthorRegistry Object made in init()
   */
  public static void start(Scanner scanner, Diary d, AuthorRegistry authors) throws Exception {
    // While-loop
    int valg = 0;
    do {
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // SLeep for 1 second
      System.out.println("\n------- Main Menu -------");
      System.out.println("Diary: " + d.getDiaryName());
      System.out.println("1  See all diary-entries");
      System.out.println("2  See all diary-entries by spesific Author");
      System.out.println("3. Add an entry");
      System.out.println("4. Delete an Entry");
      System.out.println("------");
      System.out.println("5. Search entries");
      System.out.println("------");
      System.out.println("6. See all authors");
      System.out.println("7. See Author statistics");
      System.out.println("8. Add an Author");
      System.out.println("9. Delete an Author");
      System.out.println("---------------------");
      System.out.println("10. Quit");

      System.out.print("Enter your number of choice (1-10):");

      String valgInput = scanner.nextLine();
      try {
        valg = Integer.parseInt(valgInput);
      } catch (Exception e) {
        System.out.println("");
        System.out.println("Invalid input, please try again.");
        continue;
      }
      System.out.println("");

      String userInput = null;
      int userParse = 0;
      switch (valg) {
        case 1:
          try {
            d.seeAll();
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No Entries found.");
            pressToContinue(scanner);
            continue;
          }
          pressToContinue(scanner);
          break;
        case 2:
          try {
            authors.seeAll();
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No Authors are registered.");
            pressToContinue(scanner);
            continue;
          }
          
          System.out.print("\nWrite down the Author ID you want to see all entries from:");
          userInput = scanner.nextLine();

          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            pressToContinue(scanner);
            continue;
          }

          try {
            d.seeAllByAuthor(userParse, authors);
          } catch (IllegalArgumentException e) {
            System.out.println("No Author found with Id of, " + userParse + ".");
            pressToContinue(scanner);
            continue;
          }
          pressToContinue(scanner);
          break;
        case 3:
          if (authors.getAuthors().isEmpty()) {
            System.out.println("No Authors are registered and therefore can't make an entry");
            pressToContinue(scanner);
          }

          System.out.println("----- Adding an Entry -----");

          System.out.print("Add your title: ");
          final String name = scanner.nextLine();

          authors.seeAll();
  
          System.out.print("Write down the Author ID you want to assign to this entry: ");
          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            pressToContinue(scanner);
            continue;
          }
          

          System.out.println("Add your content (your thoughts and evaluations):");
          String content = scanner.nextLine();

          System.out.println("Include details about your workout:");
          String workout = scanner.nextLine();

          Author foundAuthor = authors.getAuthorById(userParse);

          try {
            DiaryEntry entry = new DiaryEntry(authors, foundAuthor, name, content, workout);
            d.addEntry(entry);
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Chosen Author does not exist.");
            pressToContinue(scanner);
            continue;
          } catch (IllegalArgumentException e) {
            System.out.println("None of the input fields can be empty");
            pressToContinue(scanner);
            continue;
          }

          System.out.println(" ");
          System.out.println("Success!");
          
          pressToContinue(scanner);
          break;
        case 4:
          if (d.getEntries().isEmpty()) {
            System.out.println("No entry to delete.");
            pressToContinue(scanner);
            continue;
          }
          System.out.println("----- Deleting an Entry -----");

          d.seeAll();

          System.out.print("Write the specified ID for the Entry you want to delete it: ");
          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            pressToContinue(scanner);
            continue;
          }

          try {
            d.deleteEntry(userParse);
            System.out.println("Entry is found and deleted");
            pressToContinue(scanner);
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Entry does not exist.");
            pressToContinue(scanner);
            continue;
          }

          break;
        case 5:
          if (d.getEntries().isEmpty()) {
            System.out.println("No entry registered, so no need to search ");
            pressToContinue(scanner);
            continue;
          }
          search(scanner, d, authors);
          break;
        case 6:
          try {
            authors.seeAll();
            pressToContinue(scanner);
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No Authors are registerd.");
            pressToContinue(scanner);
            continue;
          }
          
          break;
        case 8:
          System.out.println("----- Adding an Author -----");
          System.out.print("Write the Author's name you want to add: ");
          String authorName = scanner.nextLine();
          Author newAuthor = new Author(authorName);

          System.out.println("Author, " + newAuthor.getAuthorName() + " is added.");
          authors.addAuthor(newAuthor);
          pressToContinue(scanner);
          break;
        case 7:
          try {
            d.showAuthorStatistics();
            pressToContinue(scanner);
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Authors havent made an entry");
            pressToContinue(scanner);
            continue;
          }
          break;
        case 9:
          if (authors.getAuthors().isEmpty()) {
            System.out.println("No Author to delete.");
            pressToContinue(scanner);
            continue;
          }
          System.out.println("----- Deleting an Author -----");
            
          authors.seeAll();

          System.out.print("Write the specified Author ID you want to delete: ");
          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            pressToContinue(scanner);
            continue;
          }
          int tempParse = userParse;

          try {
            authors.deleteById(tempParse);
            List<DiaryEntry> deletedAuthorEntries =
                d.getEntries().stream().filter(x -> x.getAuthorId() == tempParse).toList();

            for (DiaryEntry authorEntry : deletedAuthorEntries) {
              d.deleteEntry(authorEntry.getId());
            }
            System.out.println("Author found and deleted.");
            pressToContinue(scanner);

          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Inputted Author does not exist");
            pressToContinue(scanner);
            continue;
          }

          break;
        case 10:
          System.out.println("Exiting the program. Goodbye!");
          break;
        default:
          System.out.println("Invalid input, please try again.");
          break;
      }
    } while (valg != 10);
  }
  /**
   * Branches from start(). It's an main menu option.
   * 
   * @param scanner Scanner Object made in init()
   * @param d Diary Object made in init()
   * @param authors AuthorRegistry Object made in init()
   * @throws Exception 
   */

  public static void search(Scanner scanner, Diary d, AuthorRegistry authors) throws Exception {
    int valg2 = 0;
    do {
      try {
        Thread.sleep(200);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("\n------- Search Functions: -------");
      System.out.println(d.getDiaryName());
      System.out.println("1. See all diary-entries by spesific Author");
      System.out.println("2. See all diary-entries between two dates");
      System.out.println("3. See all diary-entries from a spesific date");
      System.out.println("4. See all diary-entries by keyword");
      System.out.println("5. Return to main menu");

      System.out.print("Enter your number of choice (1-5):");
      String valg2Input = scanner.nextLine();
      try {
        valg2 = Integer.parseInt(valg2Input);
      } catch (Exception e) {
        System.out.println("");
        System.out.println("Invalid input, please try again.");
        continue;
      }
      System.out.println(" ");

      String userInput = null;
      int userParse = 0;
      switch (valg2) {
        case 1:
          System.out.println("----- Searching Entries by Author -----");
          
          try {
            authors.seeAll();
          } catch (ArrayIndexOutOfBoundsException e) {
            // TODO: handle exception
          }

          System.out.print("\nWrite down the Author ID you want to see all entries from: ");
          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            pressToContinue(scanner);
            continue;
          }
          d.seeAllByAuthor(userParse, authors);
          pressToContinue(scanner);
          break;
        case 2:
          System.out.println("----- Searching Entries Between Two Dates -----");

          System.out.print("Skriv inn første dato (DD-MM-YYYY):");
          String startDato = scanner.nextLine();
          System.out.print("Skriv inn andre dato (DD-MM-YYYY):");
          String sluttDato = scanner.nextLine();

          if (startDato.length() != 10
              || sluttDato.length() != 10
              || sluttDato.charAt(2) != '-'
              || startDato.charAt(2) != '-'
              || sluttDato.charAt(5) != '-'
              || startDato.charAt(5) != '-') {
            System.out.println("Please type in your date at the spesified format.");
            continue;
          }

          LocalDate start = null;
          LocalDate end = null;
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          try {
            start = LocalDate.parse(startDato, formatter);
            end = LocalDate.parse(startDato, formatter);
          } catch (DateTimeParseException e) {
            System.out.println("Please type in valid dates.");
            pressToContinue(scanner);
          }

          if (start.isAfter(end) || end.isBefore(start)) {
            System.out.println(
                "Please type a valid interval, the first date should be before the second date");
            pressToContinue(scanner);
            continue;
          }

          d.seeAllBetweenDates(startDato, sluttDato);
          pressToContinue(scanner);
          break;
        case 3:
          System.out.println("----- Searching Entries made on a spesific Date -----");
          System.out.print("Skriv inn første dato (DD-MM-YYYY):");
          String datoInput = scanner.nextLine();

          if (datoInput.length() != 10
              || datoInput.charAt(2) != '-'
              || datoInput.charAt(5) != '-') {
            System.out.println("Please type in your date at the spesified format.");
            pressToContinue(scanner);
            continue;
          }
          LocalDate datoParsed;
          formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          try {
            datoParsed = LocalDate.parse(datoInput, formatter);
          } catch (DateTimeParseException e) {
            System.out.println("Please type in valid dates.");
            pressToContinue(scanner);
            continue;
          }
          System.out.println("");   


          d.seeAllInDate(datoInput);
          pressToContinue(scanner);
          break;
        case 4:
          System.out.println("----- Searching Entries that contain keyword -----");

          System.out.print("Skriv inn søkeord:");
          String keyword = scanner.nextLine();
          d.seeAllWithWord(keyword);
          pressToContinue(scanner);
          break;
        case 5:
          System.out.println("Returning to main menu...");
          break;
        default:
          System.out.println("Please input a valid choice");
          break;
      }
    } while (valg2 != 5);
  }


  /**
   * Main method, all starts here.
   * 
   * @param args Java Start
   * @throws Exception 
   */

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    init(scanner);
  }

  /**
   * Helper function to controll the passing.
   * 
   * @param scanner Scanner Objects
   */  
  public static void pressToContinue(Scanner scanner) {
    System.out.println(" ");
    System.out.println("Press Enter to continue...");
    scanner.nextLine();
  }
}
