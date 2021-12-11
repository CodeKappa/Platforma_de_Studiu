# ProiectBD

## 1. Add JDBC driver jar file to Java project.

- Open project properties => select project then `ALT+ENTER` OR `right click on project` => `Properties` 
- In the `Java Build path`, in the `Libraries Section` => `Classpath` => Click on `Add External Jars` => Select `mysql-connector-java-<version>.jar` from 
`\ProiectBD\mysql-connector-java-8.0.27` => `Open` => `Apply` => `Apply and Close`

## 2. Create database for testing
- Connect to your MySql Server and run the `testLogin.sql` from `\ProiectBD`

## 3. Basic details of the MySQL JDBC driver to develop the program
- Modify the url, user and password in `\ProiectBD\main\MainClass.java` **(Warrning don't push your password if you don't want to)**

## Check [this](https://www.knowprogram.com/jdbc/connect-mysql-database-eclipse/) for more details
