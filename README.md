# 🚀 FinPilot – AI-Powered Expense Manager

FinPilot is a full-stack expense management system designed to help users track spending, gain insights, and predict future expenses using intelligent backend logic.

This project focuses on building a **secure, scalable, and production-ready backend** rather than just a basic CRUD application.

---

## ✨ Features

### 🔐 Authentication & Security

* User Registration & Login APIs
* JWT-based Authentication
* Secure password handling
* Protected routes with proper 401/403 handling

---

### 💸 Expense Management

* Add expenses with amount, description, category, and date
* Fetch user-specific expenses
* Clean DTO-based architecture
* Structured request and response handling

---

### 📊 Insights Engine

* Analyze spending patterns
* Identify highest spending categories
* Generate meaningful financial insights

---

### 📈 Prediction System

* Calculate average daily expense
* Predict weekly spending
* Built using real data-driven logic

---

### 🧪 API Testing

* Fully tested using Postman
* Verified all endpoints
* Handled real-world errors (401, 403, 500)

---

## 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication

### Database

* PostgreSQL

### Tools

* Postman (API Testing)
* Git & GitHub

---

## 📂 Project Structure

```
com.om.expensemanager
│
├── controller        # REST Controllers
├── service           # Business Logic
├── repository        # Database Access
├── model             # Entity Classes
├── dto               # Request/Response DTOs
├── security          # JWT & Security Config
├── config            # Security Configuration
```

---

## 🔑 API Endpoints

### 🔐 Authentication

* `POST /auth/register` → Register a new user
* `POST /auth/login` → Login and receive JWT token

---

### 💸 Expenses

* `POST /expenses/add` → Add new expense
* `GET /expenses/all` → Get all expenses (user-specific)

---

### 📊 Insights

* `GET /expenses/insights/{userId}` → Get spending insights

---

### 📈 Prediction

* `GET /expenses/predict/{userId}` → Get expense predictions

---

## 🔐 Authorization

All protected APIs require JWT token in headers:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## ▶️ How to Run

### 1️⃣ Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/finpilot.git
cd finpilot
```

---

### 2️⃣ Configure PostgreSQL Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/finpilot
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### 3️⃣ Run the backend

```bash
mvn spring-boot:run
```

---

### 4️⃣ Test APIs

Use Postman to test all endpoints.

---

## 🧠 What I Learned

* Building secure backend systems using JWT
* Handling real-world debugging scenarios (401, 403, 500 errors)
* Designing scalable REST APIs
* Implementing logic beyond CRUD (insights & predictions)
* Connecting backend logic with real data

---

## 🚀 Future Enhancements

* Frontend integration (React UI)
* Deployment (Render / AWS)
* Advanced AI-based predictions
* Budget tracking & alerts

---

## 🤝 Contributing

Contributions are welcome! Feel free to fork and improve the project.

---

## 📬 Contact

**Om Shukla**
📧 [om.shukla9901@gmail.com](mailto:om.shukla9901@gmail.com)
🔗 LinkedIn: https://www.linkedin.com/in/omshukla456/

---

⭐ If you found this project useful, consider giving it a star!
