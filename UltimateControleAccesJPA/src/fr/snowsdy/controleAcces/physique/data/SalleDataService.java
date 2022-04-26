package fr.snowsdy.controleAcces.physique.data;

import java.util.List;
import lml.persistence.CrudService;
import fr.snowsdy.controleAcces.metier.entities.Salle;

/**
 *
 * @author fanou
 */
public interface SalleDataService extends CrudService<Salle> {
    public List<Salle> getByProtege(boolean protege) throws Exception;
}

