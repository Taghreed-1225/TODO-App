# ToDo App Backend

This is the final backend project for my Spring Boot training course **[Spring Training Tasks](https://github.com/Taghreed-1225/Spring-Training)**. It includes two microservices:

1. **ToDo Service** – Handles CRUD operations for todo items.
2. **User Service** – Manages user authentication, authorization, and account operations.

## Technologies Used

- Java 17  
- Spring Boot
- Spring Rest APIs
- Spring Security + JWT  
- Hibernate + JPA  
- MySQL  
- Swagger  
- Postman  
- JUnit5  

## Project Structure

### ✅ ToDo Service

Handles operations for tasks related to a specific user.

**Features:**

- Add, update, delete, and search for todo items
- Each item is connected to a user
- Swagger documentation for all APIs
- Global exception handling (e.g., NotFoundException)
- Backend validation on inputs
- Unit testing with JUnit5
- Postman collection for testing
- Token validation via `/checkToken` API from User Service

**Database Tables:**

- `items`: id, title, user_id, item_details_id  
- `item_details`: id, description, created_at, priority, status  

### 🔐 User Service

Handles authentication, authorization, and user account operations.

**Features:**

- Register, login, delete, update user
- JWT token generation and validation
- OTP-based password reset using Java Mail
- Swagger documentation
- Backend validation and exception handling
- Unit testing with JUnit5
- Postman collection for testing

**Database Tables:**

- `user`: id, email, password, enabled  
- `otp`: id, otp, expiration_time, user_id  
- `jwt`: id, token, user_id, created_at, expiration_date, token_type  

## APIs Overview

### Auth Flow

| Endpoint | Description |
|----------|-------------|
| `POST /rest/auth/register` | Register new user and send OTP |
| `POST /rest/auth/login` | Login and generate JWT |
| `PUT /activate?username` | Activate account using OTP |
| `POST /checkToken` | Validate token |
| `POST /forgetPassword` | Generate OTP and send by email |
| `PUT /changePassword` | Change password using token + OTP |
| `POST /regenrateOtp?email` | Resend OTP |

### User Operations

| Endpoint | Description |
|----------|-------------|
| `POST /add` | Add user |
| `PUT /update` | Update user |
| `POST /delete` | Delete user |
| `GET /search` | Search by email |

### ToDo Operations

| Endpoint | Description |
|----------|-------------|
| `POST /items` | Add todo item |
| `PUT /items/{id}` | Update item |
| `DELETE /items/{id}` | Delete item |
| `GET /items` | Search item by title |



## Screenshots

![Swagger Screenshot 1](ToDoService%20Swagger.png)  
![Swagger Screenshot 2](UsersService%20Swagger.png)
