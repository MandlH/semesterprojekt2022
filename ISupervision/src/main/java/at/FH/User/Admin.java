package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ILoggedIn;
import at.FH.General.ISaveAndDelete;

import javax.persistence.*;

@Entity(name = "admin")
public class Admin extends User implements ISaveAndDelete, ILoggedIn {

    @SuppressWarnings("unused")
    public Admin(){

    }

    @Enumerated(EnumType.STRING)
    private Role role;


    public Admin(String email, String firstname, String password, String secondname){
        super(email, firstname, password, secondname);
        this.role = Role.ADMIN;
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

    @Override
    public String show() {
        return getFirstname();
    }
}
