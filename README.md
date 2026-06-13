# Hotel Management System

A JavaFX desktop application built as part of an Object-Oriented Programming (OOP) course project at St. Mary University.  
This system demonstrates MVC architecture, DAO pattern, and JDBC integration with MySQL for managing hotel operations.

## Features
- Room Management (CRUD)
  - Add, update, delete, and view rooms with details (number, type, price, status).
- Reservation Management (CRUD)
  - Create, update, cancel, and view reservations.
- Billing
  - Generate bills including room charges and service charges.
  - Support for payments (cash).
- Service Requests
  - Guests can request food, drinks, laundry, and other services.
  - Waiters can manage and update service statuses.
- Authentication
  - Login system for Manager, Guest, and Waiter roles.
- User Accounts
  - Staff members (Manager, Waiter) and Guests have separate accounts with role-based access.

## Technologies Used
- JavaFX → User Interface
- Java (OOP principles) → Encapsulation, Inheritance, Polymorphism, Abstraction
- MySQL → Relational database
- JDBC → Database connectivity
- DAO Pattern → Clean separation of database logic
- MVC Architecture → Organized structure for scalability

## 📂 Project Structure

HotelManagement/
├── src/com/mycompany/model/        # Entity classes (User, Manager, Guest, Room, Service, Payment, etc.)
├── src/com/mycompany/controller/   # Controllers for Manager, Guest, Waiter
├── src/com/mycompany/dao/          # DAO classes for database operations
├── resources/                      # FXML files and UI resources
└── README.md                       # Project documentation

---

## Getting Started

### Prerequisites
- Java JDK 17+  
- NetBeans IDE (or IntelliJ/Eclipse with JavaFX support)  
- MySQL Server  

### Setup
1. Clone the repository:
   ```bash
