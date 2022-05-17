package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements ISaveAndDelete {

    @Id
    private String email;

    private String firstname, secondname, password;

    public User(){

    }

    public User(String email, String firstname, String password, String secondname){
        this.email = email;
        this.firstname = firstname;
        this.password = password;
        this.secondname = secondname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
}
