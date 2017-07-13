## Bus Route RESTful Web Service

This project implements a  RESTful web service that checks if there is a direct bus route connection between two given bus stations.
The bus route data file provided by the bus provider contains a list of bus routes. 
These routes consist of an unique identifier and a list of stations (also just unique identifiers).
A bus route **connects** its list of stations.
*Note: The station identifiers given in a query may not be part of any bus route!*

#### Bus Route Data

The first line of the data gives you the number **N** of bus routes, followed by
**N** bus routes. For each bus route there will be **one** line containing a
space separated list of integers. This list contains at least three integers. The
**first** integer represents the bus **route id**. The bus route id is unique
among all other bus route ids in the input. The remaining integers in the list
represent a list of **station ids**. A station id may occur in multiple bus
routes, but can never occur twice within the same bus route.

*Note: The bus route data file will be a local file and the service will get
the path to file as the first command line argument. The service will get
restarted if the file or its location changes.*

#### REST API

The REST service supports GET requests only.
It serves `http://localhost:8088/api/direct?dep_sid={}&arr_sid={}`.
The required parameters `dep_sid` (departure) and `arr_sid` (arrival) are two station ids (sid) represented by 32 bit integers.

The response is a single JSON Object:

```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "dep_sid": {
      "type": "integer"
    },
    "arr_sid": {
      "type": "integer"
    },
    "direct_bus_route": {
      "type": "boolean"
    }
  },
  "required": [
    "dep_sid",
    "arr_sid",
    "direct_bus_route"
  ]
}
```

The `direct_bus_route` field would be set to `true` if there exists a bus route in the input data that connects the stations 
represented by `dep_sid` and `arr_sid`, otherwise it would be set to `false`.

#### Example Data

Bus Routes Data File:
```
3
0 0 1 2 3 4
1 3 1 6 5
2 0 6 4
```

Query:
```
http://localhost:8088/api/direct?dep_sid=3&arr_sid=6
```

Response:
```
{
    "dep_sid": 3,
    "arr_sid": 6,
    "direct_bus_route": true
}
```

## Requirements

* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later
* [Maven 3.0+](http://maven.apache.org/download.cgi)

## Build with Maven

* [Welcome to Apache Maven](https://maven.apache.org/)
* [Building Java Projects with Maven](https://spring.io/guides/gs/maven/)

## Build and run tests with Maven

* cd into project-root-folder using the terminal.

* Run this maven command:
 
``` 
mvn clean test
``` 

## Build, run and execute smoke tests on the service

* cd into project-root-folder using the terminal.

* Run these commands:

```
./service.sh                     : prints usage
./service.sh dev_build           : builds the app
./service.sh dev_run FILE_PATH   : runs the app
./service.sh dev_smoke           : runs our smoke tests on the machine
```
**Note:** FILE_PATH is the path to the local file. If the path has spaces then add quotation marks.

## Test the service

Start the service and visit:

```
http://localhost:8088/api/direct?dep_sid=3&arr_sid=6
```