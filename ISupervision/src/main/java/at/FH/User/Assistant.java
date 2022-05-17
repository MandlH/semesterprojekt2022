package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;

import javax.persistence.*;

@Entity
public class Assistant extends User implements ISaveAndDelete {

    @SuppressWarnings("unused")
    private Assistant(){

    }

    @Column(unique = true)
    private int pnr;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Assistant(String email, String firstname, String password, String secondname){
        super(email, firstname, password, secondname);
        this.role = Role.ASSISTANT;
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
