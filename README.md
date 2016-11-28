# Menu App (REST webapp using Dropwizard and Guice)

## Introduction

**Menu App** is a backend system which aims to provide a basic menu component to be consumed by any client using **REST** (or any other communication protocol).
It's a prototype of a micro service web app using the **Dropwizard** framework and **Guice** for DI. And also it's a template for a better separation of concerns for a web app.

The chosen arquitecture is a N-Tier layout from JavaEE apps: **Presentation** - **Business** - **ORM**, and is divided into the next modules:
	**api**, **core**, **protocol**, and **client**.
	
On **Presentation** tier we have the **protocol** module which defines the data layout the api delivers to any client, and the **api** module which defines the exposed resources for client communication.

On **Business** tier we have the **core** module which defines the domain components and the interactions between them and with the **ORM** tier.

On **ORM** tier we also have the **core** module which additionally defines the repositories and daos, which in essence is the mapping between domain components and persisted entities.

Modules
-------

- The **api** project is the application by it self. 
It's a standalone executable jar which serves REST calls with JSON-like responses under a Jetty embedded container.
This project defines every resources' URL the system exposes.
Built using Maven shade plugin to generate an uber jar which facilitates the distribution of the app since no dependencies must be installed at destination.

- The **protocol** project provides the mapping between the requests and responses with those components the api can understand.
The mapping is via representational elements so we avoid messing up the business components with annotations and/or conversion logic. 
Here you define your custom parsers and what data you expect to receive/send for every REST call.
Also you can put any validation you want whether at receiving a request or at generating a response. Currently the project uses `org.hibernate.validator.*`, `javax.validation.*`, and custom annotated validations.

- The **core** project exposes the Service layer, and handles the communication downwards to the persistence layer of data via a Repository layer.
The Service layer only interacts with the many repositories in order to create, read, update, and delete data (CRUD). It contains stateless services which are managed by the DI api (Guice).
The Repository layer aims to separate the entities the business understands and the different ways they are persisted.
The Repository layer uses DAO components to interact with the underlying persistence backend.

- The **client** project is aimed to be used by third party components. Hiding the communication with the api, here you can add facades whenever a third party component can't understand JSON and needs another communication format.

Technologies
------------
- Java 8
- Dropwizard 1.0.5
- Guice 4.1.0
- Dropwizard-Guicey 4.0.1
- Maven 3.3.9
- CouchbaseMock test server
- Dropwizard-Couchbase 0.2.3

Building CouchbaseMock Test Server
----------------------------------
This project comes with the CouchbaseMock Test Server which serves as a simple Couchbase DB.
See the project at [CouchbaseMock](https://github.com/couchbase/CouchbaseMock) for more details.
You must first generate the package and then execute the server:
```sh
cd CouchbaseMock-master
mvn clean package
java -jar target/CouchbaseMock-1.4.3.jar -h localhost -p 8091 -b default::couchbase
```
Above command starts the Couchbase Test Server with default bucket, no password, and couchbase as bucket type.
Test everything is OK connecting to [http://localhost:8091/pools/default/buckets](http://localhost:8091/pools/default/buckets).

For command line options use:
```sh
java -jar target/CouchbaseMock-1.4.3.jar -?
```

Building app and setup of Eclipse projects
------------------------------------------
You can compile using next maven profiles: `local` (default), `test`, `stage`, or `prod`.
Each of them presents different configurations according the target environment you want to deploy at.
Pay attention that `prod` profile truncates the filtered resource so it fails fast. You must provide your own production ready config file. See next section.
```sh
cd menuapp
mvn clean install
mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true
```

Execution in any environment
----------------------------
You need to first start the CouchbaseMock test server. See above.

#### Quickstart

Remember you can use maven profiles adding `-P<profile.name>`. By default maven takes the `local` profile.
```sh
cd menuapp
mvn clean install
cd api
java -jar target/api-1.0.0-SNAPSHOT.jar server -
```
Listening requests on port 8080 as per profiles `local` (default), `test`, or `stage`.

#### Production environment

For production environment you must build with `-Pprod` profile and then provide your own production ready `server-config.yml` file. For example:
```sh
cd menuapp
mvn clean install -Pprod
cd api
java -jar target/api-1.0.0-SNAPSHOT.jar server /etc/server-config-prod.yml
```

#### Debug using Eclipse:

Make sure your `MAVEN_OPTS` contains `-Xmx512m -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n`
```sh
cd menuapp
mvn clean install
cd api
mvn exec:java -Dexec.args="server -"
```
Then open Eclipse and go to Run -> Debug Configurations -> create a Remote Java Application listening to port 4000 and hit Debug.

Build info for Continuous Integration
-------------------------------------
By default when compiling any project there is a maven plugin named `buildnumber-maven-plugin` which gets revision number, current branch, build time, and use that info on filtering resources phase.
Currently only the *api* projects use the build info provided by the plugin.
So you can call http://localhost:8080/buildinfo and be responded with revision id and branch name information of the last change made in the project, as well as the build time.
During development, some settings of this plugin are disabled in order to speed up compilation. Nevertheless you can completely turn it off using `-Dmaven.buildNumber.skip=true`
Conversely, when using a CI software use the next command in order to always retrieve latest code and avoid compilation if local changes has been made:
```sh
mvn clean install -Dmaven.buildNumber.doCheck=true -Dmaven.buildNumber.doUpdate=true
```

Example URLs
------------
Just using GET method.

[http://localhost:8080/buildinfo](http://localhost:8080/buildinfo)

[http://localhost:8080/profile](http://localhost:8080/profile)

[http://localhost:8080/user/1/menu](http://localhost:8080/user/1/menu)

[http://localhost:8080/user/1/menu/2](http://localhost:8080/user/1/menu/2)

The app loads a set of dummy menus, which are created at class `PreloadedMenuDao`. This class is managed as a bean, and is injected into the menu repository which is also injected into the menu service.
The dependency injection is all setup in the api module. Take a look at `org.fabri1983.menuapp.api.provide` package.

More example URLs
-----------------
Using POST method.

Note: add `Content-Type:application/json` and `Accept:application/json,text` in your REST Client plugin at header section.

	POST http://localhost:8080/user/login
    {
      "userName": "johnz",
      "userPassHashed": "123AARtiy56DDdcsK98d9gi",
      "location": {
        "latitude": 15546568450,
        "longitude": 85345243570
      }
    }
	
	POST http://localhost:8080/user/1/menu/group
    {
      "maxResults": 10,
      "groupData": {
        "priceFrom": 150,
        "priceTo":  850,
        "currency": "DEFAULT_USD"
      }
    }
	
	POST http://localhost:8080/user/1/menu/filter
    {
      "maxResults": 10,
      "filterData": {
        "maxPrice": 100,
        "currency": "DEFAULT_USD"
      }
    }
	
	POST http://localhost:8080/user/1/menu/filter
    {
      "maxResults": 10,
      "filterData": {
        "maxPrice": 100,
        "currency": "DEFAULT_USD",
        "hourFrom": "20:00:00",
        "hourTo": "23:00:00",
        "availableDays": [
            "Friday",
            "Saturday",
            "Sunday"
        ],
        "availableDateFrom": "2016-03-01T00:00:00",
        "availableDateTo": "2016-04-01T23:59:59"
      }
    }
	
	POST http://localhost:8080/user/1/menu/1/rate
	{
	  "rating": 1,
	  "description": "The description of the rating isn't stored yet"
	}
	
TODO list
---------
* Possible duplication of resources injection. Next message appears twice in console: io.dropwizard.server.SimpleServerFactory: Registering jersey handler with root path prefix

* Investigate maven shade plugin output for collisions on dependencies.

* Use of maven scope `provided`.

* Investigate the use of Transactions (UnitOfWork) with Dropwizard.

* Define exceptions and handle them in Service layer to provide upper layers human readable messages. Then according the type of exception use the correct HTTP return code.

* Use of `Optional<T>` to hide null manipulation.

* When a service needs several execution steps, use `Chain of Responsibility` with a chain builder or alike to improve readability.

* For currency conversion use `Strategy` pattern, so I can provide an algorithm for any currency conversion.

* Unit test out the many resources, services, parsers, converters, etc.

* Revisit the list of `FIXMEs` and `TODOs`.
