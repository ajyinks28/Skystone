// this was written by adeyinka jedidiah adegalu
import java.io.*;
import java.util.Scanner;

public class TestFunction {

    // this method creates the tests
    public static void createTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the test code:");
        String testCode = scanner.nextLine();

        System.out.println("Enter the name of the file:");
        String fileName = scanner.nextLine();

        String directoryPath = "tests";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Failed to create directory.");
                return;
            }
        }

        File file = new File(directory, fileName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(testCode);
            writer.newLine();
            String question;
            do {
                System.out.println("Enter a question (or type 'exit' to finish):");
                question = scanner.nextLine();
                if (!"exit".equalsIgnoreCase(question)) {
                    System.out.println("Enter the answer:");
                    String answer = scanner.nextLine();
                    writer.write(question + ", " + answer);
                    writer.newLine();
                }
            } while (!"exit".equalsIgnoreCase(question));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Test created successfully in folder 'tests'.");
    }

    // this method lets users take the tests
    public static void takeTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String userName = scanner.nextLine();

        System.out.println("Enter the test code:");
        String testCode = scanner.nextLine();

        File testsDir = new File("tests");
        File[] files = testsDir.listFiles();
        boolean found = false;

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String storedTestCode = reader.readLine();
                        if (storedTestCode != null && storedTestCode.equals(testCode)) {
                            found = true;
                            int score = 0;
                            int totalQuestions = 0;
                            String line;
                            while ((line = reader.readLine()) != null) {
                                String[] parts = line.split(", ");
                                if (parts.length == 2) {
                                    System.out.println(parts[0]);
                                    String userAnswer = scanner.nextLine();
                                    if (userAnswer.equalsIgnoreCase(parts[1])) {
                                        score++;
                                    }
                                    totalQuestions++;
                                }
                            }
                            System.out.println("Test completed. Your score: " + score + "/" + totalQuestions);
                            saveTestResults(testCode, userName, score, totalQuestions);
                            break;
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading the file: " + e.getMessage());
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No test with that code found.");
        }
    }
    // this was written by adeyinka jedidiah adegalu
    // this function saves the test results
    public static void saveTestResults(String testCode, String userName, int score, int totalQuestions) {
        String resultsDirectoryPath = "test_results";
        File directory = new File(resultsDirectoryPath);
        if (!directory.exists() && !directory.mkdirs()) {
            System.out.println("Failed to create directory for test results.");
            return;
        }

        File resultsFile = new File(directory, testCode + ".csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFile, true))) {
            writer.write("Test Code,User Name,Score,Total Questions\n");
            writer.write(testCode + "," + userName + "," + score + "," + totalQuestions + "\n");
        } catch (IOException e) {
            System.err.println("Error writing test results: " + e.getMessage());
        }

        System.out.println("Test results saved successfully.");
    }
}
// this was written by adeyinka jedidiah adegalu