package example.nio;

import example.nio.entities.Course;
import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

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

            System.out.println("code: Instructor: " + instructor);

            // get course for the instructor
            System.out.println("code: Courses: " + instructor.getCourses());

            session.getTransaction().commit();
            session.close();

            System.out.println("\ncode: The session is now closed!\n");
            
            // get course for the instructor
            System.out.println("code: Courses: " + instructor.getCourses());

            System.out.println("code: Done!");
        }
    }
}
