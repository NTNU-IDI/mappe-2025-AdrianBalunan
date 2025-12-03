import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunTests {
  public static void main(String[] args) {
    System.out.println("Running test, this may take longer than expected ...");

    Result authorTestResult = JUnitCore.runClasses(AuthorTest.class);
    Result authorRegistryTestResult = JUnitCore.runClasses(AuthorRegistryTest.class);
    final Result DiaryEntryTestResult = JUnitCore.runClasses(DiaryEntryTest.class);
    final Result DiaryTestResult = JUnitCore.runClasses(DiaryTest.class);

    System.out.println("----- TEST RESULTS ------");
    for (Failure failure : authorTestResult.getFailures()) {
      System.out.println("[FAIL] " + failure.toString());
    }
    for (Failure failure : authorRegistryTestResult.getFailures()) {
      System.out.println("[FAIL] " + failure.toString());
    }
    for (Failure failure : DiaryEntryTestResult.getFailures()) {
      System.out.println("[FAIL] " + failure.toString());
    }
    for (Failure failure : DiaryTestResult.getFailures()) {
      System.out.println("[FAIL] " + failure.toString());
    }

    System.out.println("--- STATS ---");
    System.out.println("Total tests: " 
        + authorTestResult.getRunCount()
        + authorRegistryTestResult.getRunCount() 
        + DiaryTestResult.getRunCount()
        + DiaryEntryTestResult.getRunCount());
    System.out.println("Failures: " 
        + authorTestResult.getFailureCount()
        + authorRegistryTestResult.getFailureCount() 
        + DiaryTestResult.getFailureCount()
        + DiaryEntryTestResult.getFailureCount());
    System.out.println("Ignored: " 
        + authorTestResult.getIgnoreCount()
        + authorRegistryTestResult.getIgnoreCount() 
        + DiaryTestResult.getIgnoreCount()
        + DiaryEntryTestResult.getIgnoreCount());
    System.out.println("Time: " 
        + authorTestResult.getRunTime()
        + authorRegistryTestResult.getRunTime() 
        + DiaryTestResult.getRunTime()
        + DiaryEntryTestResult.getRunTime()
        + " ms");
    System.out.println("");
    if (authorTestResult.wasSuccessful() 
        && authorRegistryTestResult.wasSuccessful()
        && DiaryEntryTestResult.wasSuccessful()
        && DiaryTestResult.wasSuccessful()
        ) {
      System.out.println("ALL TEST PASSED");
    } else {
      System.out.println("SOME TEST FAILED");
    }
  }
}
