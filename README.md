#How to run
1, Build the project by going to the project folder and execute the following maven command

<code>
mvn clean package
</code>

2, Go to the target folder and run the following command substituting {pathToSodokuFile} with the path to your sodoku file.
(There are some test files in test/resources folder) 

<code>
java -jar sodoku-1.0-SNAPSHOT-shaded.jar {pathToSodokuFile}
</code>

#How to generate report html including test report
1, Go to project folder and execute the following maven command

<code>
mvn site
</code>

2, Go to target/site folder and open index.html