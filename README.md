## Selenium java framework


### Installation

- install [java 17](https://www.oracle.com/java/technologies/downloads/)
- install [Gradle 8.5](https://gradle.org/releases/)
- install Allure:
   * 'brew install allure' for Mac
   * 'scoop install allure' for Windows
- install dependencies 
   * './gradlew build'


### Run tests

- For run tests using testng need to do actions

    1.Open Settings ->Build,Executions,Deployment ->Build Tools->Gradle
    2.Change Build and run  using to Intellij IDEA

    3.Change Run tests using to intellij IDEA
     
    4.Apply and Ok
- Run all tests `./gradlew clean test`

- Run tests using task `./gradlew clean nameOfTask`(task name can be found in `build.gradle` file)



### Config file

- Config file is located in `src/main/resoursec/config.properties`
- Need to add project url
- selenoid.state - `false=local run`,`true = remove run`
- selenoid.url - `url ro selenoid`

### Reporst

-After running tests,you can find reports in `build/reports/allure-report`folder
-To open report,run `allure serve build/allure-results`

### Screenshots

- Screenshots are saved in `reference folder`
- Screenshots are taken when test fails
- To do Screenshot is reference need to change Screenshot name ,remove `tmp_` from Screenshot name


