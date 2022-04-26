package fr.snowsdy.controleAcces.physique.data;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.Evenement;
import fr.snowsdy.controleAcces.metier.entities.Salle;

/**
 *
 * @author saturne
 */
class EvenementDataServiceJPAImpl extends AbstracCrudServiceJPA<Evenement> implements EvenementDataService {

    public EvenementDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Evenement> getByJour(Date jour) throws Exception {
        List<Evenement> events = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Evenement m WHERE m.date = :fdate");
            query.setParameter("fdate", jour);
            events = query.getResultList();
        } finally {
            this.close();
        }
        return events;
    }

    @Override
    public List<Evenement> getBySalle(Salle salle) throws Exception {
        List<Evenement> events = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Evenement m WHERE m.salle = :fsalle");
            query.setParameter("fsalle", salle);
            events = query.getResultList();
        } finally {
            this.close();
        }
        return events;
    }

   
}
