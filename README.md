# mplatform-qa-automation

* This repo is used as the core of our automation framework.

* In this framework we are applying Page Object Model design pattern where Object Repository is created (Model classes) for web UI elements.
* For each web page in the application, there should be corresponding page class. This Page class will find the WebElements of that web page.
* There are controller classes which contain page methods which perform operations on corresponding WebElements in model classes.
* There is also a Data classes which will held all input values coming from external source (.xls files).
* Test classes will contain a collection of TestMethods/TestCases that are related to the targeted Scenario/Feature.

```
** Which means, each page in the application has one model class, one controller class and one data class if required.
** Test classes will fetch methods from various controllers.
** In each test method there is a line of code that redirects fetching the input values from .xls in a flexible manner where rows and columns can be changed base on the need.
```

* This project is designed to reduce manual effort in testing mPlatform apps by Automating Stories, testcase and repeated bugs. It is not to entirely replace manual testing as some testcase are not subjected to be automated.

## Getting Started

In order to get a copy of the project, clone the project from the respective git repository at your desired local path.

```bash
git clone https://github.com/GroupM-mPlatform/mplatform-qa-automation.git
```

### Prerequisites

This project should be running on local environments after fulfilling the following Prerequisites

```
1. Latest version of JDK 8. [TJDK 8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Latest version of maven. [maven] (https://maven.apache.org/download.cgi)
3. IDE of your choice. [eclipse] (https://www.eclipse.org/downloads/?)

Note: maven pom.xml should take care of required version of the following:

1. Selenium WebDriver
2. TestNG
3. All required depencies
```

### Installing

1. After having all Prerequisites then cloning the project, everything should work smoothly.
2. Make sure to include unnecessary files to .gitignore file


```
Example for files/Directories that should be placed in .gitignore --> testResults, log files,,,,etc
```


## Project Config & Running the tests


|       Main Directories    | Packages          |                                                Description                                                    |
| ------------------------- |:-----------------:| -------------------------------------------------------------------------------------------------------------:|
| src/main/java(Framework)  | base              | contains infrastructure of the framework, starting and ending points of the execution, all reusable functions |
|                           | model             | acting as Object Repository where all WebElements' locators of the page will be collected                     |
|                           | controller        | contains page methods which perform operations on corresponding WebElements in model classes                  |
|                           | data              | which will held all input values coming from external source (.xls files)                                     |
|                           |                   |                                                                                                               |
| src/test/java(Test Suites)| test              | collection of TestMethods/TestCases and will fetch methods from various controllers                           |
|                           |                   |                                                                                                               |
| Results                   | html test results | all test results will be loaded there with time consumed, validation, screenshots (Modulewise and Stepwise)   |
|                           |                   |                                                                                                               |
| xml test suites config    | ex: smoke.xml     | Contains parameters that set the configuration for each test suite ex: browser type and classes to execute    |

```bash
from command line Use following command to run test: mvn clean test -DsuiteXmlFile="SmokeTest"
From IDE, right click to desired xml file to run test
```



## Deployment & Contributing

Each contributor is expected to work on a branch that represent current assigned feature, a pull request should be created to prepare merging to master branch after code review

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

Current version of the framework: mPlatfrom_0.0.1

## Authors

* **Ahmed Neinae** - *Initial work* -

See also the list of [contributors](https://github.com/GroupM-mPlatform/mplatform-qa-automation/graphs/contributors) who participated in this project.