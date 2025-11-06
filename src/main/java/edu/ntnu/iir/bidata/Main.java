package edu.ntnu.iir.bidata;

import java.util.Scanner;

public class Main {
    public static void init(Scanner scanner){
        // Make 
        System.out.print("What would you like to name your Diary: ");
        String inputName = scanner.nextLine();
        Diary Diary1 = new Diary(inputName);
        System.out.println("");


        // Start
        start(scanner, Diary1);
    }
    public static void start(Scanner scanner, Diary d){
        // Filler content:
        DiaryEntry aEntry = new DiaryEntry ("Leaving", "Bro why everyone leaving sob sob sob", "Typeshi");
        d.addEntry(aEntry);
        DiaryEntry vEntry = new DiaryEntry ("Grahhhh", "Bro why everyone leaving sob sob sob", "Adrian");
        d.addEntry(vEntry);
        // While-loop
        int valg = 0;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // SLeep for 1 second
            System.out.println("\n------- Main Menu -------");
            System.out.println("Diary: " + d.getDiaryName());
            System.out.println("1  See all diary-entries");
            System.out.println("2. Add an entry");
            System.out.println("3. Delete an Entry");
            System.out.println("4. Quit");



            System.out.print("Enter your number of choice (1-3):");
            valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {  
                case 1:
                    d.toString();
                    break;
                case 2:
                    System.out.print("Add your title: ");
                    String name = scanner.nextLine();
                    System.out.print("\nAdd the author: ");
                    String author = scanner.nextLine();
                    System.out.println("\nAdd your content:");
                    String content = scanner.nextLine();
                    DiaryEntry dEntry = new DiaryEntry (name, content, author);
                    d.addEntry(dEntry);
            
                    break;
                case 3:
                    d.toString();
                    System.out.print("Write the specified ID for the Entry you want to delete it: ");
                    int inputID = scanner.nextInt();
                    scanner.nextLine();
                    d.deleteEntry(inputID);
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    break;
            }
        } while (valg != 4);   
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        init(scanner);    
    }
}
