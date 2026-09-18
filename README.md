# Lost & Found Manager

## 1. Project Overview

**Lost & Found Manager** is a command-line Java application designed to help students report, search, match, and claim lost and found items within a campus environment.

The system provides a structured way to record lost items and found items instead of depending on informal communication.

---

## 2. Problem Statement

Students frequently lose personal belongings such as electronics, books, ID cards, bags, keys, and accessories on campus. Finding these items through informal messages or announcements can be difficult and time-consuming.

This project provides a centralized command-line system where users can register, report lost or found items, search records, identify possible matches, and submit claims.

---

## 3. Features

- User registration
- Report lost items
- Report found items
- View lost items
- View found items
- Search items using keywords
- Automatic possible-match calculation
- Claim management
- Approve or reject claims
- Input validation
- Persistent data storage using Java file I/O

---

## 4. Technologies Used

- **Language:** Java (JDK 17+)
- **Collections:** Java Collections Framework (`ArrayList`, etc.)
- **Persistence:** Java File I/O & Object Serialization
- **Interface:** Command Line Interface (CLI)
- **Version Control:** Git and GitHub

---

## 5. Project Structure

```text
Lost & Found Manager/
├── data/
├── docs/
├── screenshots/
├── src/
│   ├── model/
│   │   ├── User.java
│   │   ├── Item.java
│   │   ├── LostItem.java
│   │   ├── FoundItem.java
│   │   └── Claim.java
│   ├── service/
│   │   ├── UserService.java
│   │   ├── ItemService.java
│   │   ├── MatchingService.java
│   │   └── ClaimService.java
│   ├── util/
│   │   └── FileManager.java
│   └── Main.java
├── README.md
└── statement.md
```

---

## 6. Requirements

- **Java Development Kit (JDK):** Version 17 or later
- **Terminal:** Command Prompt / PowerShell / Bash / VS Code Integrated Terminal
- **Git:** (Required for cloning and submission)

---

## 7. How to Run

1. Open a terminal in the project root directory.

2. Navigate to the source folder:
   ```bash
   cd src
   ```

3. Compile all source files:
   ```bash
   javac model/*.java service/*.java util/*.java Main.java
   ```

4. Run the application:
   ```bash
   java Main
   ```

---

## 8. Application Workflow

1. **Register** a new user account.
2. **Report** a lost item or found item.
3. **View or search** active records.
4. Run the **matching feature** to scan for possible matches.
5. **Submit a claim** for a matching item.
6. **Approve or reject** pending claims.
7. Application state is automatically synchronized into the `data/` directory.

---

## 9. Matching System

The matching engine compares key attributes between reported lost and found items:

| Attribute | Weight |
| :--- | :--- |
| **Category** | 25 points |
| **Location** | 25 points |
| **Color** | 20 points |
| **Item Name** | 20 points |
| **Description** | 10 points |
| **Total Possible** | **100 points** |

> **Note:** Items with a match confidence score of **$\ge 50\%$** are flagged and displayed as potential matches.

---

## 10. Data Persistence

The application uses native Java Object Serialization to persist user and item data between sessions.

The following files are generated automatically inside the `data/` directory:

```text
data/
├── users.dat
├── lost_items.dat
├── found_items.dat
└── claims.dat
```

---

## 11. Error Handling

The application includes validation for:

- Non-numeric and malformed inputs
- Blank or empty field submissions
- Duplicate user IDs and item IDs
- Non-existent user, item, and claim references
- Invalid menu selections

---

## 12. Author

**Programming in Java Project**  
*Lost & Found Manager*