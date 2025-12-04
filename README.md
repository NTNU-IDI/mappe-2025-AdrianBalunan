# Portfolio project IDATT1003

STUDENT NAME =  Adrian Paul Limpiado Balunan

STUDENT ID = 157346

## Project description

The main focus of this project is developing a Sports-Diary. Features of this system include adding and deleting an entry, searching for a spesific entry through dates or by word, managing authors and show author statistics.

This program uses Java and is developed on VS code.

## Project structure

The files in the project can be split into groups: **Class** and **UI**.

* **Classes:** Includes the *Author, AuthorRegistry, DIary, DiaryEntry* files. These files are only Java objects that hold the nessesary variables for methods to handle user input and show correct data when needed.
* **UI:** *Main* file holds all the code for the user interface and takes inn user input.

## Link to repository

[Link to GitHub Repo for Adrian Balunan](https://github.com/NTNU-IDI/mappe-2025-AdrianBalunan)

## How to run the project

**Requirements**: Java 21 or higher.

1. Download `SportsDiary.jar` file. Directly by downloading as a zip, or using `git clone` on a familiar folder with a command line based emulator.
2. Navigate to the folder containing the `SportsDiary.jar` file on a terminal umulator.
3. Run this code:

   ```bash
   java -jar SportsDiary.jar
   ```

## How to run the tests

### Normal Tests

**Requirements**: Java 21 or higher.

To run and see normals tests:

1. Download `RunTests.jar` file. Directly by downloading as a zip, or using `git clone` on a familiar folder with a command line based emulator.
2. Navigate to the folder containing the `RunTests.jar` file on a terminal umulator.
3. Run this code:

   ```bash
   java -jar RunTests.jar
   ```

Unittesting uses 4.13.2 JUnit and 1.3 Hamcrest:

* **JUnit** and **Hamcrest** for the unitesting

The results are package into a fat jar file.

### Tests with Coverage

***Please Note***: The test covers the Main.java file, which is NOT whats supposed to be happeining. Due to this, the statistics are way off, only because of  the Main.java being included.

**No Requirements.**

1. Download the `testWithCoverage` folder either directly by downloading as a zip, or using `git clone` on a familiar folder with a command line based emulator.
2. In your File Explorer, navigate to the said `testWithCoverage` folder and open it.
3. Open the `index.html` with your preferred web browser. By either double-clicking or right clicking then opening with your web browser.

Unittesting uses 4.13.2 JUnit, 1.3 Hamcrest and 0.8.14 JaCoCo:

* JUnit and Hamcrest are used to compile and run the java test file. The results can be viewed on the step above *Normals Tests.*
* JaCoCo is used to test for coverage and to create a proper display for the results.


## References

GitHub Copilot and ChatGPT has beed used for:

* Creating test/filler data.
* Suggesting .jar files format

Other websites such as: [Stack Overflow](https://stackoverflow.com/questions), [Geeksforgeeks](https://www.geeksforgeeks.org/), [W3schools](https://www.w3schools.com), Reddit etc.
