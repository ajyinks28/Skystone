// this was written by adeyinka jedidiah adegalu
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Authenticate {
    public static final String CSV_FILE_PATH = "base/users.csv";

    public static void ensureFileExists() throws IOException {
        if (!Files.exists(Paths.get(CSV_FILE_PATH))) {
            Files.createDirectories(Paths.get("base"));
            Files.createFile(Paths.get(CSV_FILE_PATH));
        }
    }

    public static String[] checkCredentials(String username, String password) throws IOException {
        ensureFileExists();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String storedUsername = data[1];
                String storedPassword = data[2];
                String storedAccountType = data[3];
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return new String[]{storedAccountType}; // Return the account type
                }
            }
        }
        return null; // Return null if credentials not found
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        boolean accountLoop = false;
        while (!accountLoop) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            try {
                String[] accountTypeArray = checkCredentials(username, password);
                if (accountTypeArray != null) {
                    String accountType = accountTypeArray[0];
                    System.out.println("Credentials verified. Access granted.");
                    switch (accountType) {
                        case "0":
                            Accounts.personal();
                            break;
                        case "1":
                            Accounts.business();
                            break;
                        case "2":
                            Accounts.businessPlus();
                            break;
                        default:
                            System.out.println("Unknown account type.");
                    }
                    accountLoop = true; // Set accountLoop to true inorder to exit the loop
                } else {
                    System.out.println("Incorrect username or password. Access denied.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while checking credentials. Please try again.");
            }
        }
    }
    // this was written by adeyinka jedidiah adegalu
    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Fullname: ");
        String fullname = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String accountType = "";
        while (true) {
            System.out.println("What account would you like to register for...");
            System.out.println("0 personal");
            System.out.println("1 business (recommended)");
            System.out.println("2 business plus");
            System.out.print("Enter your choice: ");
            accountType = scanner.nextLine();
            if (accountType.equals("0") || accountType.equals("1") || accountType.equals("2")) {
                break;
            }
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.println(fullname + "," + username + "," + password + "," + accountType);
            System.out.println("User data has been saved to users.csv.");
            System.out.println("Registered");
            switch (accountType) {
                case "0":
                    Accounts.personal();
                    break;
                case "1":
                    Accounts.business();
                    break;
                case "2":
                    Accounts.businessPlus();
                    break;
                default:
                    System.out.println("Unknown account type.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while registering the user. Please try again.");
        }
    }
}
// this was written by adeyinka jedidiah adegalu