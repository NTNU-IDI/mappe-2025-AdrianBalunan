package edu.ntnu.iir.bidata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void init(Scanner scanner) {
    // Make
    System.out.print(
        "What would you like to name your Diary (Press enter for default name: SportsDiary): ");
    String inputName = scanner.nextLine();
    if (inputName.isEmpty()) {
      inputName = "Sports-Diary";
    }
    Diary Diary1 = new Diary(inputName);

    System.out.print(
        "Write down the authors name (you can add or delete later)(Press enter for default: John Doe): ");
    String authorName = scanner.nextLine();
    if (authorName.isEmpty()) {
      authorName = "John Doe";
    }
    Author author1 = new Author(authorName);
    AuthorRegistry Authors = new AuthorRegistry();
    Authors.addAuthor(author1);

    System.out.print("Add filler content? (y/n): ");
    String fillerChoice = scanner.nextLine();
    if (fillerChoice.equalsIgnoreCase("y")) {
      // Filler content:
      Author author2 = new Author("Adrian Balunan");
      Authors.addAuthor(author2);

      DiaryEntry entry1 =
          new DiaryEntry(
              author2,
              "Første innlegget",
              "Dette er det første innlegget i dagboken min!",
              "23-10-2024");
      Diary1.addEntry(entry1);

      DiaryEntry entry3 =
          new DiaryEntry(
              author2, "Noe innlegg", "Noe tilfeldig skal stå her eller noe slikt!", "30-10-2024");
      Diary1.addEntry(entry3);

      DiaryEntry entry4 =
          new DiaryEntry(
              author2,
              "Denne forfatteren",
              "Denne forfatteren har mange innlegg i denne dagboken!",
              "31-10-2024");
      Diary1.addEntry(entry4);

      Author author3 = new Author("Ola Nordmann");
      Authors.addAuthor(author3);

      DiaryEntry entry2 =
          new DiaryEntry(
              author3,
              "Andre innlegget",
              "Dette er det andre innlegget i dagboken min!",
              "30-10-2025");
      Diary1.addEntry(entry2);
    }
    System.out.println("");

    // Start
    start(scanner, Diary1, Authors);
  }

  public static void start(Scanner scanner, Diary d, AuthorRegistry Authors) {
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
        System.out.println("Please put a valid input");
        continue;
      }
      System.out.println("");

      String userInput = null;
      int userParse = 0;
      switch (valg) {
        case 1:
          d.seeAll();
          break;
        case 2:
          Authors.seeAll();
          System.out.print("\nWrite down the Author ID you want to see all entries from:");

          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            continue;
          }

          scanner.nextLine();
          d.seeAllByAuthor(userParse, Authors);
          break;
        case 3:
          System.out.print("Add your title: ");
          String name = scanner.nextLine();

          Authors.seeAll();
          System.out.print("\nWrite down the Author ID you want to assign to this entry: ");
          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            continue;
          }
          Author foundAuthor = Authors.getAuthorByID(userParse);
          scanner.nextLine();

          System.out.println("\nAdd your content:");
          String content = scanner.nextLine();
          DiaryEntry dEntry = new DiaryEntry(foundAuthor, name, content);
          d.addEntry(dEntry);

          break;
        case 4:
          d.seeAll();
          System.out.print("Write the specified ID for the Entry you want to delete it: ");

          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            continue;
          }
          scanner.nextLine();

          boolean entryExists = false;
          for (DiaryEntry entry : d.getEntries()) {
            if (entry.getId() == userParse) {
              entryExists = true;
              return;
            }
          }

          if (entryExists) {
            d.deleteEntry(userParse);
          } else {
            System.out.println("Entry does not exist.");
          }
          break;
        case 5:
          search(scanner, d, Authors);
          break;
        case 6:
          Authors.seeAll();
          break;
        case 8:
          System.out.print("Write the Author's name you want to add: ");
          String author_name = scanner.nextLine();
          Author newAuthor = new Author(author_name);

          Authors.addAuthor(newAuthor);
          break;
        case 7:
          d.showAuthorStatistics();
          break;
        case 9:
          Authors.seeAll();
          System.out.print("Write the specified Author ID you want to delete: ");

          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            continue;
          }
          int tempParse = userParse;

          if (Authors.getAuthorByID(tempParse) == null) {
            System.out.println("Inputted Author does not exist");
            continue;
          }

          List<DiaryEntry> deletedAuthorEntries =
              d.getEntries().stream().filter(x -> x.getAuthorID() == tempParse).toList();

          for (DiaryEntry entry : deletedAuthorEntries) {
            d.deleteEntry(entry.getId());
          }
          Authors.DeleteByID(userParse);
          break;
        case 10:
          System.out.println("Exiting the program. Goodbye!");
          break;
        default:
          System.out.println("Invalid input, please try again");
          break;
      }
    } while (valg != 10);
  }

  public static void search(Scanner scanner, Diary d, AuthorRegistry Authors) {
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
      valg2 = scanner.nextInt();
      scanner.nextLine();
      System.out.println("");

      String userInput = null;
      int userParse = 0;
      switch (valg2) {
        case 1:
          Authors.seeAll();
          System.out.print("\nWrite down the Author ID you want to see all entries from: ");
          userInput = scanner.nextLine();
          try {
            userParse = Integer.parseInt(userInput);
          } catch (Exception e) {
            System.out.println("Please put a valid input next time");
            continue;
          }
          scanner.nextLine();
          d.seeAllByAuthor(userParse, Authors);
          break;
        case 2:
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
          }

          if (start.isAfter(end) || end.isBefore(start)) {
            System.out.println(
                "Please type a valid date interval, the first date should be before the second date");
            continue;
          }

          d.seeAllBetweenDates(startDato, sluttDato);
          break;
        case 3:
          System.out.print("Skriv inn første dato (DD-MM-YYYY):");
          String datoInput = scanner.nextLine();

          if (datoInput.length() != 10
              || datoInput.charAt(2) != '-'
              || datoInput.charAt(5) != '-') {
            System.out.println("Please type in your date at the spesified format.");
            continue;
          }
          LocalDate datoParsed = null;
          formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          try {
            datoParsed = LocalDate.parse(datoInput, formatter);
          } catch (DateTimeParseException e) {
            System.out.println("Please type in valid dates.");
          }

          d.seeAllInDate(datoInput);
          break;
        case 4:
          System.out.print("Skriv inn søkeord:");
          String keyword = scanner.nextLine();
          d.seeAllWithWord(keyword);
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

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    init(scanner);
  }
}
