package example.nio;

import example.nio.entities.Course;
import example.nio.entities.Instructor;
import example.nio.entities.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

            // hibernate query with HQL

            int id = 1;
            Query<Instructor> query =
                    session.createQuery("select i from Instructor  i "
                            + "JOIN fetch i.courses "
                            + "where i.id=:theInstructorId",
                            Instructor.class);

            // set parameter on query
            query.setParameter("theInstructorId", id);

            // execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("code: Instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();
            session.close();

            System.out.println("\ncode: The session is now closed!\n");
            
            // get course for the instructor
            System.out.println("code: Courses: " + instructor.getCourses());

            System.out.println("code: Done!");
        }
    }
}
