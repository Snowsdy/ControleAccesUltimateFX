package fr.snowsdy.controleAcces.physique.data;

import java.util.List;
import javax.persistence.Query;
import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.Salle;

/**
 *
 * @author saturne
 */
class SalleDataServiceJPAImpl extends AbstracCrudServiceJPA<Salle> implements SalleDataService {

    public SalleDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Salle> getByProtege(boolean protege) throws Exception {
        List<Salle> salles = null;
        try{
            this.open();
            Query query = em.createQuery("SELECT m FROM Salle m WHERE m.protege = :fprotege");
            query.setParameter("fprotege", protege);
            salles = query.getResultList();
        } finally {
            this.close();
        }
        return salles;
    }

}
