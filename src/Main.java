// this was written by adeyinka jedidiah adegalu
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean hasLoggedIn = false;
        Scanner scanner = new Scanner(System.in);
        while (!hasLoggedIn) {
            ClearTerminal.clearTerminal();
            HomeGreet();
            String choice = scanner.nextLine();
            if (choice.equals("0")) {
                ClearTerminal.clearTerminal();
                Authenticate.login();
                hasLoggedIn = true;
            } else if (choice.equals("1")) {
                ClearTerminal.clearTerminal();
                Authenticate.register();
                hasLoggedIn = true;
            } else if (choice.equalsIgnoreCase("x")) {
                System.out.println("Are you sure you want to exit? (Y to affirm /to reject, press any other key): ");
                String ask = scanner.nextLine();
                if (ask.equalsIgnoreCase("y")) {
                    System.exit(0);
                }
            } else {
                ClearTerminal.clearTerminal();
                System.out.println("Input an option between 0, 1 or x");
            }
        }
        scanner.close();
    }


    private static void HomeGreet() {
        System.out.println("WELCOME TO SKYSTONE\n" +
                "Certificate generation made easy....\n" +
                "0 Login\n" +
                "1 Register\n" +
                "x Exit\n");
    }
}
// this was written by adeyinka jedidiah adegalu