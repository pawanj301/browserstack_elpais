# browserstack_elpais
This repository is for the BrowserStack assignment to build a script that demonstrates skills in web scraping, API integration, and text processing using the Selenium framework and Java.

**The task to complete is defined in the steps below:**
1. Visit the website El País, a Spanish news outlet.
   - Ensure that the website's text is displayed in Spanish.
2. Scrape Articles from the Opinion Section:
   - Navigate to the Opinion section of the website.
   - Fetch the first five articles in this section.
   - Print the title and content of each article in Spanish.
   - If available, download and save the cover image of each article to your local machine.
3. Translate Article Headers:
   - Use a translation API of your choice, such as:
       - Google Translate API
       - Rapid Translate Multi Traduction API - USED
    - Translate the title of each article to English.
    - Print the translated headers.
4. Analyze Translated Headers:
    - From the translated headers, identify any words that are repeated more than twice across all headers combined.
    - Print each repeated word along with the count of its occurrences.
5. Cross-Browser Testing:
    - Run the solution locally to verify functionality.
    - Once validated, execute the solution on BrowserStack across 5 parallel threads, testing across a combination of desktop and mobile browsers.
    - Create a free trial account on BrowserStack to automate this test.

# Framework Details
A scalable TestNG-based automation framework built to perform both **UI and API testing** on [El País](https://elpais.com), leveraging **Selenium WebDriver**, **RestAssured**, **BrowserStack**, and **Maven** for parallel, cross-browser, and cross-device execution.
  - Hybrid framework combining **UI + API testing**
  - Modular **Page Object Model (POM)**
  - Dynamic locator handling for **desktop and mobile browsers**
  - Uses **BrowserStack** for real-device/browser cloud execution
  - API testing using **RestAssured** with payload and schema validation
  - Tests include:
  - Validating website language (Spanish)
  - Scraping articles from the Opinion section
  - Translating article headers using a third-party API
  - Word frequency analysis on translated headers
  - Supports **parallel execution** via `testng.xml`
  - Thread-safe WebDriver management
  - Centralized `TestContext` for environment and runtime variables
  - Custom configuration via `.properties` files

# Tech Stack
| Component     | Technology               |
|---------------|---------------------------|
| Test Framework| TestNG                    |
| UI Automation | Selenium WebDriver        |
| API Testing   | RestAssured               |
| Cloud Grid    | BrowserStack              |
| Build Tool    | Maven                     |
| Language      | Java                      |

# Steps to Run
- Run from cmdLine
  - git clone repo
  - cd BrowserStackTestProject
  - mvn test -DsuiteXmlFile=browserstack_test_suite.xml
- Run from Eclipse IDE
  - Right click on browserstack_test_suite.xml
  - Run As -> TestNG suite

# Configuration
  - browserstack.properties
    - browserstack.user=<username>
    - browserstack.key=<key>
  - env.properties
    - url=https://elpais.com/
  - rapid_translation_api.properties
    - base.url=https://rapid-translate-multi-traduction.p.rapidapi.com/t
    - api.key=<key>
    - api.host=rapid-translate-multi-traduction.p.rapidapi.com

# Author
  **Pawan Jain** | LinkedIn: https://www.linkedin.com/in/pawan-jain-a65703200/
