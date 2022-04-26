package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evenement implements Serializable {

    public Evenement() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Personne personne;
    @OneToOne
    private Salle salle;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dte")
    private Date date;
    private boolean autorise;

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
     * @return the salle
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     * @param salle the salle to set
     */
    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the autorise
     */
    public boolean isAutorise() {
        return autorise;
    }

    /**
     * @param authoriser
     */
    public void setAutorise(boolean authoriser) {
        this.autorise = authoriser;
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
        String s = " est allé à la salle ";
        if (!this.autorise) {
            s = " n'a pas eu accès à la salle ";
        }
        return "Event : " + this.personne + s + this.salle.getId() + " le " + this.date;
    }
}
