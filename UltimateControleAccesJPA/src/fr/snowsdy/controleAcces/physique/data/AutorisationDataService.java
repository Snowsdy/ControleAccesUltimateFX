package fr.snowsdy.controleAcces.physique.data;

import java.util.List;
import lml.persistence.CrudService;
import fr.snowsdy.controleAcces.metier.entities.Autorisation;
import fr.snowsdy.controleAcces.metier.entities.Personne;
import fr.snowsdy.controleAcces.metier.entities.Salle;
import fr.snowsdy.controleAcces.metier.entities.TimeSlot;

/**
 *
 * @author fanou
 */
public interface AutorisationDataService extends CrudService<Autorisation> {
    public List<Autorisation> getBySalle(Salle salle) throws Exception;
    public List<Autorisation> getByPersonne(Personne personne) throws Exception;
    public List<Autorisation> getByPlageHoraire(TimeSlot plageHoraire) throws Exception;
    public List<Autorisation> getByPeronneEtSalle(Personne personne, Salle salle) throws Exception;
}
