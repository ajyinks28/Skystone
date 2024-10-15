# Skystone

Welcome to Skystone, a command-line interface (CLI) certificate generation program. Skystone allows users to generate personalized certificates across three different account types: Personal, Business, and Business Plus. Each account type offers unique features designed to accommodate varying needs from individual users to large organizations.

## Account Types
Personal Account
Features:
  - Create one certificate at a time.

Business Account
Features:

Generate multiple certificates simultaneously.
Upload and use a CSV file to create certificates in bulk.

CSV File Requirements:
The CSV file must be formatted with the following columns: Date of Certificate Issuance, Certificate Holder's Name, Remark (e.g., "His Excellence in Math"), Signature, Certificate Giver, Certificate Body.
Note: you would have to input the csv file into the program directory

Business Plus Account
Features:

  Includes all features of the Business account.
   Additional capability to create and administer tests.
Test Creation:
 Users can add a test code and test name.
 Allows setting an unlimited number of questions.
Taking Tests:
  Users must enter a test code to access a specific test.
  If a matching test is found, the user can take the test.
  Test results are saved with the test code as a reference.

## Storage Directories
Certificates: Stored in the `certificates` folder.
Tests: Stored in the `tests` folder.
Test Results: Stored in the `test_results` folder.

This program offers a streamlined way for users to efficiently manage certificate issuance and testing, providing tools that cater to both individual and business needs. Whether creating a single certificate or managing tests for an entire organization, Skystone delivers a comprehensive solution.
