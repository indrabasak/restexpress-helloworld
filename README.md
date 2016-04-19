RestExpress Hello World Example
===================
This is  a simple **Customer Service** example using [**RestExpress**](https://github.com/RestExpress/RestExpress). RestExpress is a Java REST framework based on [**Netty**](http://netty.io/). 

----------

#### To Compile the project
> mvn clean package

#### To Run the project
> java -jar restexpress-helloworld-1.0.jar

#### To Run from Eclipse IDE
Run **CustomerMain** class as Java Application

#### Testing GET operation
URL: http://localhost:9000/customers/1
HTTP Method: GET
Header: Accept: application/json

    Expected Response Body:
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

####  Testing POST operation 
URL: http://localhost:9000/customers
HTTP Method: POST
Header: 
Content-Type: application/json
Accept: application/json

Request Body:

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

Expected Response Body:

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

#### Testing DELETE operation 
URL: http://localhost:9000/customers/4
HTTP Method: POST
Header: 
Content-Type: application/json
Accept: application/json

Expected Response Body:

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

#### Testing UPDATE operation 
URL: http://localhost:9000/customers/1
HTTP Method: PUT
Header: 
Content-Type: application/json
Accept: application/json
Request Body:

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

Expected Response Body:

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

#### Testing PARTIAL UPDATE operation 
URL: http://localhost:9000/customers/1
HTTP Method: PATCH
Header: 
Content-Type: application/json
Accept: application/json

    Request Body:
    {
      "firstName" : "John",
    }

Expected Response Body:

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