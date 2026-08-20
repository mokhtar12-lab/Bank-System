# 🏦 Bank Management System

A secure RESTful backend application for managing banking operations, built with **Java and Spring Boot**.

The system provides secure user authentication, role-based authorization, account management, and transaction processing while following clean backend development practices.

---

## 🚀 Features

### 🔐 Authentication & Authorization

- User registration and login
- JWT-based authentication
- Spring Security integration
- Role-based authorization
- Support for:
  - `ADMIN`
  - `EMPLOYEE`
  - `CUSTOMER`
- Secure endpoint access based on user roles
- Password encryption

### 👤 User & Customer Management

- Create and manage users
- Customer management
- Employee management
- Secure customer data access
- Customers can only access their own accounts and transactions

### 💳 Account Management

- Create bank accounts
- Retrieve account information
- Update account information
- Delete accounts according to business rules
- Support for different account types
- Account balance management

### 💰 Transactions

The system supports:

- Deposit
- Withdrawal
- Transfer between accounts
- Transaction history
- Transaction retrieval by ID
- Transactions associated with customer accounts
- Balance validation
- Prevention of withdrawals when the account balance is insufficient
- Customer-specific transaction filtering

### 📊 Pagination, Sorting & Filtering

- Pagination using Spring Data `Pageable`
- Dynamic sorting
- Filtering
- Search functionality
- Efficient database-level querying

### 🛡️ Security & Data Protection

- JWT authentication
- Role-based access control
- Secure password storage
- Customer ownership validation
- Protection against unauthorized access to other customers' accounts
- JWT secret stored using environment variables

### 📝 Auditing

Implemented Spring Data JPA Auditing to track:

- Created By
- Created Date
- Last Modified By
- Last Modified Date

### ✅ Validation & Exception Handling

- Request validation using Bean Validation
- Custom exception handling
- Global exception handling
- Business rule validation
- Proper HTTP response status handling

## 🏗️ Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
