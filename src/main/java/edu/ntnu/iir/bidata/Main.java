package edu.ntnu.iir.bidata;

import java.util.Scanner;

public class Main {
    public static void init(Scanner scanner){
        // Make 
        System.out.print("What would you like to name your Diary: ");
        String inputName = scanner.nextLine();
        Diary Diary1 = new Diary(inputName);

        System.out.print("Write down the authors name (you can add or delete later): ");
        String authorName = scanner.nextLine();
        author author1 = new author(authorName);
        authorRegistry Authors = new authorRegistry();
        Authors.addAuthor(author1);

        System.out.println("");


        // Start
        start(scanner, Diary1, Authors);
    }
    public static void start(Scanner scanner, Diary d, authorRegistry Authors){
        // Filler content:
        author author1 = new author("Adrian Balunan");
        Authors.addAuthor(author1);

        DiaryEntry entry1 = new DiaryEntry (author1, "Første innlegget", "Dette er det første innlegget i dagboken min!");
        d.addEntry(entry1);


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
            System.out.println("2. Add an entry");
            System.out.println("3. Delete an Entry");
            System.out.println("------");
            System.out.println("4. See all authors");
            System.out.println("5. Add an author");
            System.out.println("6. Delete an author");
            System.out.println("------");
            System.out.println("7. Quit");



            System.out.print("Enter your number of choice (1-7):");
            valg = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");

            switch (valg) {  
                case 1:
                    d.seeAll();
                    break;
                case 2:
                    System.out.print("Add your title: ");
                    String name = scanner.nextLine();


                    Authors.seeAll();
                    System.out.print("\nWrite down the Author ID you want to assign to this entry: ");
                    int authorId = scanner.nextInt();
                    author foundAuthor = Authors.getAuthorByID(authorId);
                    scanner.nextLine();

                    System.out.println("\nAdd your content:");
                    String content = scanner.nextLine();
                    DiaryEntry dEntry = new DiaryEntry (foundAuthor, name, content);
                    d.addEntry(dEntry);
            
                    break;
                case 3:
                    d.seeAll();
                    System.out.print("Write the specified ID for the Entry you want to delete it: ");
                    int inputID = scanner.nextInt();
                    scanner.nextLine();
                    d.deleteEntry(inputID);
                    break;

                case 4:
                    Authors.seeAll();
                    break;
                case 5:
                    System.out.print("Write the author's name you want to add: ");
                    String author_name = scanner.nextLine();
                    author newAuthor = new author(author_name);
                    Authors.addAuthor(newAuthor);
                    break;
                case 6:
                    Authors.seeAll();
                    System.out.print("Write the specified Author ID you want to delete: ");
                    int authorID = scanner.nextInt();
                    scanner.nextLine();
                    Authors.DeleteByID(authorID);
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    break;
            }
        } while (valg != 7);   
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        init(scanner);    
    }
}
