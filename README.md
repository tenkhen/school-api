# School API

**School API** is a fully functional RESTful API built using Spring Boot. It allows users to manage students, teachers, courses, classes, and enrollments in a school system. The API supports CRUD operations, pagination, filtering, and includes comprehensive Swagger documentation.

---

## Features

- **CRUD Operations**: Manage students, teachers, courses, classes, and enrollments.
- **Pagination and Filtering**: Fetch paginated lists of entities with optional filtering.
- **Relationship Management**: Handle many-to-many relationships like student enrollments in courses.
- **Swagger Documentation**: View and test all API endpoints using Swagger UI.
- **Robust Validation**: Ensures data integrity and provides meaningful error messages.

---

## Technologies Used

- **Backend**: Spring Boot (Java)
- **Database**: MySQL
- **Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Testing**: Postman

---

## Installation

### Prerequisites

1. Java 17 or higher
2. Maven
3. MySQL Database

### Clone the Repository

```bash
git clone https://github.com/tenkhen/school-api.git
cd school-api
```

### Configure the Database

Update the `application.properties` file in the `src/main/resources` directory:

```properties
spring.datasource.url=jdbc:[db_mysql]
spring.datasource.username=[db_username]
spring.datasource.password=[db_password]
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run the Application

1. Build the application:
   ```bash
   mvn clean package
   ```
2. Run the application:
   ```bash
   java -jar target/school-api-0.0.1-SNAPSHOT.jar
   ```

The application will start at `http://localhost:8080`.

---

## API Documentation

Swagger UI is available at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Endpoints

### **Student**

- `GET /api/students`: Get all students
- `GET /api/students/{id}`: Get student by ID
- `POST /api/students`: Create a new student
- `PUT /api/students/{id}`: Update a student
- `DELETE /api/students/{id}`: Delete a student
- `GET /api/students/search?name={name}`: Search students by name (with pagination)
- `GET /api/students/page?page={page}&size={size}`: Get paginated students

### **Teacher**

- `GET /api/teachers`: Get all teachers
- `GET /api/teachers/{id}`: Get teacher by ID
- `POST /api/teachers`: Create a new teacher
- `PUT /api/teachers/{id}`: Update a teacher
- `DELETE /api/teachers/{id}`: Delete a teacher

### **Course**

- `GET /api/courses`: Get all courses
- `GET /api/courses/{id}`: Get course by ID
- `POST /api/courses`: Create a new course
- `PUT /api/courses/{id}`: Update a course
- `DELETE /api/courses/{id}`: Delete a course

### **Class**

- `GET /api/classes`: Get all classes
- `GET /api/classes/{id}`: Get class by ID
- `POST /api/classes`: Create a new class
- `PUT /api/classes/{id}`: Update a class
- `DELETE /api/classes/{id}`: Delete a class

### **Enrollment**

- `GET /api/enrollments`: Get all enrollments
- `GET /api/enrollments/student/{studentId}`: Get enrollments by student ID
- `GET /api/enrollments/course/{courseId}`: Get enrollments by course ID
- `POST /api/enrollments`: Enroll a student in a course
- `DELETE /api/enrollments/{id}`: Delete an enrollment

---

## Example Payloads

### **Create a Student**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "dateOfBirth": "2001-01-01"
}
```

### **Enroll a Student in a Course**

```json
{
  "studentId": 5,
  "courseId": 3
}
```

---

## Error Handling

The API returns meaningful error responses for invalid operations:

- **400 Bad Request**: Validation or input error.
- **404 Not Found**: Entity not found.
- **500 Internal Server Error**: Unexpected server-side error.

---

## Future Enhancements

- Add support for advanced filtering and sorting.
- Implement role-based authentication and authorization.
- Provide export/import functionality (e.g., CSV/JSON).
- Add caching for frequently accessed data.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Author

Created by **Khenrab Dorjee Lama**

- Email: [khenrabdorjee.lama@gmail.com](mailto:khenrabdorjee.lama@gmail.com)
- Portfolio: [khenrab.io](https://khenrab.io)
- LinkedIn: [Khenrab Dorjee Lama](https://www.linkedin.com/in/khenrab-dorjee-lama-5a212b162)

Feel free to reach out for questions or collaboration!
