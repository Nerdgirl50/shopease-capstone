# 🛍️ ShopEase - Full Stack E-Commerce Application
### Capstone Project | FSD Java Program

---

## 📌 Overview
ShopEase is a full-stack e-commerce web application built with **Java Spring Boot** (backend) and **Thymeleaf + HTML/CSS/JS** (frontend). It allows users to browse products, manage a cart, place orders, and track their purchase history. Admins can manage products, orders, and users from a dedicated dashboard.

---

## 🚀 Tech Stack

| Layer       | Technology                          |
|-------------|-------------------------------------|
| Backend     | Java 17, Spring Boot 3.2            |
| Security    | Spring Security (BCrypt, Sessions)  |
| Database    | H2 (dev) / MySQL (production)       |
| ORM         | Spring Data JPA / Hibernate         |
| Frontend    | Thymeleaf, HTML5, CSS3, JavaScript  |
| Icons       | Font Awesome 6                      |
| Build Tool  | Maven                               |

---

## ✨ Features

### User Features
- ✅ Register & Login with secure password hashing
- ✅ Browse all products with category filter & search
- ✅ Product detail page with stock info
- ✅ Add to cart, update quantity, remove items
- ✅ Checkout with address & payment method
- ✅ View order history & order details
- ✅ Edit profile (name, phone, address)

### Admin Features
- ✅ Dashboard with stats (products, orders, users, revenue)
- ✅ Add / Edit / Delete products
- ✅ View and update order status (Pending → Confirmed → Shipped → Delivered)
- ✅ View all registered users

---

## 🛠️ How to Run

### Prerequisites
- Java 17+
- Maven 3.8+

### Steps

```bash
# 1. Clone or extract the project
cd shopease

# 2. Run the application
mvn spring-boot:run

# 3. Open in browser
http://localhost:8080
```

### H2 Database Console (Dev)
```
URL:  http://localhost:8080/h2-console
JDBC: jdbc:h2:mem:shopease_db
User: sa  |  Password: (blank)
```

---

## 🔐 Default Login Credentials

| Role  | Email                    | Password  |
|-------|--------------------------|-----------|
| Admin | admin@shopease.com       | admin123  |
| User  | user@shopease.com        | user123   |

---

## 📁 Project Structure

```
shopease/
├── src/main/java/com/shopease/
│   ├── ShopEaseApplication.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   ├── CustomUserDetailsService.java
│   │   └── DataSeeder.java
│   ├── controller/
│   │   ├── ProductController.java
│   │   ├── UserController.java
│   │   ├── CartController.java
│   │   ├── OrderController.java
│   │   └── AdminController.java
│   ├── model/
│   │   ├── User.java
│   │   ├── Product.java
│   │   ├── Cart.java, CartItem.java
│   │   └── Order.java, OrderItem.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   ├── ProductRepository.java
│   │   ├── CartRepository.java
│   │   └── OrderRepository.java
│   └── service/
│       ├── UserService.java
│       ├── ProductService.java
│       ├── CartService.java
│       └── OrderService.java
├── src/main/resources/
│   ├── templates/
│   │   ├── fragments/layout.html
│   │   ├── user/ (login, register, products, cart, checkout, orders, profile)
│   │   └── admin/ (dashboard, products, orders, users)
│   ├── static/
│   │   ├── css/style.css
│   │   └── js/main.js
│   └── application.properties
└── pom.xml
```

---

## 🔄 Switching to MySQL (Production)

In `application.properties`, comment out the H2 section and uncomment MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopease_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

Then create the database:
```sql
CREATE DATABASE shopease_db;
```

---

## 👨‍💻 Author
**Name:** [Your Name]  
**Roll Number:** [Your Roll Number]  
**Batch/Program:** FSD Java

---

*Built with ❤️ using Spring Boot | ShopEase Capstone Project 2026*
