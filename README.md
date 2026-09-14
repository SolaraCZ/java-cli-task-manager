# Java CLI Task Manager


A lightweight command-line interface (CLI) task management tool built in Java. It allows users to track their to-do items with automatic local persistence in JSON format and includes unit tests written in JUnit 5.

---

## Features

- **Task Management**: Create tasks with titles and descriptions, list all tasks with status indicators (`[ ]` vs `[✓]`), and mark them as completed by ID.
- **Data Persistence**: Loads and saves tasks to a local `tasks.json` file using **Jackson Databind**.
- **State Recovery**: Tracks task IDs across sessions to avoid conflicts after application restarts.
- **Robust Input Handling**: Handles invalid inputs, empty strings, and I/O exceptions without crashing.
- **Unit Testing**: Core storage and serialization logic are covered by **JUnit 5** tests.

---

## Tech Stack

- **Language:** Java 17+
- **Build Tool:** Apache Maven
- **JSON Processing:** Jackson Databind (`2.17.1`)
- **Testing Framework:** JUnit 5 (Jupiter)
