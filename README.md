# Menu App (REST webapp using Dropwizard and Guice)

**Menu App** is a backend system which aims to provide a basic menu component to be consumed by any client using **REST** (or any other communication protocol).
It's a prototype of a micro service web app using the **Dropwizard** framework and **Guice** for DI. And also it's a template for a better separation of concerns for a web app.

The chosen arquitecture is a N-Tier layout from JavaEE apps: **Presentation** - **Business** - **ORM**, and is divided into the next modules:
	**api**, **core**, **protocol**, and **client**.
	
On **Presentation** tier we have the **protocol** module which defines the data layout the api delivers to any client, and the **api** module which defines the exposed resources for client communication.

On **Business** tier we have the **core** module which defines the domain components and the interactions between them and twith the **ORM** tier.

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
Also you can put any validation you want whether when receiving a request or when generating a response. Currently the project uses `org.hibernate.validator.*` and `javax.validation.*`.

- The **core** project exposes the Service layer, and handles the communication downwards to the persistence layer of data via a Repository layer.
The Service layer only interacts with the many repositories in order to create, read, update, and delete data (CRUD). It contains stateless services which are managed by the DI api (Guice).
The Repository layer aims to separate the entities the business understands and the different ways they are persisted.
The Repository layer uses DAO components to interact with the underlying persistence backend.

- The **client** project is aimed to be used by third party components. Hiding the communication with the api, here you can add facades whenever a third party component can't understand JSON and needs another communication format.

Technologies
------------
	Java 8
	Dropwizard v1.0.3
	Guice v4.1.0
	Dropwizard-Guice v1.0.0.2
	Maven v3.3.9
	
Building and setup of Eclipse projects
--------------------------------------
	cd menuapp
	mvn clean install -Plocal
	mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true
	
Execution in local environment
------------------------------
	cd menuapp
	mvn clean install -Plocal
	cd api
	java -jar target/api-1.0.0-SNAPSHOT.jar server target/server-config.yml
	Listening requests on port 8090. You can provide your own server-config.yml file.

If you want to debug the code using Eclipse:

	make sure your MAVEN_OPTS has -Xmx1g -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n
	cd api
	mvn exec:java -Dexec.args="server target/server-config.yml" -Plocal
	Then open Eclipse -> Run -> Debug Configurations -> create a Remote Java Application listening to port 4000 and hit Debug.
	
Example URLs
------------
http://localhost:8090/test

http://localhost:8090/user/1/menu

http://localhost:8090/user/1/menu/2

The app loads a set of dummy menus, which are created at class `PreloadedMenuDao`. This class is managed as a bean, and is injected into the menu repository which is also injected into the menu service.
The dependency injection is all setup in the api module. Take a look at `org.fabri1983.menuapp.api.provide` package.

More example URLs
-----------------
Note: add `Content-Type:application/json` and `Accept:application/json` in your REST Client plugin at header section.

	POST http://localhost:8090/user/1/menu/group
    {
      "maxResults": 10,
      "groupData": {
        "priceFrom": 150,
        "priceTo":  850,
        "currency": "DEFAULT_USD"
      }
    }
	
	POST http://localhost:8090/user/1/menu/filter
    {
      "maxResults": 10,
      "filterData": {
        "maxPrice": 100,
        "currency": "DEFAULT_USD"
      }
    }
	
	POST http://localhost:8090/user/1/menu/filter
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
	
	POST http://localhost:8090/user/1/menu/1/rate
	{
	  "rating": 1,
	  "description": "The description of the rating isn't stored yet"
	}
	
TODO
----
* Define exceptions and handle them in Service layer to provide upper layers human readable messages.

* Unit test the many resources, services, parsers, converters, etc.

* Use of `Optional<T>` to hide null manipulation.

* Available days should be an `Enum` type instead of hardcoded strings like "Friday", "Saturday", "Sunday".

* Complete the use of maven profiles in order to provide a different `server-config.yml` per profile. Use resource filtering on it.

* When a service needs several steps to execute, use chain of responsability with a chain builder or alike to improve readability.

* Revisit the list of `FIXMEs` and `TODOs`.
