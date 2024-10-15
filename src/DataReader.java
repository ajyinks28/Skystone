// this was written by adeyinka jedidiah adegalu
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    class CertificateDataReader {
        // Method to read certificate data from the CSV file
        public static List<String[]> readCertificateDataFromCSV(String fileName) {
            List<String[]> certificateData = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    certificateData.add(data);
                }
            } catch (IOException e) {
                System.err.println("Error reading CSV file: " + e.getMessage());
            }
            return certificateData;
        }
    }
}
// this was written by adeyinka jedidiah adegalu