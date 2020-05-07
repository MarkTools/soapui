According to the business requirements, I developed the source code of soapUI twice. Specific business requirements: batch generation of request message with certain format according to wsdl locations, which is impossible for soapUI source code. Please read readme for details.
## Build and run
* Please follow the source code of [soapui](https://github.com/SmartBear/soapui) to build and run.
* I used [IntelliJ IDEA](https://www.jetbrains.com/idea/) to open project. You can skip the test to install or run when you compile and report some errors.
* On the basis of the source code, it integrates mybatis to read and write operation of the database, so it is necessary to introduce the related dependency of mybatis and mysql, and update mybatis-config.xml, database.properties of yourself.
## Update and add
The source code of soapui has five modules, but we only need to give attention to [soapui](soapui)-The core module that creates the soapui.jar file.
* First of all, I added one package under main/Java, [com.founder.soapui](soapui/src/main/java/com/founder/soapui), encapsulate the methods in the source code to parse the wsdl.
* Secondly, a folder, [com.founder.soapui](soapui/src/main/resources/com.founder.soapui), is added under resource to store configuration files and mybatis mapping files.
* Thirdly, added one package under test/Java, [com.founder.soapui](soapui/src/test/java/com/founder/soapui), to test of parsing wsdl.
