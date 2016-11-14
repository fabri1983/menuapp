The **api** project is the application by it self. 
It's a standalone executable jar which serves REST calls with JSON-like responses under a Jetty embedded container.
This project defines every resources' URL the system exposes.
Built using Maven shade plugin to generate an uber jar which facilitates the distribution of the app since no dependencies must be installed at destination.
