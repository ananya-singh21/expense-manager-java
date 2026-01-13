# 💸 Expense Manager (Java Spring Boot)

A full-stack **Expense Manager web application** built using **Java Spring Boot** that allows users to track income, expenses, and manage their financial records through a clean web interface.

This project demonstrates backend development, MVC architecture, database interaction, and real-world project structure — suitable for **open-source contributions and GSoC preparation**.

---

## 🚀 Features

- User authentication (Login & Register)
- Add, view, and manage expenses
- Categorize expenses
- Dashboard view for expense tracking
- Server-side rendering using Thymeleaf
- Clean MVC-based project structure
- Maven-based dependency management

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Maven
- HTML / CSS
- H2 / MySQL (configurable)


## 📁 Project Structure

📦 expense-manager-java
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── ananya
│   │   │           └── expense_tracker
│   │   │               ├── controller
│   │   │               ├── service
│   │   │               ├── repository
│   │   │               ├── entity
│   │   │               └── ExpenseTrackerApplication.java
│   │   └── resources
│   │       ├── templates
│   │       ├── static
│   │       └── application.properties
│   └── test
│       └── java
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── LICENSE
└── README.md


## ▶️ How to Run Locally

### Prerequisites
- Java 17+ (or compatible)
- Maven (or Maven Wrapper)

### Steps

```bash
git clone https://github.com/ananya-singh21/expense-manager-java.git
cd expense-manager-java
./mvnw spring-boot:run
```

**For Windows:**
```bash
mvnw.cmd spring-boot:run
```

Open in browser:
```
http://localhost:8080
```

---

## ⚙️ Configuration

Application configuration can be modified in:

```
src/main/resources/application.properties
```

---

## 🧪 Testing

Run tests using:

```bash
./mvnw test
```


## 🌱 Future Enhancements

- REST API support
- Role-based authentication
- Expense analytics & charts
- Export expenses (CSV / PDF)
- Docker support
- Frontend migration to React

## 🤝 Open Source & GSoC Readiness

This project follows:

- Clean repository structure
- Proper commit history
- Open-source license
- Readable and maintainable code

### Suitable for:
- Open-source contributions
- Internship portfolios
- Google Summer of Code (GSoC) preparation


## 📄 License

This project is licensed under the MIT License.
See the LICENSE file for details.

## 👩‍💻 Author

**Ananya Singh**  
GitHub: https://github.com/ananya-singh21


