# Opsly backend

Sample Project which loads all feed in one JSON from several social networks incudling:

1. Facebook;
2. Twitter;
3. Instagram

## REQUIREMENTS

Java 11

Maven 3

## How to use

1. Move to projects root directory (where pom.xml is located);
2. Run command ``` mvn clean install ``` (if you want to run it without unit tests run following command ``` mvn clean install -DskipTests=true ```);
3. Move to projects target directory;
4. Run command ``` java -jar opsly-backend-test-0.0.1.jar ```;
5. Open any browser and enter ``` localhost:3000 ``` (Or you can run it via ``` curl localhost:3000 ```);

## How to use (From IDE)

This project can be imported in any Java IDE
1. Open ``` com.opsly.newsfeed.app.Application.java ```;
2. Open any browser and enter ``` localhost:3000 ``` (Or you can run it via ``` curl localhost:3000 ```);


## Unit and Integration tests

There are several test cases In projects ``` src/test/java ``` directory. Tests are grouped in ``` RequestProcessingTests.java ``` and ``` SimpleTests.java ```

## About controller

Main controller class (``` FeedController.java ```) is located at following package ``` com.opsly.newsfeed.controllers ```.
