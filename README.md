
# **Task Management System**

## **Overview**
The Task Management System is a backend service for managing projects and tasks. It provides CRUD operations for projects, tasks, and users and allows task assignments and status updates. The application is built using Java with Spring Boot, implementing both JPA and JDBC for communication with database.

### **Development**
The system uses an H2 database for development and testing purposes.
### **Production**
The backend application has been deployed on Railway, using PostgreSQL as the production database.

### **Swagger UI**
You can interact with the Task Management System API directly through the **Swagger UI**. Click the logo below to visit the interactive API documentation:

<a href="https://task-management-system-production-299b.up.railway.app/swagger-ui/index.html" target="_blank">
    <img src="https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg" height="50" width="174" alt="Swagger Logo">
</a>

Alternatively, you can visit the Swagger UI documentation here: <br>
https://task-management-system-production-299b.up.railway.app/swagger-ui/index.html


---

## **Features**
- **Project Management**: Create, edit, view, and delete projects.
- **Task Management**: Add, update, change status, and manage tasks.
- **User Management**: Handle user data and assign projects and tasks.
- **Database Interaction**: Supports both JPA and JDBC implementations for repository services.
- **Error Handling**: Custom exceptions for enhanced error messages.
- **Integration Tests**: Provides integration tests for validation.

---

## **Technologies Used**
- **Java 17**
- **Spring Boot 3.4.0**
- **JPA / JDBC**
- **Maven**
- **JUnit**
- **H2 Database** - for development
- **H2 PostgreSQL** - production
- **Railway**

---

