import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
// this was written by adeyinka jedidiah adegalu
class Certificate {
    private String date;
    private String certificateHolderName;
    private String remark;
    private String signature;
    private String certificateGiver;
    private String certificateBody;

    public Certificate(String date, String certificateHolderName, String remark, String signature, String certificateGiver, String certificateBody) {
        this.date = date;
        this.certificateHolderName = certificateHolderName;
        this.remark = remark;
        this.signature = signature;
        this.certificateGiver = certificateGiver;
        this.certificateBody = certificateBody;
    }
    // this was written by adeyinka jedidiah adegalu

    public void generateCertificate(String fileName) {
        String certificateText = String.format("%s%n%nThis certificate is presented to %s for %s%n%nSignature: %s%nIssuer: %s%nCompany: %s",
                date, certificateHolderName, remark, signature, certificateGiver, certificateBody);

        // This creates the directory if it does not exist
        try {
            Files.createDirectories(Paths.get("certificates"));
        } catch (IOException e) {
            System.err.println("Error creating directory: " + e.getMessage());
            return;
        }

        // Write the certificate text to a .txt file
        try (FileWriter writer = new FileWriter("certificates/" + fileName + ".txt")) {
            writer.write(certificateText);
            System.out.println("Certificate saved to certificates/" + fileName + ".txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}

public class Accounts {
    public static void personal() {
        System.out.println("Personal");

        Scanner scanner = new Scanner(System.in);

        // Get user input for certificate details
        String dateChoice = Certificate.getUserInput("Use current date? (yes/no): ");
        String date;
        if (dateChoice.equalsIgnoreCase("yes")) {
            date = Certificate.getCurrentDate();
        } else {
            date = Certificate.getUserInput("Enter date (yyyy-MM-dd): ");
        }

        String certificateHolderName = Certificate.getUserInput("Enter certificate holder name: ");
        String remark = Certificate.getUserInput("Enter remark: ");
        String signature = Certificate.getUserInput("Enter signature: ");
        String certificateGiver = Certificate.getUserInput("Enter certificate giver: ");
        String certificateBody = Certificate.getUserInput("Enter certificate body: ");

        // Get user input for the certificate file name
        String fileName = Certificate.getUserInput("Enter a name for the certificate file (without extension): ");

        // Create the certificate
        Certificate certificate = new Certificate(date, certificateHolderName, remark, signature, certificateGiver, certificateBody);
        certificate.generateCertificate(fileName);
    }

    public static void business() {
        System.out.println("Business");

        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("What would you like to do?");
            System.out.println("0 - Create a certificate");
            System.out.println("1 - Upload CSV file (to create multiple certificates)");
            System.out.println("2 - Exit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    personal();
                    break;
                case "1":
                    System.out.println("What is the name of your CSV file?");
                    String groupFile = scanner.nextLine();
                    List<String[]> certificateData = DataReader.CertificateDataReader.readCertificateDataFromCSV(groupFile + ".csv");

                    if (certificateData != null) {
                        for (String[] data : certificateData) {
                            if (data.length >= 6) {
                                Certificate certificate = new Certificate(data[0], data[1], data[2], data[3], data[4], data[5]);
                                String fileName = data[1].replaceAll("\\s+", "_").toLowerCase();
                                certificate.generateCertificate(fileName);
                            } else {
                                System.err.println("Invalid data format for certificate entry.");
                            }
                        }
                    } else {
                        System.err.println("Error reading data from CSV file.");
                    }
                    break;
                case "2":
                    System.out.println("Exiting...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 0, 1, or 2.");
                    break;
            }
        }

        scanner.close();
    }
// this was written by adeyinka jedidiah adegalu
    public static void businessPlus() {
        System.out.println("Business Plus");

        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("What would you like to do?");
            System.out.println("0 - Create a certificate");
            System.out.println("1 - Upload CSV file (to create multiple certificates)");
            System.out.println("2 - Create a test)");
            System.out.println("3 - Take test");
            System.out.println("4 - Exit");
            String choice = scanner.nextLine();
            // this was written by adeyinka jedidiah adegalu

            switch (choice) {
                case "0":
                    personal();
                    break;
                case "1":
                    business();
                case "2":
                    // create test
                    TestFunction.createTest();
                case "3":
                    // do test
                    TestFunction.takeTest();
                case "4":
                    System.out.println("Exiting...");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 0, 1, 2, 3 or 4.");
                    break;
            }
        }
        scanner.close();
    }
}

