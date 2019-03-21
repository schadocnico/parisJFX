package controleur;

import controleur.notifications.Notification;
import controleur.notifications.Observateur;
import controleur.notifications.Sujet;
import facade.FacadeParis;
import facade.exceptions.*;
import modele.Match;
import modele.Pari;
import views.*;
import views.jfx.*;

import java.util.ArrayList;
import java.util.Collection;

public class Controleur implements Observateur {

        private ParisOuvertsInterface parisOuverts;
        private MenuPrincipalInterface maVue;
        private RecapParierInterface recapParier;
        private AnnulerInterface annulerInterface;
        private RecapAnnulerInterface recapAnnulerInterface;

        private FabriqueVues fabriqueVues;
        private FacadeParis modele;
        private String pseudo;
        private AccueilInterface accueilView;
        private ArrayList<String> liste;
        private MesParisInterface mesParisInterface;
        private views.ParierInterface parierInterface;
        private Match match_var;
        private long idM;
        private Pari pariAnnuler;



        private Collection<Sujet> sujets;

    public Controleur(FabriqueVues fabriqueVues, FacadeParis modele)
        {
            this.modele = modele;
            this.fabriqueVues = fabriqueVues;
            sujets = new ArrayList<>();
            this.initialisationVues();
            this.maVue.show();
        }


        public void initialisationVues() {
            this.maVue = this.fabriqueVues.buildMenuPrincipalView(this);
            this.accueilView = (views.jfx.AccueilView) this.fabriqueVues.buildAccueilView(this);
            this.maVue = this.fabriqueVues.buildMenuPrincipalView(this);
            this.parisOuverts = (ParisOuvertsView) this.fabriqueVues.buildParisOuvertsView(this);

            this.parierInterface = this.fabriqueVues.buildParierView(this);
            this.mesParisInterface = this.fabriqueVues.buildMesParisView(this);

            this.recapParier = this.fabriqueVues.buildRecapParierView(this);

            this.annulerInterface = this.fabriqueVues.buildAnnulerView(this);

            this.recapAnnulerInterface = this.fabriqueVues.buildRecapAnnulerView(this);



        }


    public ArrayList<String> getEquipes(long idMatch){
        long idM = idMatch;
        ArrayList<String> listeVainqueurs = new ArrayList<String>();
        listeVainqueurs.add(modele.getMatch(idM).getEquipe1());
        listeVainqueurs.add(modele.getMatch(idM).getEquipe2());
        return listeVainqueurs;
        }


    public void goToMenuPrincipal(){
            this.accueilView.show();
        }


////////////////////////////////////////////////////////////////////////////:

        public void goToAccueil(String user, String password){
            pseudo = user;
            try {
                modele.connexion(pseudo, password);
                this.accueilView.show();

            } catch (UtilisateurDejaConnecteException e) {

                Notification notification = Notification.creer("Utilisateur déjà connecté", Notification.TypeNotification.CONNEXION_ERREUR);
                this.broadCastNotification(notification);
            } catch (InformationsSaisiesIncoherentesException e) {
                Notification notification = Notification.creer("Veuillez saisir votre login et/ou pseudo", Notification.TypeNotification.CONNEXION_ERREUR);

                this.broadCastNotification(notification);
            }
        }

    public void goToDeconnexion() {
        this.modele.deconnexion(pseudo);
        this.maVue.show();

    }

    public void goToParisOuverts() {
        Collection<Match> liste = modele.getMatchsPasCommences();
        this.parisOuverts.majListeAllParis(liste);
        this.parisOuverts.show();
    }


    public void goToMesParis() {
        Collection<Pari> liste = modele.getMesParis(pseudo);
        this.mesParisInterface.majListeMesParis(liste);
        this.mesParisInterface.show();
    }


    public void goToParier(Match match) {
        match_var = match;
        liste = getEquipes(match_var.getIdMatch());
        Notification notification = Notification.creerMajEquipe(liste);
        this.broadCastNotification(notification);





        this.parierInterface.show();
    }

    public void parier(double mise, String equipe) {
        idM = match_var.getIdMatch();
    try {
        long pari =modele.parier(pseudo,idM,equipe,mise);
        Pari idPari = modele.getPari(pari);
        this.recapParier.majPari(idPari);
        this.recapParier.show();
////////////////////////////////////////////////////////////////////////////:

    } catch (MatchClosException e) {
        Notification notification = Notification.creer("Le match est clos", Notification.TypeNotification.PARIER_ERREUR);
        this.broadCastNotification(notification);

        }

        catch (ResultatImpossibleException e) {
            Notification notification = Notification.creer("Le résultat est incorrect", Notification.TypeNotification.PARIER_ERREUR);
            this.broadCastNotification(notification);
    }

    }

    public void goToAnnuler(Pari pari) {
        pariAnnuler = pari;
        this.annulerInterface.majMonAnnulation(pari);
        this.annulerInterface.show();

    }

    public void annuler() {
        try {
            modele.annulerPari(pseudo,pariAnnuler.getIdPari());
            Notification notification = Notification.creerMajAnnulationPari(pariAnnuler);
            this.broadCastNotification(notification);
//            this.recapAnnulerInterface.majPariAnnule(pariAnnuler);
            this.recapAnnulerInterface.show();

        } catch (OperationNonAuthoriseeException e) {
            ////////////////////////////////////////////////////////////////////////////:
            Notification notification = Notification.creer("Opération non authorisée", Notification.TypeNotification.ANNULER_PARI_ERREUR);
            this.broadCastNotification(notification);
        }

    }


    @Override
    public void enregistrerSujet(Sujet s) {
        this.sujets.add(s);

    }

    @Override
    public void broadCastNotification(Notification n) {

        for(Sujet s : sujets) {
            s.notifier(n);
        }
    }

}
