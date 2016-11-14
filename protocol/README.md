The **protocol** project provides the mapping between the requests and responses with those components the api can understand.
The mapping is via representational elements so we avoid messing up the business components with annotations and/or conversion logic. 
Here you define your custom parsers and what data you expect to receive/send for every REST call.
Also you can put any validation you want whether when receiving a request or when generating a response. Currently the project uses `org.hibernate.validator.*` and `javax.validation.*`.
