package example.nio;

import example.nio.entities.Course;
import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try(factory;session){
            session.beginTransaction();

            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Instructor: " + instructor);

            // get course for the instructor
            System.out.println("Courses: " + instructor.getCourses());

            session.getTransaction().commit();
            System.out.println("Done!");
        }
    }
}
