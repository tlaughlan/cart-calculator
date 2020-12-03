## cart-calculator :file_folder: >> :dollar:

A simple CLI App used to process carts full of products from an online store.

### About

cart-calculator is a simple Maven project built in Java 8. The application takes two arguments, a cart file 
and a base prices file, both in json format. The app will match all of the products against their appropriate base 
prices and ultimately output an integer which represents the total price (in cents) of all products in the cart.

### Dependencies

cart-calculator leverages the following technologies:

- FasterXML Jackson API for json processing.
- Apache's log4j2 Library for logging services.
- JUnit testing framework.

All of these dependencies are managed by Maven and will be automatically downloaded during the build lifecycle. For this
reason the initial build of the application may take some time.

### Installation

First, ensure that your environment is running Java SE 1.8. A good java installation guide can be found 
[here](https://www.vultr.com/docs/how-to-manually-install-java-8-on-ubuntu-16-04). The application should work fine with
later version of Java as well, but it is intended for 8. Java 11 is known to trigger some warnings in log4j2.

Next you will need to install Apache Maven, which is used to build the project and run the test suites. A good Maven 
installation guide can be found [here](https://www.vultr.com/docs/how-to-install-apache-maven-on-ubuntu-16-04). If your 
environment is running a Linux distro which supports the Advanced Package Tool (apt) it is advised that you do NOT use 
it to install Maven, as it will try to install OpenJDK 11 first.

Finally, you need only clone down the cart-calculator repository into your favourite development directory and you 
should be good to go!

### Building the app

To build the app simply execute the maven command `mvn clean package` from within the root project directory 
(`/cart-calculator`).

This will cause Maven to download all of its dependencies and compile the source code into a generated `target` 
directory. The target directory should hold a jar file called `cart-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar`.

### Running the tests

All of the models and services in this repo are fully unit tested. These tests are executed as part of the Maven build 
lifecycle. If you would like to only run the tests instead of packaging the whole project, then use `mvn test`.

### Execution

Finally, executing the application is a piece of :cake:! Just go to the `target` folder and run the jar file, passing in 
your cart and base price file as parameters (in that order). For example:  

`java -jar cart-calculator-1.0-SNAPSHOT-jar-with-dependencies.jar ./cart-4560.json .base-prices.json`

Upon executing, the application will create a comprehensive log file in the generated `target/log` directory. This log 
outputs a useful commentary as the products as paired with their appropriate prices and their total costs are 
calculated. Any warnings or errors will also be sent to this log file.

### Time complexity

In Big O notation, the time complexity of this application would be described as ***O(n\*m)*** where ***n*** is the size 
of the cart file and ***m*** is the size of the base prices file.

The application implements the following strategies in order to reduce time complexity:

- Grouping base prices by product type. This greatly reduces the prices traversed per product.
- Exiting fast during the options comparison, `containsOptions`. There is no point comparing all of a base price's
options lists if one of them has already failed to satisfy the product's corresponding option.