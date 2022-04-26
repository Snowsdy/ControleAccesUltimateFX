package fr.snowsdy.controleAcces.physique.data;

import lml.persistence.CrudService;
import fr.snowsdy.controleAcces.metier.entities.Badge;

/**
 *
 * @author fanou
 */
public interface BadgeDataService extends CrudService<Badge> { 
    public Badge getByContenu(String contenu) throws Exception;
}
