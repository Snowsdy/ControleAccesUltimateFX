package fr.snowsdy.controleAcces.physique.data;

import lml.persistence.CrudService;
import fr.snowsdy.controleAcces.metier.entities.Attribution;
import fr.snowsdy.controleAcces.metier.entities.Badge;
import fr.snowsdy.controleAcces.metier.entities.Personne;

/**
 *
 * @author fanou
 */
public interface AttributionDataService extends CrudService<Attribution> {
    public Attribution getByBadge(Badge badge) throws Exception;
    public Attribution getByPersonne(Personne personne) throws Exception;
}
