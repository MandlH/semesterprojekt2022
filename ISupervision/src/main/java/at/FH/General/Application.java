package at.FH.General;
import at.FH.Database.HibernateSupport;
import at.FH.Form.*;
import at.FH.User.Admin;
import at.FH.User.Assistant;
import at.FH.User.User;


public class Application {
    public static void main(String[] args) {
        System.out.println("Start Test Function");

        Admin admin1 = new Admin("admin@1", "admin01", "goodPassword", "Mandl");
        Admin admin2 = new Admin("admin@2", "admin02", "goodPassword", "Mandl");
        Assistant assistant1 = new Assistant("assistant@1", "assistant01", "goodPassword", "Mandl");
        //Assistant assistant2 = new Assistant("assistant@2", "assistant02", "goodPassword", "Mandl");
        HibernateSupport.beginTransaction();
        //admin1.save();
        admin2.save();
        assistant1.save();
        //assistant2.save();
        HibernateSupport.commitTransaction();

        Login login = new Login();
        login.open();

    }

}
