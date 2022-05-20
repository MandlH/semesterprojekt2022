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

    private String problem, firstname, secondname, message;
    private boolean accepted;



    @SuppressWarnings("unused")
    public Registration(){

    }

    public Registration(String email, String problem, String firstname, String secondname, String message){
        this.email = email;
        this.problem = problem;
        this.firstname = firstname;
        this.secondname = secondname;
        this.message = message;
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

}
