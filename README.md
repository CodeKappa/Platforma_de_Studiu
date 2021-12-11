# ProiectBD

## 1. Adding JDBC driver jar file to Java project.

### 1.1 Open project properties
-Select project then `ALT+ENTER` OR `right click on project` => `Properties` 

### 1.2 In the `Java Build path`, in the `Libraries Section` => `Classpath` => Click on `Add External Jars` => Select `mysql-connector-java-<version>.jar` from 
`\ProiectBD\mysql-connector-java-8.0.27` => `Open` => `Apply` => `Apply and Close`

## 2.Connect to your MySql Server and run the `testLogin.sql` from `\ProiectBD`

## 3. Modify the url, user and password in `\ProiectBD\main\MainClass.java` **(Warrning don't push your password if you don't want to)**

Step 2 from [https://www.knowprogram.com/jdbc/connect-mysql-database-eclipse/]
