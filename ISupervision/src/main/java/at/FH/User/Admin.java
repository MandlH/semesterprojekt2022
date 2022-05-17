package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Admin extends User implements ISaveAndDelete {

    @SuppressWarnings("unused")
    protected Admin(){

    }

    @Column(unique = true)
    private int pnr;

    @Enumerated(EnumType.STRING)
    private Role role;


    public Admin(String email, String firstname, String password, String secondname){
        super(email, firstname, password, secondname);
        this.role = Role.ADMIN;
    }

    public void setPnr(int pnr) {
        this.pnr = pnr;
    }

    public int getPnr() {
        return pnr;
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
