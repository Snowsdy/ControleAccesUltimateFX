package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Badge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contenu;

    public Badge() {
    }
    

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
     * @return the contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * @param contenu the contenu to set
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    @Override
    public String toString() {
        return this.id + " : " + this.contenu;
    }
    
    @Override
    public boolean equals(Object o){
//        if (!(o instanceof Badge)) {
//            throw new ClassCastException();
//        }
//        return (o.hashCode() == this.hashCode());
        return o instanceof Badge;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.contenu);
        return hash;
    }
}
