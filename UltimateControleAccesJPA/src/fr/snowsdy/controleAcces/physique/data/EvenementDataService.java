package fr.snowsdy.controleAcces.physique.data;

import java.util.Date;
import java.util.List;
import lml.persistence.CrudService;
import fr.snowsdy.controleAcces.metier.entities.Evenement;
import fr.snowsdy.controleAcces.metier.entities.Salle;

/**
 *
 * @author fanou
 */
public interface EvenementDataService extends CrudService<Evenement> {
    public List<Evenement> getByJour(Date jour) throws Exception;
    public List<Evenement> getBySalle(Salle salle) throws Exception;
}
