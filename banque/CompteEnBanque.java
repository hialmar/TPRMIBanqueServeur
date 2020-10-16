package banque;
import java.rmi.RemoteException;
import java.util.Date;

import partage.Notification;

/**
 * Gère un compte en banque
 * @author torguet
 *
 */
public class CompteEnBanque {
	/**
	 * solde du compte
	 */
    private double solde;
    /**
     * date de la dernière opération
     */
    private Date derniereOperation;
    
    private Notification notification;

    /**
     * constructeur
     * @param somme : la somme initiale
     */
    public CompteEnBanque(double somme) {
        this.solde = somme;
        derniereOperation = new Date(); // recupere la date courante
    }

    /**
     * retourne le solde du compte
     * @return le solde du compte
     */
    public double getSolde() {
        return solde;
    }

    /**
     * retourne la date de dernière opération
     * @return la date
     */
    public Date getDerniereOperation() {
        return derniereOperation;
    }

    /**
     * Ajoute une somme au compte
     * @param somme : la somme
     */
    public void ajouter(double somme) {
        solde += somme;
        derniereOperation = new Date(); // recupere la date courante
    }

    /**
     * Retire une somme du compte
     * @param somme : la somme
     */
    public void retirer(double somme) {    		
        solde -= somme;
        derniereOperation = new Date(); // recupere la date courante
        if(solde < 0) {
        	if(notification != null) {
        		try {
					notification.notifier("Compte a decouvert");
				} catch (RemoteException e) {
					notification = null;
				}
        	}
        }
    }

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

    
    
}