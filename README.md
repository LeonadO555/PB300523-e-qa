## Selenium java framework

### Installation

- install [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- install [Gradle 8.5](https://gradle.org/releases/)
- install Allure:
  * 'brew install allure' for Mac
  * 'scoop install allure' for Windows
- install dependencies:
  * `./gradlew build`

### Run tests

### IMPORTANT ACTIONS
- For run test using testing need to do actions:
  1. Open Settings -> Build, Execution , Deployment -> Build Tools -> Gradle
  2. Change Build and run using to Intellij IDEA
  3. Change Run tests using to Intellij IDEA
  4. Apply and ok


- Run all tests `./gradlew clean test`

- Run tests using task `./gradlew clean nameofTask`(task name can be found in `build.gradle` file)


### Config file
- Config file is located in `src/main/resources/config.properties`
- Need to add project url
- selenoid.state - `false = local run`, `true = remore run`
- selenoid.url - `url to selenoid`

### Reports
- After running tests, you can find reports in `build/reports/allure-report` folder
- To open report, run `allure serve build/allure-results`

### Screenshots
- Screenshots are saved i `reference` folder
- Screenshots are taken when test fails
- To do screenshot is reference need to change screenshot name, remove `tmp_` from screenshot name
- Screenshots are attached to allure report

  