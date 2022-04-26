package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Autorisation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Autorisation() {
    }
    
    @OneToOne
    private Salle salle;
    @OneToOne
    private Personne personne;
    @OneToOne
    private TimeSlot plageHoraire;

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

    /**
     * @return the plageHoraire
     */
    public TimeSlot getPlageHoraire() {
        return plageHoraire;
    }

    /**
     * @param plageHoraire the plageHoraire to set
     */
    public void setPlageHoraire(TimeSlot plageHoraire) {
        this.plageHoraire = plageHoraire;
    }
    
    @Override
    public String toString() {
        return "Autorisation : " + this.personne.toString() + " /Salle : " + this.salle.getId() + " /Horaire : " + this.plageHoraire.toString();
    }
    
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Autorisation)) {
            throw new ClassCastException();
        }
        return (o.hashCode() == this.hashCode());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.salle);
        hash = 97 * hash + Objects.hashCode(this.personne);
        hash = 97 * hash + Objects.hashCode(this.plageHoraire);
        return hash;
    }
}
