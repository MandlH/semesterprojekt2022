package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;

import javax.persistence.*;

@Entity
@Table(name = "registration")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Registration implements ISaveAndDelete {

    @Id
    private String email;

    private String problem, firstname, secondname, message, password;



    @SuppressWarnings("unused")
    public Registration(){

    }

    public Registration(String email, String problem, String firstname, String secondname, String message, String password){
        this.email = email;
        this.problem = problem;
        this.firstname = firstname;
        this.secondname = secondname;
        this.message = message;
        this.password = password;
    }

    @Override
    public boolean save() {
        if(!HibernateSupport.commit(this))
            return false;

        return true;
    }

    @Override
    public void delete() {
        HibernateSupport.deleteObject(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProblem() {
        return problem;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getMessage() {
        return message;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
