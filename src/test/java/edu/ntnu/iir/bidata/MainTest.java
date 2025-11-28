
import java.util.Scanner;

public class MainTest {
    public static void init(Scanner scanner){
        // Make 
        System.out.print("What would you like to name your Diary (Press enter for default name: SportsDiary): ");
        String inputName = scanner.nextLine();
        if (inputName.isEmpty()){
            inputName = "Sports-Diary";
        }
        DiaryTest Diary1 = new DiaryTest(inputName);

        System.out.print("Write down the authors name (you can add or delete later)(Press enter for default: John Doe): ");
        String authorName = scanner.nextLine();
        if (authorName.isEmpty()){
            authorName = "John Doe";
        }
        authorTest author1 = new authorTest(authorName);
        authorRegistryTest Authors = new authorRegistryTest();
        Authors.addAuthor(author1);

        System.out.print("Add filler content? (y/n): ");
        String fillerChoice = scanner.nextLine();
        if (fillerChoice.equalsIgnoreCase("y")){
            // Filler content:
            authorTest author2 = new authorTest("Adrian Balunan");
            Authors.addAuthor(author2);

            DiaryEntry entry1 = new DiaryEntry (author2, "Første innlegget", "Dette er det første innlegget i dagboken min!", "23-10-2024");
            Diary1.addEntry(entry1);

            DiaryEntry entry3 = new DiaryEntry (author2, "Noe innlegg", "Noe tilfeldig skal stå her eller noe slikt!", "30-10-2024");
            Diary1.addEntry(entry3);

            DiaryEntry entry4 = new DiaryEntry (author2, "Denne forfatteren", "Denne forfatteren har mange innlegg i denne dagboken!", "31-10-2024");
            Diary1.addEntry(entry4);

            authorTest author3 = new authorTest("Ola Nordmann");
            Authors.addAuthor(author3);

            DiaryEntry entry2 = new DiaryEntry (author3, "Andre innlegget", "Dette er det andre innlegget i dagboken min!" , "30-10-2025");
            Diary1.addEntry(entry2);
        }
        System.out.println("");

        // Start
        start(scanner, Diary1, Authors);
    }
    public static void start(Scanner scanner, DiaryTest d, authorRegistryTest Authors){
        // While-loop
        int valg = 0;
        do {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // SLeep for 1 second
            System.out.println("\n------- Main Menu -------");
            System.out.println("Diary: " + d.getDiaryName());
            System.out.println("1  See all diary-entries");
            System.out.println("2  See all diary-entries by spesific author");
            System.out.println("3. Add an entry");
            System.out.println("4. Delete an Entry");
            System.out.println("------");
            System.out.println("5. Search entries");
            System.out.println("------");
            System.out.println("6. See all authors");
            System.out.println("7. See author statistics");
            System.out.println("8. Add an author");
            System.out.println("9. Delete an author");
            System.out.println("---------------------");
            System.out.println("10. Quit");



            System.out.print("Enter your number of choice (1-9):");
            valg = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");

            switch (valg) {  
                case 1:
                    d.seeAll();
                    break;
                case 2:
                    Authors.seeAll();
                    System.out.print("\nWrite down the Author ID you want to see all entries from: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();
                    d.seeAllByAuthor(authorId, Authors);
                    break;
                case 3:
                    System.out.print("Add your title: ");
                    String name = scanner.nextLine();


                    Authors.seeAll();
                    System.out.print("\nWrite down the Author ID you want to assign to this entry: ");
                    authorId = scanner.nextInt();
                    authorTest foundAuthor = Authors.getAuthorByID(authorId);
                    scanner.nextLine();

                    System.out.println("\nAdd your content:");
                    String content = scanner.nextLine();
                    DiaryEntry dEntry = new DiaryEntry (foundAuthor, name, content);
                    d.addEntry(dEntry);
            
                    break;
                case 4:
                    d.seeAll();
                    System.out.print("Write the specified ID for the Entry you want to delete it: ");
                    int inputID = scanner.nextInt();
                    scanner.nextLine();
                    d.deleteEntry(inputID);
                    break;
                case 5:
                    search(scanner, d, Authors);
                    break;
                case 6:
                    Authors.seeAll();
                    break;
                case 8:
                    System.out.print("Write the author's name you want to add: ");
                    String author_name = scanner.nextLine();
                    authorTest newAuthor = new authorTest(author_name);

                    Authors.addAuthor(newAuthor);
                    break;
                case 7:
                    d.showAuthorStatistics();
                    break;
                case 9:
                    Authors.seeAll();
                    System.out.print("Write the specified Author ID you want to delete: ");
                    int authorID = scanner.nextInt();
                    scanner.nextLine();

                    Authors.DeleteByID(authorID);
                    break;
                case 10:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    break;
            }
        } while (valg != 9);   
    }
    public static void search(Scanner scanner, DiaryTest d, authorRegistryTest Authors){
        int valg2 = 0;
        do {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("\n------- Search Functions: -------");
            System.out.println(d.getDiaryName());
            System.out.println("1. See all diary-entries by spesific author");
            System.out.println("2. See all diary-entries between two dates");
            System.out.println("3. See all diary-entries from a spesific date");
            System.out.println("4. See all diary-entries by keyword");
            System.out.println("5. Quit");

            System.out.print("Enter your number of choice (1-5):");
            valg2 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");

            switch (valg2) {
                case 1:
                    Authors.seeAll();
                    System.out.print("\nWrite down the Author ID you want to see all entries from: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();
                    d.seeAllByAuthor(authorId, Authors);
                    break;
                case 2:
                    System.out.print("Skriv inn første dato (DD-MM-YYYY):");
                    String startDato = scanner.nextLine();
                    System.out.print("Skriv inn andre dato (DD-MM-YYYY):");
                    String sluttDato = scanner.nextLine();
                    d.seeAllBetweenDates(startDato, sluttDato);
                    break;
                case 3:
                    System.out.print("Skriv inn første dato (DD-MM-YYYY):");
                    String Dato = scanner.nextLine();
                    d.seeAllInDate(Dato);
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
                    break;
            }
        }
        while (valg2 != 5);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        init(scanner);    
    }
}
