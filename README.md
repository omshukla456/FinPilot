# AI-Powered Expense Manager

An intelligent expense management system that not only tracks user spending but also analyzes and categorizes expenses using smart backend logic.

---

## Overview

The AI Expense Manager is a backend-driven application designed to help users manage their finances efficiently. Unlike traditional expense trackers, this system introduces intelligent categorization and structured data handling, making it more practical and scalable.

---

## Key Features

### User Authentication

* User registration and login functionality
* Multi-user support with user-specific data

### Expense Management

* Add, view, and delete expenses
* Organized storage using PostgreSQL

### Intelligent Categorization

* Automatically assigns categories based on expense description
* Removes the need for manual categorization
* Built using rule-based logic (can be extended to machine learning)

### Clean Architecture

* DTO-based API design
* Layered structure (Controller → Service → Repository)
* Maintainable and scalable codebase

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Tools

* Postman (API testing)
* pgAdmin (database management)

---

## Project Architecture

```text
Controller → DTO → Service → Repository → Database
                    ↓
                 AI Logic
```

---

## Project Structure

```text
expensemanager/
│
├── controller/
├── service/
├── repository/
├── model/
├── dto/
├── ai/
└── config/
```

---

## API Endpoints

### Authentication

* POST /auth/register → Register a new user
* POST /auth/login → Login user

### Expenses

* POST /expenses/add → Add expense (auto-categorized)
* GET /expenses/all → Retrieve all expenses

---

## Sample Request

### Add Expense

```json
{
  "amount": 300,
  "description": "Pizza from Dominos",
  "date": "2026-04-08",
  "userId": 1
}
```

---

## Sample Response

```json
{
  "id": 1,
  "amount": 300,
  "description": "Pizza from Dominos",
  "category": "Food",
  "date": "2026-04-08",
  "userName": "Om",
  "userEmail": "om@gmail.com"
}
```

---

## Future Improvements

* Machine learning-based categorization
* Expense prediction using historical data
* Budget tracking and alerts
* Frontend dashboard integration

---

## Why This Project Matters

This project goes beyond basic CRUD functionality by introducing intelligent decision-making into a common use case. It demonstrates practical backend development skills, structured architecture, and the ability to design scalable systems.

---

## Author

Om Shukla
Aspiring Software Engineer with a focus on backend development and intelligent systems.

---

## Final Note

This project reflects an approach to building applications that are not just functional, but also meaningful and extensible.
v# AI-Powered Expense Manager

An intelligent expense management system that not only tracks user spending but also analyzes and categorizes expenses using smart backend logic.

---

## Overview

The AI Expense Manager is a backend-driven application designed to help users manage their finances efficiently. Unlike traditional expense trackers, this system introduces intelligent categorization and structured data handling, making it more practical and scalable.

---

## Key Features

### User Authentication

* User registration and login functionality
* Multi-user support with user-specific data

### Expense Management

* Add, view, and delete expenses
* Organized storage using PostgreSQL

### Intelligent Categorization

* Automatically assigns categories based on expense description
* Removes the need for manual categorization
* Built using rule-based logic (can be extended to machine learning)

### Clean Architecture

* DTO-based API design
* Layered structure (Controller → Service → Repository)
* Maintainable and scalable codebase

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Tools

* Postman (API testing)
* pgAdmin (database management)

---

## Project Architecture

```text
Controller → DTO → Service → Repository → Database
                    ↓
                 AI Logic
```

---

## Project Structure

```text
expensemanager/
│
├── controller/
├── service/
├── repository/
├── model/
├── dto/
├── ai/
└── config/
```

---

## API Endpoints

### Authentication

* POST /auth/register → Register a new user
* POST /auth/login → Login user

### Expenses

* POST /expenses/add → Add expense (auto-categorized)
* GET /expenses/all → Retrieve all expenses

---

## Sample Request

### Add Expense

```json
{
  "amount": 300,
  "description": "Pizza from Dominos",
  "date": "2026-04-08",
  "userId": 1
}
```

---

## Sample Response

```json
{
  "id": 1,
  "amount": 300,
  "description": "Pizza from Dominos",
  "category": "Food",
  "date": "2026-04-08",
  "userName": "Om",
  "userEmail": "om@gmail.com"
}
```

---

## Future Improvements

* Machine learning-based categorization
* Expense prediction using historical data
* Budget tracking and alerts
* Frontend dashboard integration

---

## Why This Project Matters

This project goes beyond basic CRUD functionality by introducing intelligent decision-making into a common use case. It demonstrates practical backend development skills, structured architecture, and the ability to design scalable systems.

---

## Author

Om Shukla
Aspiring Software Engineer with a focus on backend development and intelligent systems.

---

## Final Note

This project reflects an approach to building applications that are not just functional, but also meaningful and extensible.
