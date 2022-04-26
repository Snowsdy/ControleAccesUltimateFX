package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Salle implements Serializable{
    @Id
    private long id;
    private boolean protege;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the protege
     */
    public boolean isProtege() {
        return protege;
    }

    /**
     * @param protege the protege to set
     */
    public void setProtege(boolean protege) {
        this.protege = protege;
    }
    
    @Override
    public String toString() {
        String protec = " est protégée";
        if (!this.protege) {
            protec = " n'est pas protégée";
        }
        return this.id + protec;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = (int) (29 * hash + this.id);
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Salle)) {
            throw new ClassCastException();
        }
        return (o.hashCode() == this.hashCode());
    }
}
