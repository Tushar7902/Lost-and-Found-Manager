# Lost & Found Manager

A simple, practical command-line tool written in Java to help college students report, track, and reclaim lost belongings across campus.

---

## 1. About the Project

Losing essentials on campus—whether it's an ID card, earphones, or a notebook—is a common headache. Most recovery efforts depend on scattered WhatsApp groups or unorganized notice boards where details get buried quickly.

**Lost & Found Manager** replaces that chaos with an organized, console-based system. It lets students log missing items, record found items, look up existing listings, check automated match suggestions, and manage ownership claims in one reliable place.

---

## 2. Key Features

- **User Accounts:** Simple registration system to keep reports tied to specific students.
- **Item Logging:** Separate flows to file lost or found items with details like name, category, color, and location.
- **Search & Browse:** Filter through logged items quickly using keywords.
- **Smart Matching Engine:** Compares lost and found listings attribute-by-attribute to flag likely matches automatically.
- **Claim Workflow:** Users can stake a claim on a found item, which can then be reviewed and marked approved or rejected.
- **Persistent Storage:** Saves all records directly to disk so nothing disappears when the terminal closes.
- **Robust Input Checks:** Handles empty lines, bad numbers, and invalid menu inputs smoothly without crashing.

---

## 3. Tech Stack & Core Concepts

- **Language:** Java (JDK 17 or higher)
- **Data Structures:** Java Collections (`ArrayList`)
- **Storage:** Standard Java File I/O (`ObjectOutputStream` & `ObjectInputStream`)
- **Architecture / OOP Concepts:**
  - Encapsulation (data modeling with getters/setters)
  - Inheritance & Polymorphism (`Item` parent class extended by `LostItem` and `FoundItem`)
  - Modular package structure (`model`, `service`, `util`)
  - Custom exception & edge-case handling

---

## 4. Project Structure

```text
Lost & Found Manager/
├── screenshots/
├── src/
│   ├── data/
│   │   ├── users.dat
│   │   ├── lost_items.dat
│   │   ├── found_items.dat
│   │   └── claims.dat
│   ├── model/
│   ├── service/
│   ├── util/
│   └── Main.java
├── README.md
├── statement.md
└── .gitignore
```

> **Note:** When the application runs from `src/`, a `src/data/` folder is generated automatically to store serialized `.dat` records.

---

## 5. Prerequisites

- **Java Development Kit (JDK):** Version 17 or newer
- **Terminal:** PowerShell, Command Prompt, Git Bash, or any IDE built-in terminal
- **Git:** For version tracking and submission

---

## 6. How to Build & Run

1. Open your terminal in the root project directory:
   ```bash
   cd src
   ```

2. Compile all source files across modules:
   ```bash
   javac model/*.java service/*.java util/*.java Main.java
   ```

3. Launch the app:
   ```bash
   java Main
   ```

---

## 7. Typical Walkthrough

1. **Register** a student user profile.
2. **Post an item** under lost or found with relevant descriptors.
3. **Browse or search** the database to check if someone has already posted about it.
4. Run the **Match Finder** to let the program calculate similarities.
5. If a match looks right, **submit a claim**.
6. The finder or admin can **approve or reject** the claim.
7. Everything saves automatically to disk upon completion.

---

## 8. How the Match Engine Works

The matching algorithm compares lost reports against found listings using a weighted scoring system:

| Field | Weight | Description |
| :--- | :---: | :--- |
| **Category** | 25% | General classification (e.g., Electronics, Stationery) |
| **Location** | 25% | Specific campus area (e.g., Library, Cafeteria) |
| **Color** | 20% | Primary color match |
| **Item Name** | 20% | Keyword overlap in the item title |
| **Description** | 10% | Detailed notes match |
| **Total** | **100%** | Full score potential |

Any pair scoring **$50\%$ or higher** is surfaced to the student as a probable match.

---

## 9. Data Storage & Persistence

The project relies on native Java Object Serialization. All active sessions write to binary files located under `src/data/`:

- `users.dat` — Registered user profiles
- `lost_items.dat` — Open lost item reports
- `found_items.dat` — Open found item reports
- `claims.dat` — Ongoing and resolved claim tickets

Because state is committed to disk, you can close the terminal and pick up right where you left off.

---

## 10. Screenshots & Verification

The `screenshots/` directory includes step-by-step visual proofs of the app in action:

- Navigation through the main interactive menu
- Registering a fresh user and validating duplicate detection
- Submitting both lost and found records
- Querying records with keyword search
- Scoring results inside the matching utility
- Processing a claim end-to-end
- Showing data persisting across app restarts

---

## 11. Author

- **Course:** Programming in Java
- **Project:** Lost & Found Campus Management System
