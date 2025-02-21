# Museum Management System
This project simulates a museum management and tourism system. It handles data about museums, guides, visitor groups, events, and reviews. The application processes input files, manages a central database of entities, and demonstrates several design patterns.

## Overview

The application is structured as a Java project built with Gradle. It is organized into the following components:

- **Input Processing:**
  The main entry point ([`Main.java`](src/main/java/org/example/Main.java)) selects a file processing strategy based on the first command-line argument. Strategies include processing museums, groups, and combined events & listener notifications.

- **File Processing Strategies:**
  Specific classes implement the [`FileProcessingStrategy`](src/main/java/org/example/strategies/FileProcessingStrategy.java) interface to process different types of data:
  - [`MuseumProcessingStrategy`](src/main/java/org/example/strategies/MuseumProcessingStrategy.java) processes museum records.
  - [`GroupProcessingStrategy`](src/main/java/org/example/strategies/GroupProcessingStrategy.java) manages guide and member operations in groups.
  - [`EventProcessingStrategy`](src/main/java/org/example/strategies/EventProcessingStrategy.java) handles event notifications.
  - [`ListenerProcessingStrategy`](src/main/java/org/example/strategies/ListenerProcessingStrategy.java) composes the above strategies for full system execution.

- **Entities and Domain Classes:**
  The project defines various entities—such as [`Museum`](src/main/java/org/example/locations/Museum.java), [`Group`](src/main/java/org/example/entities/Group.java), [`Guide`](src/main/java/org/example/entities/Guide.java), and [`Person`](src/main/java/org/example/entities/Person.java)—and uses a central singleton database ([`Database`](src/main/java/org/example/information/Database.java)) for data management.

- **Exceptions:**
  Custom exceptions ([`GroupThresholdException`](src/main/java/org/example/exceptions/GroupThresholdException.java), [`GroupNotExistsException`](src/main/java/org/example/exceptions/GroupNotExistsException.java), etc.) are thrown when business rules are violated (e.g. adding too many members or an invalid guide).

## Features

- **Museum Management:**
  Ability to add and remove museums while preserving valid location and contact data from input files.

- **Group Operations:**
  Adding or removing guides and group members with proper exception handling for threshold and type errors.
  The system supports operations such as:
  - Adding a guide, with checks to ensure only professors can be set as guides.
  - Adding and removing members with a limit of 10 per group.
  - Handling review submissions and removals according to specified parameters.

- **Event Notifications:**
  When a museum event occurs, the system notifies all associated guides (observers), with a printed summary of messages directed to each guide’s email address.

- **Design Patterns:**
  The project demonstrates the use of several design patterns:
  - **Singleton:** The [`Database`](src/main/java/org/example/information/Database.java) class ensures a single instance across the application.
  - **Observer:** Museums notify guides about events (see [`Subject`](src/main/java/org/example/locations/Subject.java) and [`Observer`](src/main/java/org/example/entities/Observer.java)).
  - **Factory:** The [`PersonFactory`](src/main/java/org/example/entities/PersonFactory.java) is used for creating different types of person objects.
  - **Builder:** Used in building complex objects like [`Location`](src/main/java/org/example/locations/Location.java) and [`Museum`](src/main/java/org/example/locations/Museum.java).
  - **Strategy:** Different file processing strategies are implemented to decouple the execution flow depending on the input type.

## Running the Project

### Build and Test

This project uses Gradle. To compile and run tests, use the following commands in your project root:

```sh
./gradlew build
./gradlew test
```

### Execution

The application is run by specifying the type of data to process followed by the input file path. For example, to process museum data:

```sh
./gradlew run --args="museums src/main/resources/01-basic-museums-load/museums_01"
```

Similarly, for groups:

```sh
./gradlew run --args="groups src/main/resources/03-basic-groups/groups_01"
```

And to run full listener processing (which integrates museums, groups, and events):

```sh
./gradlew run --args="listener src/main/resources/07-museum-listener/museums_03 src/main/resources/07-museum-listener/groups_05 src/main/resources/07-museum-listener/events_01"
```

## Testing

The integrated tests in [`MainTest.java`](src/test/java/MainTest.java ) compare the generated output files (with a `.out` extension) to corresponding reference files (with a `.ref` extension). These tests validate that museum, group, and event processing work exactly as required.
