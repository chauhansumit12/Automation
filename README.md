**How to run**

This is a maven java project . To run first you need to compile and package as jar. Navigate to to directory zipproject and execute this maven command to package as jar `mvn clean install -Dskiptests`.
Create jar could be found under directory Automation/zipproject/target with name *zipproject-1.0-SNAPSHOT.jar* .

To execute this jar , run following command `java -jar zipproject-1.0-SNAPSHOT.jar` . 
Make sure you have config.properties file placed in the directory from where you invoke jar if you want to use custom input and output directory names, else make sure you use `input` as directory name to keep input zip files. Output would be writtent to `output` directory by default.
