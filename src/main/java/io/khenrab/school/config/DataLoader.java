//package io.khenrab.school.config;
//
//import com.github.javafaker.Faker;
//import io.khenrab.school.entity.Course;
//import io.khenrab.school.entity.Student;
//import io.khenrab.school.entity.Teacher;
//import io.khenrab.school.repository.CourseRepository;
//import io.khenrab.school.repository.StudentRepository;
//import io.khenrab.school.repository.TeacherRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//// Fake data creator for database
//@RequiredArgsConstructor
////@Component
//public class DataLoader implements CommandLineRunner {
//    private final StudentRepository studentRepository;
//    private final TeacherRepository teacherRepository;
//    private final CourseRepository courseRepository;
//
//    @Override
//    public void run(String... args) {
//        Faker faker = new Faker();
//        Random random = new Random();
//
//        // Generate Teachers
//        List<Teacher> teachers = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Teacher teacher = new Teacher();
//            teacher.setFirstName(faker.name().firstName());
//            teacher.setLastName(faker.name().lastName());
//            teacher.setEmail(faker.internet().emailAddress());
//            teacher.setSpecialization(faker.educator().course());
//            teachers.add(teacher);
//        }
//        teacherRepository.saveAll(teachers);
//
//        // Generate Students
//        List<Student> students = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            Student student = new Student();
//            student.setFirstName(faker.name().firstName());
//            student.setLastName(faker.name().lastName());
//            student.setEmail(faker.internet().emailAddress());
//            student.setDateOfBirth(LocalDate.of(
//                    random.nextInt(15) + 1990, // Year between 1990-2005
//                    random.nextInt(12) + 1,   // Month
//                    random.nextInt(28) + 1    // Day
//            ));
//            students.add(student);
//        }
//        studentRepository.saveAll(students);
//
//        // Generate Courses
//        List<Course> courses = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Course course = new Course();
//            course.setName(faker.educator().course());
//            course.setDescription(faker.lorem().sentence(10));
//            course.setTeacher(teachers.get(random.nextInt(teachers.size())));
//            courses.add(course);
//        }
//        courseRepository.saveAll(courses);
//
//        System.out.println("Fake data loaded successfully!");
//    }
//}