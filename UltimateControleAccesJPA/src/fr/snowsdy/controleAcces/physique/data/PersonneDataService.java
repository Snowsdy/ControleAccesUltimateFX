package fr.snowsdy.controleAcces.physique.data;

import java.util.List;
import lml.persistence.CrudService;
import fr.snowsdy.controleAcces.metier.entities.Administrateur;
import fr.snowsdy.controleAcces.metier.entities.Personne;

/**
 *
 * @author fanou
 */
public interface PersonneDataService extends CrudService<Personne> {
    public Administrateur getByLogin(String login) throws Exception;
    public List<Personne> getByNom(String nom) throws Exception;
}
