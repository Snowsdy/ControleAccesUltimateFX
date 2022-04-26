/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.snowsdy.controleAcces.physique.data;

import javax.persistence.Query;
import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.Attribution;
import fr.snowsdy.controleAcces.metier.entities.Badge;
import fr.snowsdy.controleAcces.metier.entities.Personne;

/**
 *
 * @author saturne
 */
class AttributionDataServiceJPAImpl extends AbstracCrudServiceJPA<Attribution> implements AttributionDataService {

    public AttributionDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public Attribution getByBadge(Badge badge) throws Exception {
        Attribution attr = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Attribution m WHERE m.badge = :fbadge");
            query.setParameter("fbadge", badge);
            attr = (Attribution) query.getSingleResult();
        } finally {
            this.close();
        }
        return attr;
    }

    @Override
    public Attribution getByPersonne(Personne personne) throws Exception {
        Attribution attr = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Attribution m WHERE m.personne = :fpersonne");
            query.setParameter("fpersonne", personne);
            attr = (Attribution) query.getSingleResult();
        } finally {
            this.close();
        }
        return attr;
    }
    
}
