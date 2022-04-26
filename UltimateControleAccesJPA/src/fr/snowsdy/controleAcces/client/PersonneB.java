package fr.snowsdy.controleAcces.client;

import fr.snowsdy.controleAcces.metier.entities.Administrateur;
import fr.snowsdy.controleAcces.metier.entities.Personne;

import java.io.Serializable;

public class PersonneB implements Serializable {

    private long id;
    private String nom;
    private String prenom;
    private char discriminant;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(Personne p) {
        if (p instanceof Administrateur) {
            Administrateur a = (Administrateur) p;
            this.login = a.getLogin();
        } else {
            this.login = "";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(Personne p) {
        if (p instanceof Administrateur) {
            Administrateur a = (Administrateur) p;
            this.password = a.getMdp();
        } else {
            this.password = "";
        }
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public char getDiscriminant() {
        return discriminant;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDiscriminant(Personne p) {
        if (p instanceof Administrateur) {
            this.discriminant = 'A';
        } else {
            this.discriminant = 'P';
        }
    }
}
