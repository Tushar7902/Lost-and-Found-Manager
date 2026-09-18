# Project Report: Lost & Found Manager

- **Course Title:** Programming In Java
- **Name:** Tushar Chauhan
- **Reg No:** 25BAI11335
- **Program:** B.Tech CSE (AIML)

---

## 1. Introduction & Problem Statement

Campuses face a high volume of lost belongings, making informal recovery channels inefficient, fragmented, and untracked. The **Lost & Found Manager** is a modular, command-line Java application that centralizes item reporting, keyword searching, automated similarity matching, and claim resolution while persisting data across sessions using Java Object Serialization.

---

## 2. Requirements

### Functional Requirements
- **User Registration:** Create user profiles with unique User ID, name, email, and phone; prevent duplicate IDs.
- **Item Reporting:** Register lost and found items with name, category, description, color, location, and date.
- **View & Search:** Display all active records or search across all item fields using keywords.
- **Match Detection:** Score attribute similarity between lost and found items; flag matches where similarity $\ge 50\%$.
- **Claim Management:** Submit claims referencing item IDs, review pending claims, and approve/reject claims to update item status.
- **Data Persistence:** Automatically load and store state in serialized `.dat` files upon execution and lifecycle events.

### Non-Functional Requirements
- **CLI Navigation:** Intuitive, clear numbered menu options.
- **Robustness:** Graceful handling of non-numeric, empty, or out-of-range inputs without termination.
- **Modularity:** Separation into `model`, `service`, `util`, and `main` layers.
- **Compatibility & Speed:** Compatible with Java 17+, optimized for low-latency campus data volumes.
- **Privacy:** Restrict stored fields strictly to necessary operational data.

---

## 3. System Architecture & Design

### Architecture Overview
The application uses a 4-tier modular architecture:

1. **Presentation Layer:** `Main.java` handles CLI menus, input validation, and user navigation.
2. **Service Layer:** `UserService`, `ItemService`, `ClaimService`, and `MatchingService` execute core business logic.
3. **Model Layer:** Entity classes (`User`, `Item`, `LostItem`, `FoundItem`, `Claim`) represent domain data.
4. **Persistence Layer:** `FileManager` uses Java File I/O and object serialization to persist data to disk.

```text
Main (CLI Interface)
├── UserService ──────> User
├── ItemService ──────> Item ──┬── LostItem
│     │                        └── FoundItem
│     └── MatchingService
└── ClaimService ─────> Claim
│
FileManager (Object Serialization) ───> src/data/*.dat
```

---

### Design Diagrams

#### 1. Use Case Diagram
```text
                  +---------------------------+
                  |   Lost & Found Manager    |
                  |                           |
User ------------>| Register User             |
  | ------------->| Report Lost Item          |
  | ------------->| Report Found Item         |
  | ------------->| View Lost/Found Items     |
  | ------------->| Search Items              |
  | ------------->| Find Possible Matches     |
  | ------------->| Submit Claim              |
  | ------------->| View Claims               |
  | ------------->| Approve / Reject Claim    |
                  +---------------------------+
```

#### 2. Workflow Diagram
```text
[Start] ──> [Load Saved Data] ──> [Display Main Menu]
                                          │
    ┌─────────────────┬───────────────────┼───────────────────┬──────────────────┐
    ▼                 ▼                   ▼                   ▼                  ▼
Register User   Report Item (Lost/Found)  Search / View   Find Matches     Manage Claims
    └─────────────────┴───────────────────┼───────────────────┴──────────────────┘
                                          ▼
                                     [Save Data]
                                          │
                                   [Continue?] ──Yes──> (Loop to Main Menu)
                                          │ No
                                          ▼
                                       [Exit]
```

#### 3. Sequence Diagram (Typical Operation)
```text
User            Main                  Service              FileManager
 │               │                       │                      │
 │ Select Action │                       │                      │
 ├──────────────>│ Process Input         │                      │
 │               ├──────────────────────>│                      │
 │               │                       │ Handle Business Logic│
 │               │<──────────────────────┤                      │
 │               │ Response Data         │                      │
 │               ├─────────────────────────────────────────────>│
 │               │ Save Updated State (.dat)                    │
 │               │<─────────────────────────────────────────────┤
 │ Output Display│
 │<──────────────┤
```

#### 4. Class / Component Diagram
```text
    +-------------------+           +-------------------+
    |    UserService    |           |   ClaimService    |
    +-------------------+           +-------------------+
              │                               │
              ▼                               ▼
      +---------------+               +---------------+
      |     User      |               |     Claim     |
      +---------------+               +---------------+

    +-------------------+           +-------------------+
    |    ItemService    | ────────> |  MatchingService  |
    +-------------------+           +-------------------+
              │
              ▼
      +---------------+
      |     Item      | (Base Class)
      +---------------+
         ▲         ▲
         │         │
+────────────+  +────────────+
|  LostItem  |  | FoundItem  |
+────────────+  +────────────+
```

#### 5. Logical ER Diagram
```text
+------------------+                    +------------------+
|       USER       | 1                * |      CLAIM       |
+------------------+--------------------+------------------+
| PK  userId       |                    | PK  claimId      |
|     name         |                    | FK  claimantId   |
|     email        |                    | FK  lostItemId   |
|     phone        |                    | FK  foundItemId  |
+------------------+                    |     status       |
  │ 1                                   |     claimDate    |
  │                                     +------------------+
  │                                       │ *            │ *
  │ *                                     │              │
  ├───────────────────────┐               │              │
  ▼                       ▼               ▼ 1            ▼ 1
+------------------+    +------------------+            │
|    LOST ITEM     |    |    FOUND ITEM    |            │
+------------------+    +------------------+            │
| PK  itemId       |    | PK  itemId       |            │
| FK  userId       |    | FK  finderId     |            │
|     name         |    |     name         |            │
|     category     |    |     category     |            │
|     description  |    |     description  |            │
|     color        |    |     color        |            │
|     location     |    |     location     |            │
|     date         |    |     date         |            │
|     status       |    |     status       |            │
+------------------+    +------------------+────────────┘
```

---

## 4. Design Decisions & OOP Principles

- **Encapsulation & Domain Modeling:** Segregated entities (`User`, `Item`, `Claim`) maintain private state fields accessible via explicit public getter and setter methods.
- **Inheritance & Reusability:** `LostItem` and `FoundItem` inherit base attributes and behavior from the abstract/base class `Item`, eliminating duplicated properties.
- **Polymorphism & Method Overriding:** Derived item classes override `toString()` to output formatted, context-specific CLI listings.
- **Collections Framework:** `ArrayList` handles dynamic, in-memory operations and sequential filtering efficiently.
- **Object Serialization Persistence:** Eliminates external database dependencies by writing memory structures directly to `.dat` files via `ObjectInputStream` and `ObjectOutputStream`.

---

## 5. Implementation Details

### Module Directory Structure
```text
src/
├── model/       # User, Item, LostItem, FoundItem, Claim
├── service/     # UserService, ItemService, MatchingService, ClaimService
├── util/        # FileManager
├── data/        # users.dat, lost_items.dat, found_items.dat, claims.dat
└── Main.java    # CLI Entry Point & Controllers
```

### Match Scoring Algorithm
Matches between lost and found items evaluate five key parameters totaling up to 100 points:

| Attribute | Points | Evaluation Type |
| :--- | :---: | :--- |
| **Category** | 25 | Exact Match |
| **Location** | 25 | Exact Match |
| **Color** | 20 | Exact Match |
| **Item Name** | 20 | Case-insensitive String Match |
| **Description** | 10 | Keyword Containment |
| **Total** | **100** | **Threshold for Match Display: $\ge 50\%$** |

---

## 6. Testing & Validation

| Test Case | Input / Scenario | Expected Result | Status |
| :--- | :--- | :--- | :---: |
| **User Creation** | Valid inputs (ID: 1, Name: test, etc.) | User stored successfully | Passed |
| **Duplicate Prevention** | Existing User ID or Item ID | Rejection with error notification | Passed |
| **Invalid References** | Reporting/claiming with non-existent IDs | Operation cleanly aborted | Passed |
| **Input Parsing** | Non-numeric input for menu/ID fields | Prompts retry; prevents program crash | Passed |
| **Empty Input** | Blank fields during text entry | Re-prompts until valid input is given | Passed |
| **Search Functionality** | Known keyword / Unknown keyword | Returns matches or "No items found" message | Passed |
| **Match Calculation** | Paired attributes evaluated | Returns correct percentage score | Passed |
| **Claim Lifecycle** | Submit claim $\rightarrow$ Approve/Reject | Status flags transition; item state updates | Passed |
| **Persistence Integrity** | Terminate and restart application | State restored identically from `.dat` files | Passed |

---

## 7. Challenges & Key Learnings

- **Relational Integrity via Serialization:** Maintained foreign key-like references across disparate collections (`claimantId`, `itemId`) without relational database constraints or triggers.
- **Defensive CLI Programming:** Handled mismatched input types, null references, and negative ID integers across iterative input loops.
- **OOP Architecture:** Practiced clean architectural decoupling across Model, Service, and Utility components.

---

## 8. Future Enhancements & References

### Future Enhancements
- **Authentication & RBAC:** Add role-based access control for students and administrators.
- **Database & UI:** Transition from `.dat` flat files to SQLite or PostgreSQL, paired with a web/mobile GUI.
- **Fuzzy Search & Notifications:** Implement Levenshtein-distance matching and automated email alerts.
- **Computer Vision:** Image-based item verification during upload.

### References
- Oracle Java Documentation (Collections, Object Serialization, File I/O)
- Programming in Java Course Notes & Materials
- GitHub Project Repository: [https://github.com/Tushar7902/Lost-and-Found-Manager](https://github.com/Tushar7902/Lost-and-Found-Manager)