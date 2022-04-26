package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Attribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Attribution() {
    }
    
    @OneToOne
    private Badge badge;
    @OneToOne
    private Personne personne;

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
     * @return the badge
     */
    public Badge getBadge() {
        return badge;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attribution other = (Attribution) obj;
        return this.id == other.id;
    }

    /**
     * @param badge the badge to set
     */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    /**
     * @return the personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne the personne to set
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    
    @Override
    public String toString() {
        return "Badge : " + this.badge.getContenu() + " attribué à " + this.personne.toString();
    }
}
