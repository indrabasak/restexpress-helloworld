[![Build Status][travis-badge]][travis-badge-url]

RestExpress Hello World Example
===================
This is  a simple **Customer Service** example using [**RestExpress**](https://github.com/RestExpress/RestExpress). RestExpress is a Java REST framework based on [**Netty**](http://netty.io/). 

# Build
Execute the following command from the parent directory to compile the project:

```
mvn clean package
```
Once the build completes successfully, you should have the artifact `restexpress-helloworld-1.0.jar` in the `target` folder.

# Run from Command Line
To run the JAR from command line:
```
java -jar restexpress-helloworld-1.0.jar
```

# Run from Eclipse IDE
Run **CustomerMain** class as Java Application

# Test

#### GET Operation
URL: `http://localhost:9000/customers/1`
HTTP Method: GET
Header: Accept: application/json

Expected Response Body:
```json
{
  "id" : 1,
  "firstName" : "Robert",
  "lastName" : "Land",
  "address" : {
    "street" : "2486 Maxwell Farm Road",
    "city" : "Waynesboro",
    "state" : "VA",
    "zipCode" : "22980"
  }
}
```    

####  POST Operation 
URL: `http://localhost:9000/customers`
HTTP Method: POST
Header: 
Content-Type: application/json
Accept: application/json

Request Body:
```json
{
  "firstName" : "John",
  "lastName" : "Doe",
  "address" : {
    "street" : "123 Nowhere Street",
    "city" : "Notown",
    "state" : "MA",
    "zipCode" : "02456"
  }
}
```

Expected Response Body:
```json
{
  "id" : 4,
  "firstName" : "John",
  "lastName" : "Doe",
  "address" : {
    "street" : "123 Nowhere Street",
    "city" : "Notown",
    "state" : "MA",
    "zipCode" : "02456"
  }
}
```

#### DELETE Operation 
URL: `http://localhost:9000/customers/4`
HTTP Method: POST
Header: 
Content-Type: application/json
Accept: application/json

Expected Response Body:
```json
{
  "id" : 4,
  "firstName" : "John",
  "lastName" : "Doe",
  "address" : {
    "street" : "123 Nowhere Street",
    "city" : "Notown",
    "state" : "MA",
    "zipCode" : "02456"
  }
}
```

#### UPDATE Operation 
URL: `http://localhost:9000/customers/1`
HTTP Method: PUT
Header: 
Content-Type: application/json
Accept: application/json
Request Body:
```json
{
  "firstName" : "Robert",
  "lastName" : "Redford",
  "address" : {
    "street" : "2486 Maxwell Farm Road",
    "city" : "Waynesboro",
    "state" : "VA",
    "zipCode" : "22980"
  }
}
```

Expected Response Body:
```json
{
  "id" : 1,
  "firstName" : "Robert",
  "lastName" : "Redford",
  "address" : {
    "street" : "2486 Maxwell Farm Road",
    "city" : "Waynesboro",
    "state" : "VA",
    "zipCode" : "22980"
  }
}
```

#### PARTIAL UPDATE Operation 
URL: `http://localhost:9000/customers/1`
HTTP Method: PATCH
Header: 
Content-Type: application/json
Accept: application/json

Request Body:
```json
{
  "firstName" : "John"
}
```
Expected Response Body:
```json
{
  "id" : 1,
  "firstName" : "John",
  "lastName" : "Redford",
  "address" : {
    "street" : "2486 Maxwell Farm Road",
    "city" : "Waynesboro",
    "state" : "VA",
    "zipCode" : "22980"
  }
}
```
    
 [travis-badge]: https://travis-ci.org/indrabasak/restexpress-helloworld.svg?branch=master
 [travis-badge-url]: https://travis-ci.org/indrabasak/restexpress-helloworld/  