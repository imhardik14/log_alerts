# Getting Started

### System Requirements

* Java 1.8
* Maven 3.x (for compiling code and Jar file generation)

### Compile and Execution

* For compiling project using Maven ( logtestfile.txt in the project rootfolder)
	
	mvn clean install -Dlogfile.path=logtestfile.txt


* Once jar file has been generated you can run below command for executing code(sample logfile added in project root folder):
		
	java -jar -Dlogfile.path=logfile.txt target\log_alerts-0.0.1-SNAPSHOT.jar

### Next steps:
* Use of spring batch for saving objects in batch pool
* Enhancement of test cases
* Logger enhancements
* Performance Enhancement