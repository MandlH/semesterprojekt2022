package at.FH.General;
import at.FH.Database.Connection;
import at.FH.Database.HibernateSupport;
import at.FH.Form.*;
import at.FH.Task.Project;
import at.FH.User.Admin;
import at.FH.User.Registration;
import at.FH.User.Student;
import at.FH.User.Assistant;




public class Application {
    public static void main(String[] args) {
        if(Connection.hostAvailable()){
            Admin admin1 = new Admin("admin@1", "admin01", "goodPassword", "Mandl");
            Admin admin2 = new Admin("admin@2", "admin02", "goodPassword", "Mandl");
            Assistant assistant1 = new Assistant("assistant@1", "assistant01", "goodPassword", "Mandl");
            Assistant assistant2 = new Assistant("assistant@2", "assistant02", "goodPassword", "Mandl");
            Student student1 = new Student("student@1", "student", "goodPassword", "Mandl");
            Student student2 = new Student("student@2", "student", "goodPassword", "Mandl");

            Project project1 = new Project("student@1", "Project1");
            Project project2 = new Project("student@1", "Project2");
            Project project3 = new Project("student@1", "Project3");

            HibernateSupport.beginTransaction();
            admin1.save();
            admin2.save();
            assistant1.save();
            assistant2.save();
//            project1.save();
//            project2.save();
//            project3.save();
            //student1.save();
            //student2.save();
            HibernateSupport.commitTransaction();

        }



        Login login = new Login();
        login.open();

    }

}
