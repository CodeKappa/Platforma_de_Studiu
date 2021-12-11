# ProiectBD

## 1. Open project in Eclipse

- Eclipse => File => Open Projects from File System... => Directory... => `/PlaformaDeStudiu` => Select Folder 

## 2. Modify JDBC driver jar file path

- Open project properties => select project then `ALT+ENTER` OR `right click on project => Properties`
- In the Java Build path, in the Libraries Section => Classpath => Click on `mysql-connector-java-8.0.27.jar` => Edit =>
`/mysql-connector-java-8.0.27/mysql-connector-java-8.0.27.jar` => Open => Apply => Apply and Close

## 3. Create database for testing
- Connect to your MySql Server and run the `/testLogin.sql`

## 4. Basic details of the MySQL JDBC driver to develop the program
- Modify the url, user and password in `/PlatformaDeStudiu/src/main/MainClass.java` **(Warrning don't push your password if you don't want to)**

### Check [this](https://www.knowprogram.com/jdbc/connect-mysql-database-eclipse/) for more details
