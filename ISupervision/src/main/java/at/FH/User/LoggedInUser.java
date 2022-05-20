package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.Exception.BufferedWriter;
import at.FH.Exception.ObjectNotFound;
import at.FH.General.ILoggedIn;

import at.FH.Task.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import at.FH.General.ISaveAndDelete;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoggedInUser implements ISaveAndDelete {


    private Admin admin;
    private Student student;
    private Assistant assistant;
    private static final Logger logger = LogManager.getLogger();
    private static List<Criterion> criterions = new ArrayList<Criterion>();
    private static List<Project> pList;
    private static List<Project> bList;
    private static List<Project> mList;


    public LoggedInUser(Admin admin){
        this.admin = admin;
        logInfo();
    }

    public LoggedInUser(Student student){
        this.student = student;

        criterions.add(Restrictions.eq("student", student));

        pList = HibernateSupport.readMoreObjects(Project.class, criterions);

        student.setProjects(pList);
        
        at.FH.Form.Student form = new at.FH.Form.Student();
        form.open(this.student);
        logInfo();
    }

    public LoggedInUser(Assistant assistant){
        this.assistant = assistant;
        logInfo();
    }

    private void clearList(){
        pList.clear();
        mList.clear();
        bList.clear();
    }




    private String showName(){
        if(admin != null)
            return admin.show();
        if(assistant != null)
            return assistant.show();
        if(student != null)
            return student.show();
        return "No User";
    }

    private void logInfo(){
        logger.info("User " + showName() + " successfully logged in");
    }

    @Override
    public boolean save(){
        if(admin != null)
            admin.save();
        if(assistant != null)
            assistant.save();
        if(student != null)
            student.save();
        return true;
    }

    @Override
    public void delete() {
        if(admin != null)
            admin.delete();
        if(assistant != null)
            assistant.delete();
        if(student != null)
            student.delete();
    }


}
