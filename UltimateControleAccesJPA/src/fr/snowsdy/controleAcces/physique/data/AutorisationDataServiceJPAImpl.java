package fr.snowsdy.controleAcces.physique.data;

import java.util.List;
import javax.persistence.Query;
import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.Autorisation;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.metier.entities.Salle;
import fr.snowsdy.controleAcces.metier.entities.TimeSlot;

/**
 *
 * @author saturne
 */
class AutorisationDataServiceJPAImpl extends AbstracCrudServiceJPA<Autorisation> implements AutorisationDataService {

    public AutorisationDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Autorisation> getBySalle(Salle salle) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.salle = :fsalle");
            query.setParameter("fsalle", salle);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getByPersonne(Personne personne) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.personne = :fpersonne");
            query.setParameter("fpersonne", personne);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getByPlageHoraire(TimeSlot plageHoraire) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.plageHoraire = :fts");
            query.setParameter("fts", plageHoraire);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getByPeronneEtSalle(Personne personne, Salle salle) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.personne = :fpersonne AND m.salle = :fsalle");
            query.setParameter("fpersonne", personne);
            query.setParameter("fsalle", salle);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    
}
