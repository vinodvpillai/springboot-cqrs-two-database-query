# Spring boot CQRS - Two database (Query Service)
CQRS suggests dividing the Application Layer into two sides, the commands side, and the queries side.
- The queries side should be responsible and optimized for reading data. 
- The commands side should be responsible and optimized for writing data.

In the **Two-database** approach, we have two dedicated databases, one for writing operations and one for reading operations. 

Commands side has Write Database optimized for writing operations. Query side has Read Database optimized for reading operations.

The **POC Query Service** - With every state changed by the command, the modified data has to be pushed from Write Database into the Read Database using the Events (using the  Eventual Consistency Pattern).

##
### Prerequisites
- JDK 1.8
- Maven
- Redis
- AWS - SQS (Name: SQS_Customer_Created)

## Quick Start

### Clone source
```
git clone https://github.com/vinodvpillai/springboot-cqrs-two-database-query.git
cd springboot-cqrs-two-database-query
```

```
Redis START
```

### Build
```
mvn clean package
```

### Run
```
java -jar target/springboot-cqrs-two-database-query.jar
```

### Endpoint details:

##### 1. CustomerQueryRestController - Get customer by ID (CURL Request):

```
GET /customers/1 HTTP/1.1
 Host: localhost:8080
 cache-control: no-cache
 Postman-Token: d17c8bcb-21d1-4e5b-8387-47e369c19913
```