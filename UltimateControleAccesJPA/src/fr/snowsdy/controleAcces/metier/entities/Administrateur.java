package fr.snowsdy.controleAcces.metier.entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Administrateur extends Personne implements Serializable {

    private String login;
    private String mdp;

    private String codeMD5(String msg) throws NoSuchAlgorithmException {
        String code = "";
        byte[] b;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            b = md.digest(msg.getBytes());
            for (int i = 0; i < b.length; i++) {
                int x = b[i];

                if (x < 0) {
                    x += 256;
                }
                
                String s = String.format("%02x", x);
                code += s;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }

        return code;
    }

    public boolean isValid(String mdp) throws NoSuchAlgorithmException {
        return this.mdp.equals(this.codeMD5(mdp));
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     * @throws NoSuchAlgorithmException
     */
    public void setMdp(String mdp) throws NoSuchAlgorithmException {
        this.mdp = this.codeMD5(mdp);
    }
    
    public void setEncodedMdp(String mdp) throws NoSuchAlgorithmException {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "(" + this.login + ") " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Administrateur | o instanceof Personne)) {
            throw new ClassCastException();
        }
        return (o.hashCode() == this.hashCode());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.login);
        return hash;
    }
}
