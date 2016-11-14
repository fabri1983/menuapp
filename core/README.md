The **core** project exposes the Service layer, and handles the communication downwards to the persistence layer of data via a Repository layer.
The Service layer only interacts with the many repositories in order to create, read, update, and delete data (CRUD). It contains stateless services which are managed by the DI api (Guice).
The Repository layer aims to separate the entities the business understands and the different ways they are persisted.
The Repository layer uses DAO components to interact with the underlying persistence backend.
