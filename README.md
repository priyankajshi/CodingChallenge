# Auto-Tests

### Tools used:
* Maven
* Java
* TestNG
* Selenium
* RestAssured
* Extent Reports


### Important commands:
* Build project: mvn clean install
* Run tests: mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml -DbaseUrl=https://www.credify.tech/phone/nonDMFunnel -Dusername=candidate+101@upgrade-challenge.com -Dpassword=Tester123
* Path to HTML report: reports/testReport.html

