# Project Statement

## Project Title

Lost & Found Manager

## 1. Problem Statement

In a college campus, students can easily lose things such as ID cards, earphones, books, bags, keys, and other personal belongings. At the same time, someone else may find the same item but may not know how to return it to its owner.

Usually, students depend on WhatsApp groups, friends, notices, or word of mouth to find lost items. This can make the process difficult because the information gets mixed up and it is not easy to search for a particular item.

The purpose of this project is to create a simple Lost & Found Manager that keeps information about lost and found items in one place and helps users find possible matches between them.

## 2. Proposed Solution

The Lost & Found Manager is a command-line Java application. A user can register in the system and report an item as either lost or found.

The application stores details such as the item name, category, description, color, location, and date. Users can also search the stored records using keywords.

The main feature of the project is the matching system. It compares details of a lost item with found items and gives a match score based on common attributes. This helps identify items that could possibly belong to the same person.

If a possible match is found, the user can submit a claim. The claim can then be approved or rejected.

## 3. Main Objectives

- Provide a simple way to report lost items.
- Provide a way to record items that have been found.
- Make lost and found records easy to search.
- Automatically identify possible matches between items.
- Provide a basic claim management process.
- Store the data so that it is available even after restarting the application.
- Reduce the difficulty of finding lost belongings on campus.

## 4. Main Modules

### User Management

Users can register themselves by providing their user ID, name, email, and phone number.

### Lost Item Management

A registered user can report an item as lost by entering its details such as name, category, description, color, location, and date.

### Found Item Management

A registered user can also report an item that they have found using similar details.

### Search

Users can search for items using keywords related to the item name, category, color, location, or description.

### Matching

The system compares a lost item with available found items. It checks the category, color, location, name, and description and calculates a percentage-based match score.

### Claim Management

A user can submit a claim for a possible matching item. The claim can be viewed and either approved or rejected.

### Data Storage

User, item, and claim information is stored using Java file I/O and object serialization so that the information is not lost when the program is closed.

## 5. Input

The application takes the following information from the user:

- User ID
- Name
- Email
- Phone number
- Item ID
- Item name
- Category
- Description
- Color
- Location
- Date
- Claim details

The application also provides numbered choices for categories, colors, and common campus locations to make data entry easier.

## 6. Output

Depending on the operation selected, the application displays:

- User registration confirmation
- Lost item records
- Found item records
- Search results
- Possible matching items
- Match percentage
- Claim details
- Claim approval or rejection messages
- Validation and error messages

## 7. Matching Logic

The matching system gives different weights to different item details:

- Category – 25 points
- Color – 20 points
- Location – 25 points
- Item name – 20 points
- Description – 10 points

The maximum possible score is 100%. A found item with a score of 50% or more is displayed as a possible match.

## 8. Expected Outcome

The expected outcome of this project is a simple and usable command-line system that makes it easier to maintain lost and found records.

Instead of depending only on informal communication, users can record the details of an item, search existing records, check possible matches, and manage claims through one application.

## 9. Java Concepts Used

The project makes use of several concepts from Programming in Java, including:

- Classes and objects
- Constructors
- Encapsulation
- Inheritance
- Method overriding
- ArrayList and Collections
- Exception handling
- File I/O
- Object serialization
- Packages
- Command-line input and output

## 10. Conclusion

Lost & Found Manager provides a small but practical solution to a common campus problem. The project focuses on keeping the application simple while still including useful features such as searching, matching, validation, claims, and data persistence.