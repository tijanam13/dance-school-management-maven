# Dance School Management System 

This repository contains an upgraded version of the Dance School Management System, originally developed for the **Software Engineering** course at the Faculty of Organizational Sciences (FON), University of Belgrade.

The original project can be found here: [dance-school-management](https://github.com/tijanam13/dance-school-management)

The upgrade was done for the **Software Tools** (Softverski alati) course and introduces Maven, JUnit testing, JavaDoc documentation, JSON handling, and additional modern Java technologies.

---

## What Was Added

### Maven
The project was restructured as a **multi-module Maven project** with three modules:
- `zajednicki` — shared domain classes, transfer objects, and services
- `server` — system operations, business logic, and database access
- `klijent` — Swing GUI and client-side controllers

All dependencies, builds, and tests are managed exclusively through Maven.

### JUnit Testing
Domain classes and system operations are tested using **JUnit Jupiter (JUnit 5)**. Tests cover constructors, getters/setters, equals/hashCode, toString, and business logic validation. Mockito is used for mocking database connections in system operation tests.

### JavaDoc
Domain classes, system operations, and test classes are documented with **JavaDoc** comments.

### JSON
The application works with JSON in multiple ways:
- Serialization and deserialization of domain objects to JSON files using **Gson**
- Calling an external web service for **RSD to EUR currency conversion**
- Manual JSON object construction using Gson's `JsonObject` API

### Additional Technologies
- **Lombok** — applied to selected domain classes to auto-generate getters, setters, and constructors
- **Java Records** — `Zahtev` and `Odgovor` transfer classes refactored into immutable records
- **Lambda expressions and Stream API** — used in `StatistikaUpisnicaSO` for statistical calculations (sum, average, filtering, grouping), as well as in `VratiListuSviUpisnicaSO`, `VratiListuUpisnicaPolaznikSO`, `PrijaviInstruktorSO`, and `JsonServis` for filtering, sorting, and data processing

---

## Domain Classes
`Instruktor`, `Polaznik`, `Kvalifikacija`, `InstruktorKvalifikacija`, `VrstaPlesa`, `UzrasnaKategorija`, `Upisnica`, `StavkaUpisnice`, `Sertifikat`, `Nivo`

## System Operations
Over 40 system operations covering full CRUD functionality for all domain entities, as well as search, filtering, and statistical operations.

---

## How to Build and Test

```bash
mvn clean install
```

---

## Git History

The project was developed in phases, each on a separate branch merged into `main`:

| Tag | Branch | Description |
|-----|--------|-------------|
| v1.0 | `json_deo` | Initial JSON serialization. |
| v1.1 | `javadoc` | Complete JSON implementation and service integration. |
| v1.2 | `junit` | JavaDoc documentation for domain classes and system operations. |
| v1.3 | `lombok` | Complete JUnit tests with JavaDoc comments. |
| v1.4 | `records` | Added Lombok to domain classes. |
| v1.5 | `lambda-stream-api` | Added Java Records. |
| v1.6 | `lambda-stream-api` | Added Lambda expressions and Stream API. |

---

## Course Information
- **Subject**: Software Tools (Softverski alati)
- **Faculty**: Faculty of Organizational Sciences, University of Belgrade
- **Year**: 4rd year undergraduate
