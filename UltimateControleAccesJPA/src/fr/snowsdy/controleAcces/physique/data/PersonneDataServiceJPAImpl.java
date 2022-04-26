package fr.snowsdy.controleAcces.physique.data;

import java.util.List;
import javax.persistence.Query;
import fr.snowsdy.controleAcces.persistence.jpa.AbstracCrudServiceJPA;
import fr.snowsdy.controleAcces.metier.entities.Administrateur;
import fr.snowsdy.controleAcces.metier.entities.Personne;

/**
 *
 * @author saturne
 */
class PersonneDataServiceJPAImpl extends AbstracCrudServiceJPA<Personne> implements PersonneDataService {

    public PersonneDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public Administrateur getByLogin(String login) throws Exception {
        Administrateur a = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Personne m WHERE m.login = :flogin");
            query.setParameter("flogin", login);
            a = (Administrateur) query.getSingleResult();
        } finally {
            this.close();
        }
        return a;
    }

    @Override
    public List<Personne> getByNom(String nom) throws Exception {
        List<Personne> personnes = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Personne m WHERE m.nom = :fnom ORDER BY m.id ASC");
            query.setParameter("fnom", nom);
            personnes = query.getResultList();
        } finally {
            this.close();
        }
        return personnes;
    }

    @Override
    public void update(Personne p) throws Exception {
//        super.update(p);
        Administrateur a = new Administrateur();

        try {
            this.open();
            // insertion update du type
            char dsc = 'P';
            if (p instanceof Administrateur) {
                a = (Administrateur) p;
                dsc = 'A';
            }
            Query query = em.createNativeQuery("UPDATE PERSONNE SET `LOGIN` = ?, `MDP` = ?, `dsc` = ? WHERE ID = ?");
            query.setParameter(1, a.getLogin());
            query.setParameter(2, a.getMdp());
            query.setParameter(3, dsc);
            query.setParameter(4, p.getId());

            query.executeUpdate();
            this.transac.commit();
        } catch(Exception ex) {
            System.err.println(ex);
        } finally {
            this.close();
        }
    }
    
}
