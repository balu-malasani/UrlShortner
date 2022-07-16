# UrlShortner Project

This project is created to generate shorten url for the given urls.
Supported Use cases:
1. User can access home page and it will load form with input field and submit/reset buttons.
2. User can enter long url and generate shorten url by using submit button or user can reset data using reset button.if form submitted successfully then user will be redirected to results page with original and shorten url details.
3. If user access shorten url on browser then it's is redirected to the original URL.
4. If user given same url multiple times system will return shorten Url which is generated first time.

Ex: 

If user enter https://localhost:8080/welcome then system wil generate http://localhost:8080/a . 

if user hit http://localhost:8080/a on browser then system will redirect to actual url https://localhost:8080/welcome

## GIT location : 

https://github.com/balu-malasani/UrlShortner.git

## Framework & Technologies implemented

This application is built using Springboot with below modules:
* Sprig framework (Springboot, SpringWeb, thymeleaf , Annotations)
* H2DB local In-memory DB
* Mockito Junit Test Cases
* Apache Maven for build and deployments
* Standard HTML.

## Project structure and implementation details

* main java classes focusing on the MVC architecture appraoch 
    * controllers
    * services + services Implementations
    * Entity Objects
    * Model
    * Util
    * Repository
* Test Cases:
    * Junit Mockito

* Resources for Html files


## Build Instructions 

To build all the modules run in the project root directory the following command with Maven :
    
    mvn clean install

If the build generated successfully, It will generate war file in target folder. 

The war file name : UrlShortner-0.0.1-SNAPSHOT.war

To run the application please use the below command at project root directory

java -jar  /target/UrlShortner-0.0.1-SNAPSHOT.war

Ex: java -jar D:/work/spring/UrlShortner/target/UrlShortner-0.0.1-SNAPSHOT.war 

To open home page open Url http://localhost:8080 on browser(By default application running on port 8080)





