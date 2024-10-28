# SauceDemoTest

**Project Overview**

Objective: Perform comprehensive automated testing on https://www.saucedemo.com/ using Selenium and TestNG, with results documented in GitHub
Scope: Focus on the main functionalities of the e-commerce platform, such as login, product search, cart management, and checkout processes.

**Test Strategy**
Testing Type(s): Functional Testing
Test Automation Tools: Selenium WebDriver for automation, TestNG for test execution and reporting.
Version Control: GitHub will be used for tracking changes and showcasing the project.
Browsers Tested: Chrome, Firefox , Edge.

**Test Environment**
Browsers: Chrome, Firefox, Edge.
Operating System: Windows 11
Automation Tools: Selenium WebDriver, TestNG, GitHub.

**Test Cases**

**Test Case 1: User Login**
Test Objective: Verify that users can log in with valid credentials.
Steps:
- Navigate to https://www.saucedemo.com/.
- Enter a valid username and password.
- Click on the login button.
- Repeat for all valid usernames
  
Expected Result: User should be logged in successfully and redirected to the products page.

Test Data: 
Valid usernames: 
standard_user
locked_out_user
problem_user
performance_glitch_user
error_user
visual_user
Valid password:
secret_sauce

**Test Case 2: Add Product to Cart**
Test Objective: Verify that products can be added to the shopping cart.
Steps:
- Log in to the website.
- Click on a product to view its details.
- Click on the "Add to Cart" button.

Expected Result: The product should appear in the cart.

**Test Case 3: Remove Product from Cart**
Test Objective: Verify that items can be removed from the cart.

Steps:
- Log in and add a product to the cart.
- Navigate to the cart page.
- Click on the "Remove" button for the product.
  
Expected Result: The product should be removed from the cart.

**Test Case 4: Checkout Process**

Test Objective: Verify that the user can complete the checkout process.
Steps:
- Log in, add products to the cart, and proceed to checkout.
- Fill out checkout details (first name, last name, postal code).
- Click "Finish" to complete the purchase.
  
Expected Result: A confirmation message should appear indicating the purchase was successful.

**Test Case 5: Logout Functionality**

Test Objective: Verify that users can log out successfully.
Steps:
- Log in to the website.
- Click on the "Menu" button and then "Logout."

Expected Result: The user should be redirected to the login page

**Test Suites**

- Login Suite: Includes Test Case 1.
- Shopping Cart Suite: Includes Test Cases 2 and 3.
- Checkout Suite: Includes Test Case 4.
- User Management Suite: Includes Test Case 5.

**Test Criteria**

Entry Criteria: All test cases are automated, and the test environment is set up.
Exit Criteria: All test cases have been executed with at least 95% pass rate. Failed cases have been logged as issues.

**Test Execution**
Execution Plan:
- Run all test suites sequentially for each browser (Chrome, Firefox and Edge).
- Use TestNG’s parallel execution feature to speed up cross-browser testing.

**Test Reporting**

- TestNG Reports: Generate TestNG reports after each test run.
- GitHub Documentation: Document test results and automation progress in the GitHub repository.
- Defects/Issues Tracking: Log failed tests and issues found during execution as GitHub issues

**Deliverables**

- Automated Test Scripts: Push to GitHub.
- Test Plan and Results Documentation: Available in the GitHub repository as markdown files.
- Defect Reports: Tracked as issues in the GitHub repository.

Additional Considerations
- Code Reviews: Conduct code reviews for test scripts to ensure quality.
- Browser Compatibility: Test on multiple browsers for better coverage.












