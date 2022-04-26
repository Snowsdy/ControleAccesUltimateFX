package fr.snowsdy.controleAcces.physique.data;

import javax.persistence.Query;
import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.Badge;

/**
 *
 * @author saturne
 */
class BadgeDataServiceJPAImpl extends AbstracCrudServiceJPA<Badge> implements BadgeDataService {

    public BadgeDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public Badge getByContenu(String contenu) throws Exception {
        Badge badge = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Badge m WHERE m.contenu = :fcontenu");
            query.setParameter("fcontenu", contenu);
            badge = (Badge) query.getSingleResult();
        } finally {
            this.close();
        }
        return badge;
    }
    
}
