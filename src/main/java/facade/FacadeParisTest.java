package facade;


import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;

import java.util.Collection;
import java.util.function.Supplier;

public abstract class FacadeParisTest {
    private FacadeParis facadeParis;
    private Supplier<FacadeParis> fabrique;

    public void setUp() throws Exception {
        facadeParis = fabrique.get();
    }

    public void specialDedicaceYo(Supplier<FacadeParis> fabrique) {
        this.fabrique = fabrique;
    }


    public void connexionOK() throws InformationsSaisiesIncoherentesException, UtilisateurDejaConnecteException {
        facadeParis.connexion("fred","fred");
    }


    public void connexionKO1() throws InformationsSaisiesIncoherentesException, UtilisateurDejaConnecteException {
        facadeParis.connexion("abc","abc");
    }


    public void connexionKO2() throws InformationsSaisiesIncoherentesException, UtilisateurDejaConnecteException {
        facadeParis.connexion("fred","fred");
        facadeParis.connexion("fred","fred");
    }


    public void getMatchsPasCommences() {
    }

    public void parier() {
    }

    public void annulerPari() {
    }

    public void getMesParis() {
    }

    public void ajouterMatch() {
    }

    public void resultatMatch() {
    }

    public void deconnexion() {
    }

    public void getAllParis() {
    }

    public void getMatch() {
    }

    public void getPari() {
    }

    public void getAllMatchs() {
    }
}
