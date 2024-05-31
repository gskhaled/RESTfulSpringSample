# Welcome to Gasser's submission =)
This is a small RESTful API service that is done using Spring Boot. It connects to a mysql database, from which CRUD operations on a user entity can be performed.

## To use this application, you will need:
- Java 17+ installed with `$JAVA_HOME` environment variable defined correctly.
- Mysql 5.6+ installed.
- This application uses `gradle` wrapper.

## To get this application running:
- Clone the repository.
- Create a mysql database.
- Go to `<PATH_TO_CLONED_FOLDER_NAME>/techtask/src/main/resources/application.properties` and replace all fields with `<>` in their names to your mysql database's credentials.
- Go to `<PATH_TO_CLONED_FOLDER_NAME>` and execute `./gradlew bootRun`.
- If you now go to `<YOUR_HOST>:8080/`, you should be greeted with this message `Greetings from Gasser's submission for Udin's tech task! =)` in your browser.

### Supported APIs:
The application is concerned with connecting to a mysql database and performing some basic CRUD operations on some **User** entities. Here is a list of supported APIs:

- **GET** entry point is `/users`. This retrieves all available users.
- **GET** entry point is `/users/{id}`. This retrieves a user by their id.
- **POST** entry point is `/users`. This adds a new user to the database. It expects 2 path parameters: name and email, in the form of string. It should reply with the created user's id.
- **PUT** entry point is `/users/{id}`. This updates an existing user's data by id. It expects 2 *optional* path parameters: name and email, in the form of string. It should reply with the updated user's id.
- **DELETE** entry point is `/users/{id}`. This deletes an existing user by id. In case the user with the specified id does not exist, a 404 is thrown.



*Disclaimer: this application was kick-started using Spring Initializer, here: https://start.spring.io/*
